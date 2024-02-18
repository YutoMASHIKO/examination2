package com.example.examination2.infrastructure.entity;

import com.example.examination2.domain.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookEntityTest {

    @Test
    void 正しくBookオブジェクトに変換できる場合() {
        BookEntity sut = new BookEntity("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080);

        Book expected = new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080);

        Book actual = sut.convert();

        assertEquals(expected, actual);
    }
}
