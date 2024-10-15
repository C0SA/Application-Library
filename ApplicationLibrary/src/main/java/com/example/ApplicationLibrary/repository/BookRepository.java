package com.example.ApplicationLibrary.repository;

import com.example.ApplicationLibrary.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitle(String title);
    Optional<Book> findByAuthor(String author);

}
