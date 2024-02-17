package com.example.examination2.presentation.response;

import com.example.examination2.domain.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookResponseTest {
    @Test
    void 任意のBookからBookResponseを作成できる場合() {
        Book book =  new Book("123", "テスト駆動開発", "Kent Beck", "オーム社", 3080);

        BookResponse expected = new BookResponse("123", "テスト駆動開発", "Kent Beck", "オーム社", 3080);

        BookResponse actual = BookResponse.createResponce(book);

        assertEquals(expected, actual);
    }
}
