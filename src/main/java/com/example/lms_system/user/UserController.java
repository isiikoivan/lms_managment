package com.example.lms_system.user;

import com.example.lms_system.exception.validation.ValidationException;
import com.github.javafaker.Faker;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("librarian/user")
public class UserController {
    private final UserRepository repository;
    private final Validator validator;
//    Faker faker = new Faker();
//    @PostConstruct
//    public void fakeInput(){
//        List<Users> users = IntStream.rangeClosed(1,300)
//                .mapToObj(i->new Users(
//                        null,
//                        faker.name().firstName(),
//                        faker.name().lastName(),
//                        faker.name().lastName(),
//                        faker.internet().emailAddress(),
//                        faker.phoneNumber().phoneNumber(),
//                        faker.address().fullAddress(),
//                        faker.name().title(),
//                        null,
//                        new Date(System.currentTimeMillis()),
//                        faker.name().username(),
//                        faker.internet().password(),
//                        null
//                )).toList();
//        repository.saveAll(users);
//    }
    @GetMapping
    List<Users> all(){
        return repository.findAll();
    }
    @GetMapping("/{id}")
    Optional<Users> single(@PathVariable Long id){
        return repository.findById(id);
    }
    @GetMapping("/paginate/{offset}/{size}")
    Page<Users> pages(@PathVariable int offset, @PathVariable int size){
        return repository.findAll(PageRequest.of(offset, size));
    }
    @GetMapping("/first/{name}")
    List<Users> bySur(@PathVariable String name){
        return repository.findBySurNameContainingIgnoreCase(name);
    }
    @GetMapping("/other/{name}")
    List<Users> byOther(@PathVariable String name){
        return repository.findByOtherNamesContainingIgnoreCase(name);
    }
    @GetMapping("/username/{name}")
    Optional<Users> byUname(@PathVariable String name){
        return repository.findByUsername(name);
    }
    @GetMapping("/sort/asc/{field}")
    List<Users> sorted(@PathVariable String field){
        return repository.findAll(Sort.by(Sort.Direction.ASC,field));
    }
    @GetMapping("/sort/desc/{field}")
    List<Users> sortDesc(@PathVariable String field){
        return repository.findAll(Sort.by(Sort.Direction.DESC,field));
    }
    @PostMapping
    String add(@Valid @RequestBody Users user) {
        Set<ConstraintViolation<Users>> violations = validator.validate(user);

        if (repository.findByEmail(user.getEmail()).isPresent()){
            throw new ValidationException("Username already exists. Please find another.");
        } else if (!violations.isEmpty()) {
            throw new ValidationException("First and last names should not be left empty.");
        }
        repository.save(user);
        return "Saved";
    }
    @PutMapping
    Users update(@RequestBody Users user){
        return repository.save(user);
    }
    @DeleteMapping("/{id}")
    void remove(@PathVariable Long id){
        repository.deleteById(id);
    }
}
