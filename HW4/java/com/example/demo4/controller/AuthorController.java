package com.example.demo4.controller;

import com.example.demo4.domain.entity.Author;
import com.example.demo4.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Repeatable;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService service;

    @GetMapping("/authors")
    public List<Author> list(){
        return service.listAll();
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<Author> get(@PathVariable Integer id){
        try {
            Author author = service.get(id);
            return new ResponseEntity<Author>(author, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/authors")
    public ResponseEntity<Author> add(@RequestBody Author author){
        service.save(author);
        return new ResponseEntity<>( HttpStatus.CREATED) ;
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<?> update(@RequestBody Author author,
                       @PathVariable Integer id){
        try{
            Author existAuthor = service.get(id);
            service.save(author);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/authors/{id}")
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }

}
