package com.example.library.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
 * Representa una entidad de autor en la base de datos.
 * Un autor tiene un ID, un nombre unico y una lista de libros asociados.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "authors")
public class Author {

    /**
     * ID unico del autor. Se genera automaticamente mediante la estrategia de identidad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del autor. Este campo debe ser unico en la base de datos.
     */
    @Column(unique = true)
    private String name;

    /**
     * La anotacion @JsonIgnore evita la recursion infinita en JSON.
     */
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Book> books = new ArrayList<>();
}
