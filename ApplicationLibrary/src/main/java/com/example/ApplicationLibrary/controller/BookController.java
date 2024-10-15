package com.example.ApplicationLibrary.controller;


import com.example.ApplicationLibrary.models.Book;
import com.example.ApplicationLibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> book =bookService.findAll();

        return ResponseEntity.ok(book);
    }

  /*  @GetMapping("{title}")
    public ResponseEntity<Book> getBookByTitle(@PathVariable String title){
        Optional<Book> book = bookService.findByTitle(title);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
*/
    @GetMapping("{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Optional<Book> book = bookService.findById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book savedBook=bookService.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook){
        Optional<Book> existingBook = bookService.findById(id);
        if(existingBook.isPresent()){
            updatedBook.setId(id);
            Book savedBook=bookService.save(updatedBook);
            return ResponseEntity.ok(savedBook);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        if(bookService.findById(id).isPresent()){
            bookService.deleteById(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }

}
