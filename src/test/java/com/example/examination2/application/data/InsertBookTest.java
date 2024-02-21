package com.example.examination2.application.data;

import static org.junit.jupiter.api.Assertions.*;

import com.example.examination2.domain.Book;
import org.junit.jupiter.api.Test;

class InsertBookTest {
    @Test
    void Employeeオブジェクトに変換できる() {
        InsertBook sut = new InsertBook("Clean Agile", "Robert C. Martin", "ドワンゴ", 2640);

        Book expected = new Book("4", "Clean Agile", "Robert C. Martin", "ドワンゴ", 2640);

        Book actual = sut.convert(4L);

        assertEquals(expected, actual);
    }
}
