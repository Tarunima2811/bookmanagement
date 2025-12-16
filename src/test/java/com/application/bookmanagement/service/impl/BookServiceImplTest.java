package com.application.bookmanagement.service.impl;

import com.application.bookmanagement.entity.Book;
import com.application.bookmanagement.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Test
    void shouldSortBooksByTitleAscending() {

        Page<Book> page = repository.findAll(PageRequest.of(0, 3, Sort.by("bookTitle").ascending()));

        assertEquals(3, page.getContent().size());
        assertEquals("Anne of Green Gables", page.getContent().get(0).getBookTitle());
        assertEquals("Arabian Nights", page.getContent().get(1).getBookTitle());
    }
}