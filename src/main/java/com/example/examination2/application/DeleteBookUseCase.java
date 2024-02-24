package com.example.examination2.application;

import com.example.examination2.domain.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteBookUseCase {
    private final BookRepository bookRepository;

    public void deleteBook(String id) {
        bookRepository.deleteBook(id);
    }
}
