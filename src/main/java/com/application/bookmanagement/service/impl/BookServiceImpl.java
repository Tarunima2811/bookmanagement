package com.application.bookmanagement.service.impl;

import com.application.bookmanagement.entity.Book;
import com.application.bookmanagement.repository.BookRepository;
import com.application.bookmanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    @Override
    public List<Book> findAllBooks() {
        return repository.findAll();
    }

    @Override
    public Optional<Book> findBookById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Book addOrUpdateBook(Book book) {
        return repository.save(book);
    }

    @Override
    public void deleteBook(UUID id) {
        repository.deleteById(id);
    }
}
