package com.example.examination2.application;

import com.example.examination2.application.data.UpdateBookData;
import com.example.examination2.application.exception.BookNotFoundException;
import com.example.examination2.domain.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateBookUseCase {
    private final BookRepository bookRepository;

    public void updateBook(UpdateBookData updateBookData) {
        bookRepository.updateBook(
                updateBookData.convert(
                        bookRepository.getBookById(updateBookData.id())
                                .orElseThrow(() -> new BookNotFoundException(updateBookData.id()))
                )
        );
    }

}
