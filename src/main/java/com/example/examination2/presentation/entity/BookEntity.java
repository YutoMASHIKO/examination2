package com.example.examination2.presentation.entity;

import com.example.examination2.domain.Book;

public record BookEntity(String id, String title, String author, String publisher, Integer price) {

    public Book convert() {
        return new Book(id, title, author, publisher, price);
    }

}
