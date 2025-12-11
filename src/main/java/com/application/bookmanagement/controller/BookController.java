package com.application.bookmanagement.controller;

import com.application.bookmanagement.entity.Book;
import com.application.bookmanagement.logger.LogBuilder;
import com.application.bookmanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public void deleteBook(@PathVariable UUID id) {
        logBuilder.info(String.format("Deleting book by id %s", id));
        service.deleteBook(id);
    }

}
