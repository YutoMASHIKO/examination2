package com.example.examination2.infrastructure.repository;

import com.example.examination2.domain.Book;
import com.example.examination2.domain.repository.BookRepository;
import com.example.examination2.infrastructure.entity.BookEntity;
import com.example.examination2.infrastructure.mapper.BookMapper;
import java.util.List;
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
    public Book getBookById(String id) {
        return bookMapper.getBookById(id).convert();
    }
}
