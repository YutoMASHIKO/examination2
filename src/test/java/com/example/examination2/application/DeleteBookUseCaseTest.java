package com.example.examination2.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.example.examination2.application.exception.BookNotFoundException;
import com.example.examination2.domain.Book;
import com.example.examination2.domain.repository.BookRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class DeleteBookUseCaseTest {
    @InjectMocks
    DeleteBookUseCase sut;

    @Mock
    BookRepository bookRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void 削除を行う場合() {
        when(bookRepository.getBookById("1"))
                .thenReturn(Optional.of(new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080)));

        assertDoesNotThrow(() -> sut.deleteBook("1"));
    }

    @Test
    void 存在しないIDを指定した場合() {
        assertThrows(BookNotFoundException.class, () -> sut.deleteBook("0"));
    }

}
