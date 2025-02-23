package com.example.library.services.interfaces;

import com.example.library.models.Author;

import java.util.List;
import java.util.Optional;

public interface IAuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);
    Optional<Author> findByName(String name);
}
