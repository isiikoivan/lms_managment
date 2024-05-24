package com.example.lms_system.author;

import com.example.lms_system.address.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {
    private final AuthorRepository repository;
    @GetMapping
    List<Author> all(){
        return repository.findAll();
    }
    @GetMapping("/{id}")
    Optional<Author> single(@PathVariable Long id){
        return repository.findById(id);
    }
    @PostMapping
    Author addOne(@RequestBody Author author){
        return repository.save(author);
    }
    @PutMapping
    Author update(@RequestBody Author author){
        return repository.save(author);
    }
    @DeleteMapping("/{id}")
    String remove(@PathVariable Long id){
        repository.deleteById(id);
        return "Successfully Deleted.";
    }
}
