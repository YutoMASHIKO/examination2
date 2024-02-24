package com.example.examination2.application.data;

import static java.util.Objects.nonNull;

import com.example.examination2.domain.Book;

public record UpdateBookData(String id, String title, String author, String publisher, Integer price)  {

    public Book convert(Book book) {
        String updatedTitle = book.title();
        if (nonNull(title)) {
            updatedTitle = title;
        }

        String updatedAuthor = book.author();
        if (nonNull(author)) {
            updatedAuthor = author;
        }

        String updatedPublisher = book.publisher();
        if (nonNull(publisher)) {
            updatedPublisher = publisher;
        }

        Integer updatedPrice = book.price();
        if (nonNull(price)) {
            updatedPrice = price;
        }

        return new Book(book.id(), updatedTitle, updatedAuthor, updatedPublisher, updatedPrice);
    }
}
