package com.application.bookmanagement.repository;

import com.application.bookmanagement.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Test
    void testFindBookByTitleContaining() {
        Optional<List<Book>> bookList = repository.findBookByTitleContaining("Harry");
        assertTrue(bookList.isPresent());
        assertEquals(3, bookList.get().size());
    }

    @Test
    void testFindBookByAuthor() {
        Optional<List<Book>> bookList = repository.findBookByAuthor("Enid");
        assertTrue(bookList.isPresent());
        assertEquals(2, bookList.get().size());
    }
}
