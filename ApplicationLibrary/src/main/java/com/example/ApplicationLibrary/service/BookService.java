package com.example.ApplicationLibrary.service;

import com.example.ApplicationLibrary.models.Book;
import com.example.ApplicationLibrary.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TransactionService transactionService;

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


    // 5. point in pdf, borrowing and returning books


    //For borrowing book
    @Transactional
    public Book borrowBook(Long bookId) {

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        System.out.println("Current status before return: " + book.getStatus());

        book.setStatus(Book.Status.CHECKED_OUT);

        Book updatedBook = bookRepository.save(book);


        System.out.println("New status after return: " + updatedBook.getStatus());

        return bookRepository.save(book);
    }


    //For returning book
    @Transactional
    public Book returnBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        System.out.println("Current status before return: " + book.getStatus());

        book.setStatus(Book.Status.AVAILABLE);

        Book updatedBook = bookRepository.save(book);

        System.out.println("New status after return: " + updatedBook.getStatus());
        return updatedBook;
    }



}
