package com.example.examination2.infrastructure.entity;

import com.example.examination2.domain.Book;

public record BookEntity(Integer id, String title, String author, String publisher, Integer price) {

    public Book convert() {
        return new Book(String.valueOf(id), title, author, publisher, price);
    }

}
