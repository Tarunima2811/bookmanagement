package com.application.bookmanagement.service;

import com.application.bookmanagement.entity.Book;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService {

    Page<Book> findAllBooksByPage(int page, int size, String sortBy, boolean ascending);

    Optional<Book> findBookById(UUID id);

    Optional<List<Book>> findBookByTitleContaining(String bookTitle);

    Optional<List<Book>> findBookByAuthor(String bookAuthor);

    Book addOrUpdateBook(Book book);

    void deleteBook(UUID id);
}
