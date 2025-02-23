package com.example.library.Controllers;

import com.example.library.models.Author;
import com.example.library.models.Book;
import com.example.library.services.interfaces.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    private IAuthorService authorService;

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok(authorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Optional<Author> author = authorService.findById(id);
        if (author.isPresent())
            return ResponseEntity.ok(author.get());
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping(params = "name")
    public ResponseEntity<Author> getAuthorsByName(@RequestParam String name) {
        Optional<Author> author = authorService.findByName(name);
        if (author.isPresent())
            return ResponseEntity.ok(author.get());
        else
            return ResponseEntity.notFound().build();
    }
}