package com.example.ApplicationLibrary.controller;


import com.example.ApplicationLibrary.models.Book;
import com.example.ApplicationLibrary.models.Category;
import com.example.ApplicationLibrary.repository.BookRepository;
import com.example.ApplicationLibrary.repository.CategoryRepository;
import com.example.ApplicationLibrary.service.BookService;
import com.example.ApplicationLibrary.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    //Two AutoWired below are for adding categories to books

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TransactionService transactionService;

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/{bookId}/categories/{categoryId}")
    public ResponseEntity<Book> addCategoryToBook(@PathVariable Long bookId, @PathVariable Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (bookOptional.isPresent() && categoryOptional.isPresent()) {
            Book book = bookOptional.get();
            Category category = categoryOptional.get();

            book.getCategories().add(category);
            bookRepository.save(book);
            return ResponseEntity.ok(book);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> book = bookService.findAll();

        return ResponseEntity.ok(book);
    }

    /*  @GetMapping("{title}")
      public ResponseEntity<Book> getBookByTitle(@PathVariable String title){
          Optional<Book> book = bookService.findByTitle(title);
          return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
      }
  */
    @GetMapping("{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.findById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookService.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Optional<Book> existingBook = bookService.findById(id);
        if (existingBook.isPresent()) {
            updatedBook.setId(id);
            Book savedBook = bookService.save(updatedBook);
            return ResponseEntity.ok(savedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        if (bookService.findById(id).isPresent()) {
            bookService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }


    //4. point in pdf below


    @GetMapping("/status")
    public ResponseEntity<List<Book>> getBooksByStatus(@RequestParam Book.Status status) {
        List<Book> books = bookService.getBooksByStatus(status);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String title) {
        List<Book> books = bookService.searchBooksByName(title);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Book>> getBooksByCategory(@RequestParam String categoryName) {
        List<Book> books = bookService.getBooksByCategory(categoryName);
        return ResponseEntity.ok(books);
    }


    @GetMapping("/filter")
    public ResponseEntity<List<Book>> filterBooks(@RequestParam(required = false) Book.Status status, @RequestParam(required = false) String categoryName) {
        List<Book> filteredBooks = bookService.getBooksByStatusAndCategory(status, categoryName);
        return ResponseEntity.ok(filteredBooks);
    }


    //5. point in pdf borrowing and returning books
    @Secured({"ROLE_USER"})
    @PutMapping("/{id}/borrow")
    public ResponseEntity<Book> borrowBook(@PathVariable Long id,Authentication authentication) {
        try {
            String username = authentication.getName();


            Book borrowBook = bookService.borrowBook(id);
            String title=borrowBook.getTitle();

            transactionService.recordTransaction(username, id,title,"borrowed");
            return ResponseEntity.ok(borrowBook);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Secured({"ROLE_USER"})
    @PutMapping("/{id}/return")
    public ResponseEntity<Book> returnBook(@PathVariable Long id,Authentication authentication) {
        try {
            String username = authentication.getName();

            Book returndBook = bookService.returnBook(id);
            String title=returndBook.getTitle();
            transactionService.recordTransaction(username, id,title, "returned");

            return ResponseEntity.ok(returndBook);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    private Long extractUserIdFromAuth(Authentication authentication) {
        if (authentication.getPrincipal() instanceof org.springframework.security.oauth2.jwt.Jwt jwt) {
            System.out.println("JWT Claims: " + jwt.getClaims()); // Log all claims
            Long userId = jwt.getClaim("userId");
            System.out.println("Extracted userId: " + userId);
            return userId;
        } else {
            System.out.println("Principal is not of type Jwt.");
        }

        return null;
    }




}

