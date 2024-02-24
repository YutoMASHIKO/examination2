package com.example.examination2.application.data;

import static org.junit.jupiter.api.Assertions.*;

import com.example.examination2.domain.Book;
import org.junit.jupiter.api.Test;

class UpdateBookDataTest {

    @Test
    void すべての要素を更新する場合() {
        UpdateBookData sut = new UpdateBookData("1", "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860);
        Book originalBook = new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080);

        Book expected = new Book("1", "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860);

        Book actual = sut.convert(originalBook);

        assertEquals(expected, actual);
    }

}
