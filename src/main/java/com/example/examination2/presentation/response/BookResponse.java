package com.example.examination2.presentation.response;

import com.example.examination2.domain.Book;

public record BookResponse(String id, String title, String author, String publisher, Integer price) {
    public static BookResponse createResponse(Book book) {
        return new BookResponse(
                book.id(),
                book.title(),
                book.author(),
                book.publisher(),
                book.price()
        );
    }
}
