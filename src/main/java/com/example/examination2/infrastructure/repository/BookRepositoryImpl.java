package com.example.examination2.infrastructure.repository;

import static java.util.Objects.isNull;

import com.example.examination2.domain.Book;
import com.example.examination2.domain.repository.BookRepository;
import com.example.examination2.infrastructure.entity.BookEntity;
import com.example.examination2.infrastructure.exception.SqlExecutionException;
import com.example.examination2.infrastructure.mapper.BookMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
@Slf4j
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
       Integer num = bookMapper.insert(
               new BookEntity(Integer.parseInt(book.id()), book.title(), book.author(), book.publisher(), book.price())
        );

        if (isFailedSql(num)) {
            handleSqlExecutionFailure();
        }

        return book;
    }

    @Override
    public void updateBook(Book book) {
        Integer num = bookMapper.update(
                new BookEntity(Integer.parseInt(book.id()), book.title(), book.author(), book.publisher(), book.price())
        );

        if (isFailedSql(num)) {
            handleSqlExecutionFailure();
        }
    }

    @Override
    public void deleteBook(String id) {
        Integer num = bookMapper.delete(Integer.parseInt(id));

        if (isFailedSql(num)) {
            handleSqlExecutionFailure();
        }
    }

    private boolean isFailedSql(Integer number) {
        return number != 1;
    }

    private void handleSqlExecutionFailure() {
        log.error("SQLの実行に失敗しました");
        throw new SqlExecutionException("SQLの実行に失敗しました");
    }
}
