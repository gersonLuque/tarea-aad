package com.example.library.services.interfaces;

import com.example.library.models.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Optional<Book> findByTitle(String title);
    List<Book> findByPublicationYear(int year);
    List<Book> findByAuthorId(Long authorId);
    Optional<Book> deleteById(Long id);
}
