package com.application.bookmanagement.service.impl;

import com.application.bookmanagement.entity.Book;
import com.application.bookmanagement.repository.BookRepository;
import com.application.bookmanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    @Override
    public Page<Book> findAllBooksByPage(int page, int size, String sortBy, boolean ascending) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        return repository.findAll(PageRequest.of(page, size, sort));
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
