package com.example.library.services.implementations;

import com.example.library.models.Author;
import com.example.library.repository.AuthorRepository;
import com.example.library.services.interfaces.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AuthorServiceImpl implements IAuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> findByName(String name) {
        return authorRepository.findByName(name);
    }
}
