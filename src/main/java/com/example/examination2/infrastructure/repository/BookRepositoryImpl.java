package com.example.examination2.infrastructure.repository;

import static java.util.Objects.isNull;

import com.example.examination2.domain.Book;
import com.example.examination2.domain.repository.BookRepository;
import com.example.examination2.infrastructure.entity.BookEntity;
import com.example.examination2.infrastructure.mapper.BookMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BookRepositoryImpl implements BookRepository {
    private final BookMapper bookMapper;

    @Override
    public List<Book> getAllBooks() {
        return bookMapper.getAllBooks().stream().map(BookEntity::convert).toList();
    }

    @Override
    public Optional<Book> getBookById(String id) {
        if (isNull(bookMapper.getBookById(Integer.parseInt(id)))) {
            return Optional.empty();
        }
        return Optional.of(bookMapper.getBookById(Integer.parseInt(id)).convert());
    }

    @Override
    public Long getNextId() {
        return bookMapper.getNextId();
    }

    @Override
    public Book insertBook(Book book) {
       bookMapper.insert(
               new BookEntity(Integer.parseInt(book.id()), book.title(), book.author(), book.publisher(), book.price())
        );

        return book;
    }
}
