package com.example.examination2.application.data;

import com.example.examination2.domain.Book;

public record InsertBook(String title, String author, String publisher, Integer price) {

    public Book convert(Long id) {
        return new Book(String.valueOf(id), title, author, publisher, price);
    }
}
