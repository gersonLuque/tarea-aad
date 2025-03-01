package com.example.library.repository;

import com.example.library.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repositorio para gestionar las operaciones CRUD relacionadas con la entidad Author.
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {

    /**
     * Busca un autor por su nombre.
     *
     * @param name El nombre del autor a buscar.
     * @return Un Optional que contiene el autor si se encuentra.
     */
    Optional<Author> findByName(String name);
}
