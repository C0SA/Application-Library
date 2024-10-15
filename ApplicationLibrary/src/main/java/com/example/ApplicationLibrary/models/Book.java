package com.example.ApplicationLibrary.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "Book title is required")
    private String title;

    @Column(nullable = false)
    @NotEmpty(message = "Book author is required")
    private String author;

    @Column(nullable = false)
    @NotNull(message = "Book publication year is required")
    private Integer publication_year;


    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Book isbn is required")
    private String isbn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.AVAILABLE;

    public enum Status {
        AVAILABLE,
        UNAVAILABLE,
        CHECKED_OUT


    }
}
