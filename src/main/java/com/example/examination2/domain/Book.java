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
        if (Long.parseLong(id) <= 0L || Long.parseLong(id) > 9999999999L) {
            throw new IllegalArgumentException("IDは1~9999999999でなくてはいけません。");
        }

        if (isNull(title)) {
            throw new IllegalArgumentException("タイトルがnullです。");
        }
        if (title.length() > 100) {
            throw new IllegalArgumentException("タイトルは100文字以下でなくてはいけません。");
        }

        if (isNull(author)) {
            throw new IllegalArgumentException("著者がnullです。");
        }
        if (author.length() > 100) {
            throw new IllegalArgumentException("著者は100文字以下でなくてはいけません。");
        }
    }
}
