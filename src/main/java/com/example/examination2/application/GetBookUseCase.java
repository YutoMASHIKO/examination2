package com.example.examination2.application;

import com.example.examination2.application.exception.BookNotFoundException;
import com.example.examination2.domain.Book;
import com.example.examination2.domain.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetBookUseCase {

    private final BookRepository bookRepository;

    public Book getBookById(String id) {
        return bookRepository.getBookById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }
}
