package com.example.examination2.application.data;

import static org.junit.jupiter.api.Assertions.*;

import com.example.examination2.domain.Book;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class UpdateBookDataTest {

    @ParameterizedTest
    @MethodSource("insertData")
    void 更新用のモデルに変換する場合(UpdateBookData sut, Book expected) {
        Book originalBook = new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080);

        Book actual = sut.convert(originalBook);

        assertEquals(expected, actual);
    }

    private static Stream<Arguments> insertData() {
        return Stream.of(
                Arguments.of(
                        new UpdateBookData("1", "アジャイルサムライ", "Jonathan Rasmusson", null, 2860),
                        new Book("1", "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860)
                ),
                Arguments.of(
                        new UpdateBookData("1", null, null, "ドワンゴ", null),
                        new Book("1", "テスト駆動開発", "Kent Beck", "ドワンゴ", 3080)
                )
        );
    }
}
