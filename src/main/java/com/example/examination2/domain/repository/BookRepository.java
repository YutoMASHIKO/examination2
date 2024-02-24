package com.example.examination2.domain.repository;

import com.example.examination2.domain.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<Book> getAllBooks();

    Optional<Book> getBookById(String id);

    Long getNextId();

    Book insertBook(Book book);

    void updateBook(Book book);

    void deleteBook(String id);
}
