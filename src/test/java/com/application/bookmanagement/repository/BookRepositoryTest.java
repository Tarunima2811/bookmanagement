package com.application.bookmanagement.repository;

import com.application.bookmanagement.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Test
    void testFindBookByTitleContaining() {
        List<Book> bookList = repository.findBookByTitleContaining("Harry");
        assertNotNull(bookList);
        assertEquals(3, bookList.size());
    }

    @Test
    void testFindBookByAuthor() {
        List<Book> bookList = repository.findBookByAuthor("Enid");
        assertNotNull(bookList);
        assertEquals(2, bookList.size());
    }
}
