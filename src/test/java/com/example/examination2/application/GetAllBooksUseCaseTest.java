package com.example.examination2.application;

import com.example.examination2.domain.Book;
import com.example.examination2.domain.repository.BookRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GetAllBooksUseCaseTest {
    @InjectMocks
    GetAllBooksUseCase sut;

    @Mock
    BookRepository bookRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void 全ての本の情報を取得できる場合() {
        when(bookRepository.getAllBooks())
                .thenReturn(
                        List.of(
                                new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080),
                                new Book("2", "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860),
                                new Book("3", "エクストリームプログラミング", "Kent Beck", "オーム社", 2420),
                                new Book("4", "Clean Agile", "Robert C. Martin", "ドワンゴ", 2640)
                        )
                );

        List<Book> expected = List.of(
                new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080),
                new Book("2", "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860),
                new Book("3", "エクストリームプログラミング", "Kent Beck", "オーム社", 2420),
                new Book("4", "Clean Agile", "Robert C. Martin", "ドワンゴ", 2640)
        );

        List<Book> actual = sut.getAllBooks();

        assertEquals(expected, actual);
    }
}
