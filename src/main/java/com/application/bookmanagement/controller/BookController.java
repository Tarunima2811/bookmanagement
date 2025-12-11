package com.application.bookmanagement.controller;

import com.application.bookmanagement.entity.Book;
import com.application.bookmanagement.logger.LogBuilder;
import com.application.bookmanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/bookmanagement")
public class BookController {

    @Autowired
    private BookService service;

    @Autowired
    LogBuilder logBuilder;

    @GetMapping(path = "/all")
    public List<Book> findAllBooks() {
        logBuilder.info("Finding all books in the system");
        return service.findAllBooks();
    }

    @GetMapping(path = "book/{id}")
    public Optional<Book> findBookById(@PathVariable UUID id) {
        logBuilder.info(String.format("Finding book by id %s", id));
        return service.findBookById(id);
    }

    @PostMapping(path = "addOrUpdateBook")
    public Book addorUpdateBook(@RequestBody Book book) {
        logBuilder.info(String.format("Adding/Updating book: %s", book.getBookTitle()));
        return service.addOrUpdateBook(book);
    }

    @DeleteMapping(path = "delete/{id}")
    public void deleteBook(@PathVariable UUID id) {
        logBuilder.info(String.format("Deleting book by id %s", id));
        service.deleteBook(id);
    }

}
