package com.example.examination2.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.example.examination2.application.data.InsertBookData;
import com.example.examination2.domain.Book;
import com.example.examination2.domain.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class InsertBookDataUseCaseTest {
    @InjectMocks
    InsertBookUseCase sut;

    @Mock
    BookRepository bookRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void 従業員の新規登録をする場合() {
        when(bookRepository.getNextId()).thenReturn(4L);

        when(bookRepository.insertBook(new Book("4", "Clean Agile", "Robert C. Martin", "ドワンゴ", 2640)))
                .thenReturn(new Book("4", "Clean Agile", "Robert C. Martin", "ドワンゴ", 2640));

        Book expected = new Book("4", "Clean Agile", "Robert C. Martin", "ドワンゴ", 2640);

        Book actual = sut.insertBook(new InsertBookData( "Clean Agile", "Robert C. Martin", "ドワンゴ", 2640));

        assertEquals(expected, actual);
    }
}
