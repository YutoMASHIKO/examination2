package com.example.examination2.application;

import com.example.examination2.application.data.InsertBook;
import com.example.examination2.domain.Book;
import com.example.examination2.domain.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsertBookUseCase {
    private final BookRepository bookRepository;

    public Book insertBook(InsertBook insertBook) {
        return bookRepository.insertBook(insertBook.convert(bookRepository.getNextId()));
    }
}
