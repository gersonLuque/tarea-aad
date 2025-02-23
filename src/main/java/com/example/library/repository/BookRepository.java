package com.example.library.repository;

import com.example.library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findByTitle(String title);
    List<Book> findByPublicationYear(int year);
    List<Book> findByAuthor_Id(Long authorId);
}
