package com.example.library.services.implementations;

import com.example.library.models.Author;
import com.example.library.models.Book;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.services.interfaces.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementacion del servicio para la gestion de libros.
 */
@Slf4j
@Service
public class BookServiceImpl implements IBookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Obtiene todos los libros disponibles.
     * @return lista de libros
     */
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    /**
     * Busca un libro por su ID.
     * @param id identificador del libro
     * @return libro encontrado, si existe
     */
    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    /**
     * Busca un libro por su titulo.
     * @param title titulo del libro
     * @return libro encontrado, si existe
     */
    @Override
    public Optional<Book> findByTitle(String title) {
        log.debug("Buscando libro con titulo: {}", title);
        return bookRepository.findByTitle(title);
    }

    /**
     * Busca libros por su año de publicacion.
     * @param year anio de publicacion
     * @return lista de libros publicados en ese año
     */
    @Override
    public List<Book> findByPublicationYear(int year) {
        log.debug("Buscando libros publicados en el anio: {}", year);
        return bookRepository.findByPublicationYear(year);
    }

    /**
     * Busca libros por el ID del autor.
     * @param authorId identificador del autor
     * @return lista de libros de ese autor
     */
    @Override
    public List<Book> findByAuthorId(Long authorId) {
        log.debug("Buscando libros del autor con id: {}", authorId);
        return bookRepository.findByAuthor_Id(authorId);
    }

    /**
     * Guarda un nuevo libro.
     * @param newBook libro a guardar
     * @return libro guardado
     */
    @Override
    public Book save(Book newBook) {
        log.info("Guardando nuevo libro: {}", newBook);
        if (newBook.getAuthor() != null) {
            newBook.getAuthor().getBooks().add(newBook);
        }
        return bookRepository.save(newBook);
    }

    /**
     * Elimina un libro por su ID.
     * @param id identificador del libro
     * @return libro eliminado, si existia
     */
    @Override
    public Optional<Book> deleteById(Long id) {
        log.debug("Eliminando libro con id: {}", id);
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(id);
            log.info("Libro eliminado con id: {}", id);
        } else {
            log.warn("No se encontro libro para eliminar con id: {}", id);
        }
        return book;
    }

    /**
     * Actualiza el año de publicacion de un libro.
     * @param id identificador del libro
     * @param publicationYear mapa con el nuevo año de publicacion
     * @return libro actualizado, si existe
     */
    @Override
    public Optional<Book> update(Long id, Map<String, Integer> publicationYear) {
        log.debug("Actualizando publicacion de libro con id: {}", id);
        if (!publicationYear.containsKey("publicationYear")) {
            log.warn("No se encontro el campo 'publicationYear' en la solicitud de actualizacion");
            return Optional.empty();
        }
        Optional<Book> bookToUpdate = bookRepository.findById(id);
        if (bookToUpdate.isPresent()) {
            Book book = bookToUpdate.get();
            book.setPublicationYear(publicationYear.get("publicationYear"));
            bookRepository.save(book);
            log.info("Libro actualizado con id: {}", id);
            return Optional.of(book);
        } else {
            log.warn("No se encontro libro para actualizar con id: {}", id);
            return Optional.empty();
        }
    }
}

