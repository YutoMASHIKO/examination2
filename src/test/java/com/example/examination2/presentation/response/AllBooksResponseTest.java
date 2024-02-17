package com.example.examination2.presentation.response;

import com.example.examination2.domain.Book;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AllBooksResponseTest {
    @Test
    void List化されたBooksからAllBooksResponseが作成できる() {
        List<Book> books = List.of(
                new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080),
                new Book("2", "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860)
        );

        AllBooksResponse expected = new AllBooksResponse(
                List.of(
                        new BookResponse("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080),
                        new BookResponse("2", "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860)
                )
        );

        AllBooksResponse actual = AllBooksResponse.createAllResponse(books);

        assertEquals(expected, actual);
    }
}
