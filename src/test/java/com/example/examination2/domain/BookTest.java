package com.example.examination2.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BookTest {

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
}
