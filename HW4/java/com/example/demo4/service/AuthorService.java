package com.example.demo4.service;

import com.example.demo4.domain.entity.Author;
import com.example.demo4.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AuthorService {

    @Autowired
    private AuthorRepository repo;

    public List<Author> listAll(){
        return repo.findAll();
    }

    public void save(Author author){
        repo.save(author);
    }

    public Author get(Integer id){
        return repo.findById(id).get();
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }


}
