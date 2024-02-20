package com.example.examination2.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.example.examination2.domain.Book;
import com.example.examination2.domain.repository.BookRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class GetBookUseCaseTest {
    @InjectMocks
    GetBookUseCase sut;

    @Mock
    BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void IDによる個別の本を取得できる場合() {
        when(bookRepository.getBookById("1"))
                .thenReturn(Optional.of(new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080)));

        Book expected = new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080);

        Book actual = sut.getBookById("1");

        assertEquals(expected, actual);
    }

    @Test
    void IDによる個別の本が取得できない場合() {
        when(bookRepository.getBookById("99")).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> sut.getBookById("99"));
    }
}
