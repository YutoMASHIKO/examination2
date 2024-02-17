package com.example.examination2.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BookTest {

    @Test
    void IDがnullの場合() {
        assertThatThrownBy(() -> new Book(null, "テスト駆動開発", "Kent Beck", "オーム社", 3080))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("IDがnullです。");
    }

    @Test
    void IDが数字ではない場合() {
        assertThatThrownBy(() -> new Book("a", "テスト駆動開発", "Kent Beck", "オーム社", 3080))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("IDが数字ではありません。");
    }
}
