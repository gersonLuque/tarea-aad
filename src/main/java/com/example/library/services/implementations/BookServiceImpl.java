package com.example.library.services.implementations;

import com.example.library.models.Author;
import com.example.library.models.Book;
import com.example.library.repository.BookRepository;
import com.example.library.services.interfaces.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements IBookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public List<Book> findByPublicationYear(int year) {
        return bookRepository.findByPublicationYear(year);
    }

    @Override
    public List<Book> findByAuthorId(Long authorId) {
        return bookRepository.findByAuthor_Id(authorId);
    }

    @Override
    public Book save(Book newBook){

        if(newBook.getAuthor() != null)
            newBook.getAuthor().getBooks().add(newBook);

        return bookRepository.save(newBook);
    }

    @Override
    public Optional<Book> deleteById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent())
            bookRepository.deleteById(id);
        return book;
    }

}
