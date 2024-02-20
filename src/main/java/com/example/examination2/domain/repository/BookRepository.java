package com.example.examination2.domain.repository;

import com.example.examination2.domain.Book;
import java.util.List;

public interface BookRepository {

    List<Book> getAllBooks();

    Book getBookById(String id);

}
