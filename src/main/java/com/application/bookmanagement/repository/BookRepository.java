package com.application.bookmanagement.repository;

import com.application.bookmanagement.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    @Query("SELECT book FROM Book book where book.bookTitle LIKE %:bookTitle%")
    Optional<List<Book>> findBookByTitleContaining(@Param("bookTitle") String bookTitle);

    @Query("SELECT book FROM Book book where book.bookAuthor LIKE %:bookAuthor%")
    Optional<List<Book>> findBookByAuthor(@Param("bookAuthor") String bookAuthor);
}

