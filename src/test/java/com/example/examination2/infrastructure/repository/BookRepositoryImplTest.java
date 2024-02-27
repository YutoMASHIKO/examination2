package com.example.examination2.infrastructure.repository;

import com.example.examination2.domain.Book;
import com.example.examination2.infrastructure.entity.BookEntity;
import com.example.examination2.infrastructure.exception.SqlExecutionException;
import com.example.examination2.infrastructure.mapper.BookMapper;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
                                    new BookEntity(1, "テスト駆動開発", "Kent Beck", "オーム社", 3080),
                                    new BookEntity(2, "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860),
                                    new BookEntity(3, "エクストリームプログラミング", "Kent Beck", "オーム社", 2420),
                                    new BookEntity(4, "Clean Agile", "Robert C. Martin", "ドワンゴ", 2640)
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
                    .thenReturn(new BookEntity(1, "テスト駆動開発", "Kent Beck", "オーム社", 3080));

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

    @Test
    void 次の本Idを取得する場合() {
        when(bookMapper.getNextId()).thenReturn(5L);

        Long actual = sut.getNextId();

        assertEquals(5L, actual);
    }

    @Nested
    class 新規登録 {
        @Test
        void 本の新規登録に成功する場合() {
            when(bookMapper.insert(new BookEntity(4, "Clean Agile", "Robert C. Martin", "ドワンゴ", 2640)))
                    .thenReturn(1);

            Book expected = new Book("4", "Clean Agile", "Robert C. Martin", "ドワンゴ", 2640);

            Book actual = sut.insertBook(new Book("4", "Clean Agile", "Robert C. Martin", "ドワンゴ", 2640));

            assertEquals(expected, actual);
        }

        @Test
        void 本の新規登録に失敗する場合() {
            when(bookMapper.insert(new BookEntity(4, "Clean Agile", "Robert C. Martin", "ドワンゴ", 2640)))
                    .thenReturn(0);

            Book book = new Book("4", "Clean Agile", "Robert C. Martin", "ドワンゴ", 2640);

            assertThatThrownBy(() -> sut.insertBook(book))
                    .isInstanceOf(SqlExecutionException.class)
                    .hasMessage("SQLの実行に失敗しました");
        }

    }

    @Nested
    class 本の更新 {

        @Test
        void 成功する場合() {
            when(bookMapper.update(new BookEntity(1, "テスト駆動開発", "Uncle Bob", "オーム社", 3080)))
                    .thenReturn(1);

            Book book = new Book("1", "テスト駆動開発", "Uncle Bob", "オーム社", 3080);

            assertDoesNotThrow(() -> sut.updateBook(book));
        }

        @Test
        void 失敗する場合() {
            when(bookMapper.update(new BookEntity(1, "テスト駆動開発", "Uncle Bob", "オーム社", 3080)))
                    .thenReturn(0);

            Book book = new Book("1", "テスト駆動開発", "Uncle Bob", "オーム社", 3080);

            assertThatThrownBy(() -> sut.updateBook(book))
                    .isInstanceOf(SqlExecutionException.class)
                    .hasMessage("SQLの実行に失敗しました");
        }
    }

    @Nested
    class 削除 {
        @Test
        void 正常に行える場合() {
            when(bookMapper.delete(1)).thenReturn(1);

            assertDoesNotThrow(() -> sut.deleteBook("1"));
        }

        @Test
        void 異常が発生する場合() {
            when(bookMapper.delete(1)).thenReturn(0);

            assertThatThrownBy(() -> sut.deleteBook("1"))
                    .isInstanceOf(SqlExecutionException.class)
                    .hasMessage("SQLの実行に失敗しました");
        }
    }
}
