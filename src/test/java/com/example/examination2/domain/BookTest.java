package com.example.examination2.domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BookTest {
    private static final String TOO_LONG_STRING = "abcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdef";

    @Test
    void 問題なくBookが作れる場合() {
        assertDoesNotThrow(() -> {
            new Book("123", "テスト駆動開発", "Kent Beck", "オーム社", 3080);
        });
    }

    @ParameterizedTest(name = "{2} の場合")
    @CsvSource(delimiter = '|', textBlock = """
           #         ID |             MESSAGE                | TESTNAME
                        |                      IDがnullです。 | IDがnull
                      a |               IDが数字ではありません。 | IDが数字以外
                      0 | IDは1~9999999999でなくてはいけません。 | IDの数字が許容外
            10000000000 | IDは1~9999999999でなくてはいけません。 | IDの数字が許容外
           """)
    void IDのガード条件に違反する場合(String id, String message, String testName) {
        assertThatThrownBy(() -> new Book(id, "テスト駆動開発", "Kent Beck", "オーム社", 3080))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @Nested
    class タイトルに関するガード条件 {
        @Test
        void タイトルがnullの場合() {
            assertThatThrownBy(() -> new Book("1", null, "Kent Beck", "オーム社", 3080))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("タイトルがnullです。");
        }

        @Test
        void タイトルが100文字以上の場合() {
            assertThatThrownBy(() -> new Book("1", TOO_LONG_STRING, "Kent Beck", "オーム社", 3080))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("タイトルは100文字以下でなくてはいけません。");
        }
    }

    @Nested
    class 著者に関するガード条件 {
        @Test
        void 著者がnullの場合() {
            assertThatThrownBy(() -> new Book("1", "テスト駆動開発", null, "オーム社", 3080))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("著者がnullです。");
        }

        @Test
        void 著者が100文字以上の場合() {
            assertThatThrownBy(() -> new Book("1", "テスト駆動開発", TOO_LONG_STRING, "オーム社", 3080))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("著者は100文字以下でなくてはいけません。");
        }
    }

    @Nested
    class 出版社に関するガード条件 {
        @Test
        void 出版社がnullの場合() {
            assertThatThrownBy(() -> new Book("1", "テスト駆動開発", "Kent Beck", null, 3080))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("出版社がnullです。");
        }

        @Test
        void 出版社が100文字以上の場合() {
            assertThatThrownBy(() -> new Book("1", "テスト駆動開発", "Kent Beck", TOO_LONG_STRING, 3080))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("出版社は100文字以下でなくてはいけません。");
        }
    }
}
