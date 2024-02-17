package com.example.examination2.domain;

import static java.util.Objects.isNull;

public record Book(String id, String title, String author, String publisher, Integer price) {

    public Book {
        if (isNull(id)) {
            throw new IllegalArgumentException("本IDがnullです。");
        }
    }
}
