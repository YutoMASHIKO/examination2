package com.example.examination2.application.exception;

import lombok.Getter;

@Getter
public class BookNotFoundException extends RuntimeException {
    private final String id;

    public BookNotFoundException(String id) {
        super(id);
        this.id = id;
    }
}
