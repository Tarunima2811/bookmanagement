package com.application.bookmanagement.service;

import com.application.bookmanagement.entity.Book;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface BookService {

    Page<Book> findAllBooksByPage(int page, int size, String sortBy, boolean ascending);

    Optional<Book> findBookById(UUID id);

    Book addOrUpdateBook(Book book);

    void deleteBook(UUID id);
}
