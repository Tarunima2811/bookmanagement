package com.application.bookmanagement.service;

import com.application.bookmanagement.entity.Book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService {

    List<Book> findAllBooks();

    Optional<Book> findBookById(UUID id);

    Book addOrUpdateBook(Book book);

    void deleteBook(UUID id);
}
