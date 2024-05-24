package com.example.lms_system.books;

import com.example.lms_system.exception.notfound.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {
    private final BookRepository repository;
    @GetMapping("/all/list")
    List<Books> all(){
        return repository.findAll();
    }
    @GetMapping("/all/sort/asc/{field}")
    List<Books> allSortedAsc(@PathVariable String field){
        return repository.findAll(Sort.by(Sort.Direction.ASC,field));
    }
    @GetMapping("/all/sort/desc/{field}")
    List<Books> allSortedDesc(@PathVariable String field){
        return repository.findAll(Sort.by(Sort.Direction.DESC,field));
    }
    @GetMapping("/all/paginate/{offset}/{size}")
    Page<Books> paginated(@PathVariable int offset, @PathVariable int size){
        return repository.findAll(PageRequest.of(offset,size));
    }
    @GetMapping("/all/{id}")
    Optional<Books> single(@PathVariable Long id){
        Optional<Books> book = repository.findById(id);
        if(book.isPresent()){
            return book;
        }
        throw new NotFoundException("No records found for user with id: "+id);
    }
    @GetMapping("/all/title/{title}")
    Optional<Books> byTitle(@PathVariable String title){
        Optional<Books> book = repository.findByTitleContainingIgnoreCase(title);
        if(book.isPresent()){
            return book;
        }
        throw new NotFoundException("No records found for Book with title: '"+title+"'");
    }
    @PostMapping("/librarian/add")
    Books addBk(@RequestBody Books book){
        return repository.save(book);
    }
    @PostMapping("/librarian/multiple")
    ResponseEntity<List<Books>> addMany(@RequestBody List<Books> books){
        List<Books> bks = repository.saveAll(books);
        return new ResponseEntity<>(bks, HttpStatus.OK);
    }
    @PutMapping("/librarian/edit")
    Books update(@RequestBody Books book){
        return repository.save(book);
    }
    @DeleteMapping("/librarian/remove/{id}")
    void remove(@PathVariable Long id){
        repository.deleteById(id);
    }
}
