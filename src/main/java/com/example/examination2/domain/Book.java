package com.example.examination2.domain;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public record Book(String id, String title, String author, String publisher, Integer price) {

    public Book {
        if (isNull(id)) {
            throw new IllegalArgumentException("IDがnullです。");
        }
        if (!isNumeric(id)) {
            throw new IllegalArgumentException("IDが数字ではありません。");
        }
    }
}
