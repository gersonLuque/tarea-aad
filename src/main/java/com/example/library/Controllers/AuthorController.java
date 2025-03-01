package com.example.library.Controllers;

import com.example.library.models.Author;
import com.example.library.models.Book;
import com.example.library.services.interfaces.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gestionar autores.
 */
@Slf4j
@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private IAuthorService authorService;

    /**
     * Obtiene todos los autores.
     *
     * @return lista de autores
     */
    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        log.info("Obteniendo todos los autores");
        return ResponseEntity.ok(authorService.findAll());
    }

    /**
     * Obtiene un autor por su ID.
     *
     * @param id identificador del autor
     * @return autor si se encuentra, 404 si no
     */
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        log.debug("Buscando autor con id: {}", id);
        Optional<Author> author = authorService.findById(id);
        if (author.isPresent()) {
            log.info("Autor encontrado con id: {}", id);
            return ResponseEntity.ok(author.get());
        } else {
            log.warn("No se encontro autor con id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Obtiene un autor por su nombre.
     *
     * @param name nombre del autor
     * @return autor si se encuentra, 404 si no
     */
    @GetMapping(params = "name")
    public ResponseEntity<Author> getAuthorsByName(@RequestParam String name) {
        log.debug("Buscando autor con nombre: {}", name);
        Optional<Author> author = authorService.findByName(name);
        if (author.isPresent()) {
            log.info("Autor encontrado con nombre: {}", name);
            return ResponseEntity.ok(author.get());
        } else {
            log.warn("No se encontro autor con nombre: {}", name);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina un autor por su ID.
     *
     * @param id ID del autor a eliminar
     * @return ResponseEntity con el autor eliminado o un estado not found si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Author> deleteAuthorById(@PathVariable Long id) {
        log.info("Iniciando la eliminacion del autor con ID: {}", id);
        Optional<Author> author = authorService.findById(id);

        if (author.isPresent()) {
            authorService.deleteById(id);
            log.info("Autor con ID: {} eliminado exitosamente", id);
            return ResponseEntity.ok(author.get());
        } else {
            log.warn("No se encontro un autor con ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}