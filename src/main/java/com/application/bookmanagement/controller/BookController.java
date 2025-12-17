package com.application.bookmanagement.controller;

import com.application.bookmanagement.entity.Book;
import com.application.bookmanagement.logger.LogBuilder;
import com.application.bookmanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            return ResponseEntity.noContent().build();

    }

    @GetMapping(path = "bookByTitle")
    public ResponseEntity<?> findBookContainingTitle(@RequestParam String bookTitle) {
        logBuilder.info(String.format("Finding books containing in title: %s", bookTitle));
        List<Book> bookList = service.findBookByTitleContaining(bookTitle);
        if (!bookList.isEmpty())
            return ResponseEntity.ok(bookList);
        else
            return ResponseEntity.noContent().build();

    }

    @GetMapping(path = "bookByAuthor")
    public ResponseEntity<?> findBookByAuthor(@RequestParam String bookAuthor) {
        logBuilder.info(String.format("Finding books written by: %s", bookAuthor));
        List<Book> bookList = service.findBookByAuthor(bookAuthor);
        if (!bookList.isEmpty())
            return ResponseEntity.ok(bookList);
        else
            return ResponseEntity.noContent().build();

    }

    @PostMapping("/search")
    public ResponseEntity<?> searchBooks(@RequestBody Book book) {
        logBuilder.info("Searching books with multiple parameters");
        List<Book> bookList = service.searchBooks(book);
        if (!bookList.isEmpty())
            return ResponseEntity.ok(bookList);
        else
            return ResponseEntity.noContent().build();
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
