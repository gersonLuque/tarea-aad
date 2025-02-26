package com.example.library.Controllers;

import com.example.library.models.Author;
import com.example.library.models.Book;
import com.example.library.services.interfaces.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final IBookService iBookService;

    @Autowired
    public BookController(IBookService iBookService) {
        this.iBookService = iBookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(iBookService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Optional<Book> book = iBookService.findById(id);
        if (book.isPresent())
            return ResponseEntity.ok(book.get());
        else
            return ResponseEntity.notFound().build();
    }
    @GetMapping(params = "authorId")
    public List<Book> getBooksByAuthor(@RequestParam Long authorId) {
        return iBookService.findByAuthorId(authorId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteAuthorById(@PathVariable Long id){
        Optional<Book> book = iBookService.findById(id);
        return iBookService.deleteById(id).isPresent() ? ResponseEntity.ok(book.get()) : ResponseEntity.notFound().build();
    }

}
