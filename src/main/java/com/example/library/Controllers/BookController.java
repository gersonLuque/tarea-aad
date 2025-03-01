package com.example.library.Controllers;

import com.example.library.models.Author;
import com.example.library.models.Book;
import com.example.library.services.interfaces.IBookService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controlador REST para gestionar libros.
 */
@Slf4j
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final IBookService iBookService;

    /**
     * Constructor que inyecta el servicio de libros.
     * @param iBookService servicio de libros
     */
    public BookController(IBookService iBookService) {
        this.iBookService = iBookService;
    }

    /**
     * Obtiene todos los libros.
     * @return lista de libros
     */
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        log.info("Buscando todos los libros");
        return ResponseEntity.ok(iBookService.findAll());
    }

    /**
     * Metodo para obtener un libro por su titulo.
     *
     * @param title Titulo del libro que se desea buscar.
     * @return ResponseEntity con el libro encontrado, o una respuesta 404 si no se encuentra.
     */
    @GetMapping("/title/{title}")
    public ResponseEntity<Book> getBookByTitle(@PathVariable String title) {
        Optional<Book> book = iBookService.findByTitle(title);
        if (book.isPresent()) {
            log.info("Libro encontrado con id: {}", book.get().getId());
            return ResponseEntity.ok(book.get());
        } else {
            log.warn("Libro no encontrado con titulo: {}", title);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Obtiene un libro por su ID.
     * @param id identificador del libro
     * @return libro si se encuentra, 404 si no
     */
    @GetMapping("{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        log.debug("Mostrando libro con id : {}", id);
        Optional<Book> book = iBookService.findById(id);
        if (book.isPresent()) {
            log.info("Libro encontrado con id: {}", id);
            return ResponseEntity.ok(book.get());
        } else {
            log.warn("Libro no encontrado con id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Obtiene los libros de un autor por su ID.
     * @param authorId identificador del autor
     * @return lista de libros del autor
     */
    @GetMapping(params = "authorId")
    public List<Book> getBooksByAuthor(@RequestParam Long authorId) {
        log.debug("Mostrando libros por author id : {}", authorId);
        return iBookService.findByAuthorId(authorId);
    }

    /**
     * Guarda un nuevo libro.
     * @param newBook libro a guardar
     * @return libro guardado
     */
    @PostMapping
    public ResponseEntity<Book> save(@RequestBody Book newBook) {
        log.info("Guardando nuevo libro: {}", newBook);
        Book book = iBookService.save(newBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    /**
     * Actualiza el anio de publicacion de un libro.
     * @param id identificador del libro
     * @param publicationYear mapa con el anio de publicacion
     * @return libro actualizado si se encuentra, 404 si no
     */
    @PutMapping("/publicationYear/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Map<String, Integer> publicationYear) {
        log.debug("Updating publication year for book id: {}", id);
        Optional<Book> updatedBook = iBookService.update(id, publicationYear);
        if (updatedBook.isPresent()) {
            log.info("Updated book: {}", updatedBook.get());
            return ResponseEntity.ok(updatedBook.get());
        } else {
            log.warn("Book not found for update with id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina un libro por su ID.
     * @param id identificador del libro
     * @return libro eliminado si existe, 404 si no
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteAuthorById(@PathVariable Long id) {
        log.debug("Borrando libro con id: {}", id);
        Optional<Book> book = iBookService.findById(id);
        if (book.isPresent()) {
            iBookService.deleteById(id);
            log.info("Borrado libro con id: {}", id);
            return ResponseEntity.ok(book.get());
        } else {
            log.warn("Libro no encontrado para su borrado con id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}
