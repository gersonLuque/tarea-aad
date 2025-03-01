package com.example.library.models;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

import lombok.*;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * Entidad que representa un libro en la base de datos.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book implements Serializable {

    /**
     * Identificador unico del libro.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Titulo del libro.
     */
    @Column(unique = true)
    private String title;

    /**
     * Anio de publicacion del libro.
     */
    @Column(name = "publication_year")
    private int publicationYear;

    /**
     * Autor del libro.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;
}

