package com.example.examination2.presentation.response;

import com.example.examination2.domain.Book;
import java.util.List;

public record AllBooksResponse(List<BookResponse> books) {
    public static AllBooksResponse createAllResponse(List<Book> bookList) {
        return new AllBooksResponse(bookList.stream().map(BookResponse::createResponse).toList());
    }
}
