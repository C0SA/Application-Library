package com.example.ApplicationLibrary.repository;

import com.example.ApplicationLibrary.models.Book;
import com.example.ApplicationLibrary.models.Book.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitle(String title);
    Optional<Book> findByAuthor(String author);

    //4. point in pdf below

    List<Book> findByStatus(Status status);

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByCategories_Name(String categoryName);



    //For filtering with categoryName and status
    @Query("SELECT b FROM Book b JOIN b.categories c WHERE (:status IS NULL OR b.status = :status) AND (:categoryName IS NULL OR c.name = :categoryName)")
    List<Book> findByStatusAndCategoryName(@Param("status") Status status, @Param("categoryName") String categoryName);

}
