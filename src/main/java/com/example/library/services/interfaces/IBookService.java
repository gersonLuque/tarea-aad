package com.example.library.services.interfaces;

import com.example.library.models.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IBookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Optional<Book> findByTitle(String title);
    List<Book> findByPublicationYear(int year);
    List<Book> findByAuthorId(Long authorId);
    Book save(Book newBook);
    Optional<Book> deleteById(Long id);
    Optional<Book> update(Long id, Map<String, Integer> publication_year);
}
