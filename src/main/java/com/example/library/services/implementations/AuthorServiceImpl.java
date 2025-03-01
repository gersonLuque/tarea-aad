package com.example.library.services.implementations;

import com.example.library.models.Author;
import com.example.library.repository.AuthorRepository;
import com.example.library.services.interfaces.IAuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio que implementa las operaciones para gestionar los autores.
 * Esta clase proporciona metodos para obtener todos los autores, buscar autores por ID o nombre,
 * y eliminar un autor por su ID.
 */
@Slf4j
@Service
public class AuthorServiceImpl implements IAuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    /**
     * Recupera todos los autores de la base de datos.
     *
     * @return Lista de autores
     */
    @Override
    public List<Author> findAll() {
        List<Author> authors = authorRepository.findAll();
        log.info("Recuperados {} autores", authors.size());
        return authors;
    }

    /**
     * Busca un autor por su ID.
     *
     * @param id ID del autor a buscar
     * @return Optional de Autor si se encuentra, o un Optional vacío si no
     */
    @Override
    public Optional<Author> findById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        return author;
    }

    /**
     * Busca un autor por su nombre.
     *
     * @param name Nombre del autor a buscar
     * @return Optional de Autor si se encuentra, o un Optional vacío si no
     */
    @Override
    public Optional<Author> findByName(String name) {
        Optional<Author> author = authorRepository.findByName(name);
        return author;
    }

    /**
     * Elimina un autor por su ID.
     *
     * @param id ID del autor a eliminar
     * @return Optional de Autor si se encontraba y fue eliminado, o un Optional vacío si no
     */
    @Override
    public Optional<Author> deleteById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            authorRepository.deleteById(id);
        } else {
            log.warn("Autor con ID: {} no encontrado para eliminar", id);
        }
        return author;
    }
}

