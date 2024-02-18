package com.example.examination2.infrastructure.mapper;

import com.example.examination2.domain.Book;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import java.sql.DriverManager;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DBRider
@DBUnit
class BookMapperTest {

    private static final String DB_URL = "jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false";
    private static final String DB_USER = "user";
    private static final String DB_PASSWORD = "password";

    private static final ConnectionHolder connectionHolder =
            () -> DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

    @Autowired
    BookMapper sut;

    @Test
    @DataSet(value = "datasets/all-books.yml")
    void 全件取得をする場合() {
        List<Book> expected = List.of(
                new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080),
                new Book("2", "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860),
                new Book("3", "エクストリームプログラミング", "Kent Beck", "オーム社", 2420),
                new Book("4", "Clean Agile", "Robert C. Martin", "ドワンゴ", 2640)
        );

        List<Book> actual = sut.getAllBooks();

        assertEquals(expected, actual);
    }
}
