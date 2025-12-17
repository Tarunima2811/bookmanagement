package com.application.bookmanagement.controller;

import com.application.bookmanagement.entity.Book;
import com.application.bookmanagement.logger.LogBuilder;
import com.application.bookmanagement.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @InjectMocks
    private BookController controller;

    @Mock
    private BookService service;

    @Mock
    private LogBuilder logBuilder;

    Book book1 = Book.builder().id(UUID.randomUUID()).bookTitle("Stone").build();
    Book book2 = Book.builder().id(UUID.randomUUID()).bookTitle("Chamber").build();
    Book book3 = Book.builder().id(UUID.randomUUID()).build();


    @Test
    void testFindBookById() {
        when(service.findBookById(any(UUID.class))).thenReturn(Optional.ofNullable(book1));
        assertEquals(controller.findBookById(UUID.randomUUID()), ResponseEntity.ok(book1));
    }

    @Test
    void testFindBookContainingTitle() {
        List<Book> bookList = Arrays.asList(book1, book2);
        when(service.findBookByTitleContaining(anyString())).thenReturn(bookList);
        assertEquals(controller.findBookContainingTitle(anyString()), ResponseEntity.ok(bookList));
    }

    @Test
    void testFindBookContainingTitleWhenNoBookFound() {
        when(service.findBookByTitleContaining(anyString())).thenReturn(new ArrayList<>());
        assertEquals(controller.findBookContainingTitle(anyString()), ResponseEntity.noContent().build());
    }

    @Test
    void testAddOrUpdateBookWhenMandatoryFieldIsMissing() {
        assertEquals(controller.addOrUpdateBook(book3), ResponseEntity.badRequest().body("Missing mandatory field: BookTitle"));
    }

    @Test
    void testDeleteBook() {
        when(service.findBookById(any(UUID.class))).thenReturn(Optional.of(book1));
        controller.deleteBook(UUID.randomUUID());
        verify(service, times(1)).deleteBook(any(UUID.class));
    }

    @Test
    void testDeleteBookWhenIdIsNotFound() {
        when(service.findBookById(any(UUID.class))).thenReturn(Optional.empty());
        controller.deleteBook(UUID.randomUUID());
        verify(service, times(0)).deleteBook(any(UUID.class));
    }


}
