package com.example.lms_system.department;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentRepository repository;
    @GetMapping
    List<Department> all(){
        return repository.findAll();
    }
    @GetMapping("/{id}")
    Optional<Department> single(@PathVariable Long id){
        return repository.findById(id);
    }
    @GetMapping("name/{name}")
    Optional<Department> byName(@PathVariable String name){
        return repository.findByDepartmentName(name);
    }
    @PostMapping
    Department addOne(@RequestBody Department department){
        return repository.save(department);
    }
    @PutMapping
    Department update(@RequestBody Department address){
        return repository.save(address);
    }
    @DeleteMapping("/{id}")
    String remove(@PathVariable Long id){
        repository.deleteById(id);
        return "Successfully Deleted.";
    }
}
