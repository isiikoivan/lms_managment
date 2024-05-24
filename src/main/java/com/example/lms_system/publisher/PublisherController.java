package com.example.lms_system.publisher;

import com.example.lms_system.address.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publisher")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherRepository repository;
    @GetMapping
    List<Publisher> all(){
        return repository.findAll();
    }
    @GetMapping("/{id}")
    Optional<Publisher> single(@PathVariable Long id){
        return repository.findById(id);
    }
    @PostMapping
    Publisher addOne(@RequestBody Publisher publisher){
        return repository.save(publisher);
    }
    @PutMapping
    Publisher update(@RequestBody Publisher publisher){
        return repository.save(publisher);
    }
    @DeleteMapping("/{id}")
    String remove(@PathVariable Long id){
        repository.deleteById(id);
        return "Successfully Deleted.";
    }
}
