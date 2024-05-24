package com.example.lms_system.address;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
@AllArgsConstructor
public class AddressController {
    private final AddressRepository repository;
    @GetMapping
    List<Address> all(){
        return repository.findAll();
    }
    @GetMapping("/{id}")
    Optional<Address> single(@PathVariable Long id){
        return repository.findById(id);
    }
    @PostMapping
    Address addOne(@RequestBody Address address){
        return repository.save(address);
    }
    @PutMapping
    Address update(@RequestBody Address address){
        return repository.save(address);
    }
    @DeleteMapping("/{id}")
    String remove(@PathVariable Long id){
        repository.deleteById(id);
        return "Successfully Deleted.";
    }
}
