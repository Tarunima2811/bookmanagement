package com.application.bookmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Book")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String bookTitle;

    @Column(name = "author")
    private String bookAuthor;

    @Column(name = "language")
    private String bookLanguage;

    @Column(name = "genre")
    private String bookGenre;

    @Column(name = "publisher")
    private String bookPublisher;

    @Column(name = "publishDate")
    private Date bookPublishDate;
}
