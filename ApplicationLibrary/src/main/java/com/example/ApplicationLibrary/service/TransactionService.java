package com.example.ApplicationLibrary.service;

import com.example.ApplicationLibrary.models.Book;
import com.example.ApplicationLibrary.models.Transaction;
import com.example.ApplicationLibrary.repository.BookRepository;
import com.example.ApplicationLibrary.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    public Transaction recordTransaction(String username,Long bookId,String title, String operation){
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        Transaction transaction = new Transaction();

        transaction.setUsername(username);
        transaction.setBookId(bookId);
        transaction.setTitle(title);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setTransactionType(operation);
        transaction.setBookStatus(book.getStatus().name());

        return transactionRepository.save(transaction);

    }
}
