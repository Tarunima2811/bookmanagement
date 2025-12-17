package com.application.bookmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Book")
@SQLDelete(sql = "UPDATE Book SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;
}
