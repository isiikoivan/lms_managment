package com.example.lms_system.contact;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactController {
    private final ContactRepository repository;
    @GetMapping
    List<Contact> all(){
        return repository.findAll();
    }
    @GetMapping("/{id}")
    Optional<Contact> single(@PathVariable Long id){
        return repository.findById(id);
    }
    @PostMapping
    Contact addOne(@RequestBody Contact contact){
        return repository.save(contact);
    }
    @PutMapping
    Contact update(@RequestBody Contact contact){
        return repository.save(contact);
    }
    @DeleteMapping("/{id}")
    String remove(@PathVariable Long id){
        repository.deleteById(id);
        return "Successfully Deleted.";
    }

}
