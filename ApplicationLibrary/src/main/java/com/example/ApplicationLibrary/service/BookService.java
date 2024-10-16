package com.example.ApplicationLibrary.service;

import com.example.ApplicationLibrary.models.Book;
import com.example.ApplicationLibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Optional<Book> findById(long id){
        return bookRepository.findById(id);
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }

    public void deleteById(long id){
        bookRepository.deleteById(id);
    }

    public Optional<Book> findByTitle(String title){
        return bookRepository.findByTitle(title);
    }

    public Optional<Book> findByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }


    //4. point in pdf below

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }


    public List<Book> getBooksByStatus(Book.Status status) {
        return bookRepository.findByStatus(status);
    }

    public List<Book> searchBooksByName(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> getBooksByCategory(String categoryName) {
        return bookRepository.findByCategories_Name(categoryName);
    }

    //For filtering with categoryName and status

    public List<Book> getBooksByStatusAndCategory(Book.Status status, String categoryName) {
        return bookRepository.findByStatusAndCategoryName(status, categoryName);
    }




}
