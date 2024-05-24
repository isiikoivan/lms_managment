package com.example.lms_system.edition;

import com.example.lms_system.address.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/edition")
@RequiredArgsConstructor
public class EditionController {
    private final EditionRepository repository;
    @GetMapping
    List<Edition> all(){
        return repository.findAll();
    }
    @GetMapping("/{id}")
    Optional<Edition> single(@PathVariable Long id){
        return repository.findById(id);
    }
    @PostMapping
    Edition addOne(@RequestBody Edition edition){
        return repository.save(edition);
    }
    @PutMapping
    Edition update(@RequestBody Edition edition){
        return repository.save(edition);
    }
    @DeleteMapping("/{id}")
    String remove(@PathVariable Long id){
        repository.deleteById(id);
        return "Successfully Deleted.";
    }
}
