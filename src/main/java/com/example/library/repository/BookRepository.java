package com.example.library.repository;

import com.example.library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestionar las operaciones CRUD relacionadas con la entidad Book.
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Busca un libro por su titulo.
     *
     * @param title El titulo del libro a buscar.
     * @return Un Optional que contiene el libro si se encuentra, o un Optional vacio si no existe.
     */
    Optional<Book> findByTitle(String title);

    /**
     * Busca todos los libros publicados en un año especifico.
     *
     * @param year El anio de publicación de los libros a buscar.
     * @return Una lista de libros publicados en el año dado.
     */
    List<Book> findByPublicationYear(int year);

    /**
     * Busca todos los libros escritos por un autor especifico.
     *
     * @param authorId El ID del autor cuyos libros se desean obtener.
     * @return Una lista de libros escritos por el autor con el ID proporcionado.
     */
    List<Book> findByAuthor_Id(Long authorId);
}
