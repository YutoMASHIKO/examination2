package com.example.examination2.infrastructure.repository;

import com.example.examination2.domain.Book;
import com.example.examination2.infrastructure.entity.BookEntity;
import com.example.examination2.infrastructure.mapper.BookMapper;
import it.unibo.tuprolog.solve.stdlib.primitive.Op;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BookRepositoryImplTest {

    @InjectMocks
    BookRepositoryImpl sut;

    @Mock
    BookMapper bookMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class 全件取得 {
        @Test
        void 全件取得をする場合() {
            when(bookMapper.getAllBooks())
                    .thenReturn(
                            List.of(
                                    new BookEntity("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080),
                                    new BookEntity("2", "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860),
                                    new BookEntity("3", "エクストリームプログラミング", "Kent Beck", "オーム社", 2420),
                                    new BookEntity("4", "Clean Agile", "Robert C. Martin", "ドワンゴ", 2640)
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

        @Test
        void データが存在しないときに全件取得をする場合() {
            when(bookMapper.getAllBooks()).thenReturn(new ArrayList<>());

            List<Book> expected = new ArrayList<>();

            List<Book> actual = sut.getAllBooks();

            assertEquals(expected, actual);
        }
    }

    @Nested
    class ID検索 {
        @Test
        void IDによる検索を行う場合() {
            when(bookMapper.getBookById(1))
                    .thenReturn(new BookEntity("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080));

            Optional<Book> expected = Optional.of(new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080));

            Optional<Book> actual = sut.getBookById("1");

            assertEquals(expected, actual);
        }

        @Test
        void 存在しないIDで検索を行う場合() {
            when(bookMapper.getBookById(99)).thenReturn(null);

            Optional<Book> expected = Optional.empty();

            Optional<Book> actual = sut.getBookById("99");

            assertEquals(expected, actual);
        }
    }
}
