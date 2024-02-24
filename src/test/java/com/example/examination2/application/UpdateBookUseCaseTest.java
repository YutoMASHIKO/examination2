package com.example.examination2.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.example.examination2.application.data.UpdateBookData;
import com.example.examination2.application.exception.BookNotFoundException;
import com.example.examination2.domain.Book;
import com.example.examination2.domain.repository.BookRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UpdateBookUseCaseTest {

    @InjectMocks
    UpdateBookUseCase sut;

    @Mock
    BookRepository bookRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void 本の更新を行う場合() {
        when(bookRepository.getBookById("1"))
                .thenReturn(Optional.of(new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080)));

        UpdateBookData updateBookData = new UpdateBookData("1", "テスト駆動開発", "Uncle Bob", "オーム社", 3080);

        assertDoesNotThrow(() -> sut.updateBook(updateBookData));
    }

    @Test
    void 更新先の本が存在しない場合() {
        when(bookRepository.getBookById("99")).thenReturn(Optional.empty());

        UpdateBookData updateBookData = new UpdateBookData("99", "テスト駆動開発", "Uncle Bob", "オーム社", 3080);

        assertThrows(BookNotFoundException.class, () -> sut.updateBook(updateBookData));
    }
}
