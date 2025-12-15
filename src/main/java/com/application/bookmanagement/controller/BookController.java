package com.application.bookmanagement.controller;

import com.application.bookmanagement.entity.Book;
import com.application.bookmanagement.logger.LogBuilder;
import com.application.bookmanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(path = "all")
    public Page<Book> findAllBooksByPage(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "5") int size,
                                   @RequestParam(defaultValue = "bookTitle") String sortBy,
                                   @RequestParam(defaultValue = "true") boolean ascending) {
        logBuilder.info("Finding all books in the system");
        return service.findAllBooksByPage(page, size, sortBy, ascending);
    }

    @GetMapping(path = "book/{id}")
    public ResponseEntity<?> findBookById(@PathVariable UUID id) {
        logBuilder.info(String.format("Finding book by id %s", id));
        Optional<Book> book = service.findBookById(id);
        if (book.isPresent())
            return ResponseEntity.ok(book.get());
        else
            return ResponseEntity.notFound().build();

    }

    @GetMapping(path = "bookByTitle")
    public ResponseEntity<?> findBookContainingTitle(@RequestParam String bookTitle) {
        logBuilder.info(String.format("Finding books containing in title: %s", bookTitle));
        Optional<List<Book>> bookList = service.findBookByTitleContaining(bookTitle);
        if (bookList.isPresent())
            return ResponseEntity.ok(bookList.get());
        else
            return ResponseEntity.notFound().build();

    }

    @GetMapping(path = "bookByAuthor")
    public ResponseEntity<?> findBookByAuthor(@RequestParam String bookAuthor) {
        logBuilder.info(String.format("Finding books written by: %s", bookAuthor));
        Optional<List<Book>> bookList = service.findBookByAuthor(bookAuthor);
        if (bookList.isPresent())
            return ResponseEntity.ok(bookList.get());
        else
            return ResponseEntity.notFound().build();

    }

    @PostMapping(path = "addOrUpdateBook")
    public ResponseEntity<?> addOrUpdateBook(@RequestBody Book book) {
        logBuilder.info(String.format("Adding/Updating book: %s", book.getBookTitle()));
        if (null == book.getBookTitle()) {
            logBuilder.error("Missing mandatory field: BookTitle");
            return ResponseEntity.badRequest().body("Missing mandatory field: BookTitle");
        }
        return ResponseEntity.ok(service.addOrUpdateBook(book));
    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable UUID id) {
        if (service.findBookById(id).isPresent()) {
            logBuilder.info(String.format("Deleting book by id %s", id));
            service.deleteBook(id);
            return ResponseEntity.noContent().build();
        } else {
            logBuilder.info(String.format("Book with id not found: %s", id));
            return ResponseEntity.notFound().build();
        }

    }
}
