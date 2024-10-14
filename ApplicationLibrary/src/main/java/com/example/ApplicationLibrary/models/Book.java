package com.example.ApplicationLibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private Integer publication_year;
    private String isbn;
    public enum status {
        AVAILABLE,  // should be uppercase
        UNAVAILABLE,
        CHECKED_OUT
        // add other statuses as needed
    }


}
