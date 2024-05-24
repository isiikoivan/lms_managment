package com.example.lms_system.language;

import com.example.lms_system.address.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/language")
@RequiredArgsConstructor
public class LanguageController {
    private final LanguageRepository repository;
    @GetMapping
    List<Language> all(){
        return repository.findAll();
    }
    @GetMapping("/{id}")
    Optional<Language> single(@PathVariable Long id){
        return repository.findById(id);
    }
    @PostMapping
    Language addOne(@RequestBody Language language){
        return repository.save(language);
    }
    @PutMapping
    Language update(@RequestBody Language language){
        return repository.save(language);
    }
    @DeleteMapping("/{id}")
    String remove(@PathVariable Long id){
        repository.deleteById(id);
        return "Successfully Deleted.";
    }
}
