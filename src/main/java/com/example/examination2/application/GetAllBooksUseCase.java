package com.example.examination2.application;

import com.example.examination2.domain.Book;
import com.example.examination2.domain.repository.BookRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllBooksUseCase {
    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }
}
