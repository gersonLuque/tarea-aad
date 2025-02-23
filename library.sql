-- Active: 1710234488669@@127.0.0.1@3333@library
-- Crea la base de datos si no existe
-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS library;
USE library;

-- Crear la tabla de autores
CREATE TABLE IF NOT EXISTS authors (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Crear la tabla de libros
CREATE TABLE IF NOT EXISTS books (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    publication_year INT NOT NULL,
    author_id BIGINT,
    FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE
);

-- Insertar autores
INSERT INTO authors (name) VALUES 
    ('Gabriel García Márquez'),
    ('J.K. Rowling'),
    ('George Orwell'),
    ('Jane Austen'),
    ('J.R.R. Tolkien'),
    ('Agatha Christie'),
    ('Haruki Murakami'),
    ('Stephen King'),
    ('Isabel Allende'),
    ('Dan Brown');

INSERT INTO books (title, publication_year, author_id) VALUES 
    ('Cien años de soledad', 1967, 1),
    ('El amor en los tiempos del cólera', 1985, 1),
    
    ('Harry Potter y la piedra filosofal', 1997, 2),
    ('Harry Potter y la cámara secreta', 1998, 2),
    
    ('1984', 1949, 3),
    ('Rebelión en la granja', 1945, 3),
    
    ('Orgullo y prejuicio', 1813, 4),
    ('Emma', 1815, 4),
    
    ('El Señor de los Anillos', 1954, 5),
    ('El Hobbit', 1937, 5),
    
    ('Asesinato en el Orient Express', 1934, 6),
    ('Diez negritos', 1939, 6),
    
    ('Norwegian Wood', 1987, 7),
    ('Kafka en la orilla', 2002, 7),
    
    ('El resplandor', 1977, 8),
    ('It', 1986, 8),
    
    ('La casa de los espíritus', 1982, 9),
    
    ('El código Da Vinci', 2003, 10),
    ('Inferno', 2013, 10);