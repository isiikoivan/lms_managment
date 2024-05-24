package com.example.lms_system.roles;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {
    private final RoleRepository roleRepository;
    @GetMapping
    List<Roles> all(){
        return roleRepository.findAll();
    }
    @GetMapping("/{id}")
    Optional<Roles> single(@PathVariable Long id){
        return roleRepository.findById(id);
    }
    @PostMapping
    Roles addRole(@RequestBody Roles roles){
        return roleRepository.save(roles);
    }
    @PostMapping("/many")
    ResponseEntity<List<Roles>> addMany(@RequestBody List<Roles> roles){
        List<Roles> role = roleRepository.saveAll(roles);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
    @PutMapping
    Roles update(@RequestBody Roles roles){
        return roleRepository.save(roles);
    }
    @DeleteMapping("/{id}")
    void remove(@PathVariable Long id){
        roleRepository.deleteById(id);
    }
}
