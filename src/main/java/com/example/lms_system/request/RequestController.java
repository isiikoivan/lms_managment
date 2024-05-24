package com.example.lms_system.request;

import com.example.lms_system.exception.notfound.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/request")
public class RequestController {
    private final RequestRepository repository;
//    Faker faker = new Faker();
//    @PostConstruct
//    public void fakeInput() {
//        List<Request> users = IntStream.rangeClosed(1, 5)
//                .mapToObj(i -> new Request(
//                        null,
//                        "Borrow",
//                        null,
//                        new Date(System.currentTimeMillis()),
//                        new Date(System.currentTimeMillis()),
//                        true,
//                        null,
////                        null,
//                        new Date(System.currentTimeMillis()+1000*60*48),
//                        null
//                )).toList();
//        repository.saveAll(users);
//    }
    @GetMapping
    List<Request> all(){
        return repository.findAll();
    }
    @GetMapping("/{id}")
    Optional<Request> single(@PathVariable Long id){
        Optional<Request> uno = repository.findById(id);
        if (uno.isPresent()){
            return uno;
        }
        throw new NotFoundException("No Request found with id: "+id);
    }
    @PostMapping
    Request add(@RequestBody Request request){
        return repository.save(request);
    }
    @PutMapping
    Request update(@RequestBody Request request){
        return repository.save(request);
    }
    @DeleteMapping("/{id}")
    String remove(@PathVariable Long id){
        repository.deleteById(id);
        return "Item deleted successfully";
    }
}
