package com.example.demo4.controller;

import com.example.demo4.domain.entity.Author;
import com.example.demo4.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService service;

    @GetMapping("/authors")
    public List<Author> list(){
        return service.listAll();
    }

    @GetMapping("/authors/{id}")
    public Author get(@PathVariable Integer id){
        return service.get(id);
    }
}
