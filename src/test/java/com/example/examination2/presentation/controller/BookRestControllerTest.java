package com.example.examination2.presentation.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

import com.example.examination2.application.GetAllBooksUseCase;
import com.example.examination2.application.GetBookUseCase;
import com.example.examination2.domain.Book;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class BookRestControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    GetAllBooksUseCase getAllBooksUseCase;

    @MockBean
    GetBookUseCase getBookUseCase;

    @BeforeEach
    void setup() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void ルートURLへアクセスした場合() {
        given()
                .when()
                .get("/")
                .then()
                .statusCode(200);
    }

    @Test
    void すべての本の情報を取得する場合() {
        when(getAllBooksUseCase.getAllBooks())
                .thenReturn(
                        List.of(
                                new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080),
                                new Book("2", "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860),
                                new Book("3", "エクストリームプログラミング", "Kent Beck", "オーム社", 2420),
                                new Book("4", "Clean Agile", "Robert C. Martin", "ドワンゴ", 2640)
                        )
                );

        given()
                .when()
                .get("/v1/books")
                .then()
                .status(HttpStatus.OK)
                .body("books[0].id", equalTo("1"))
                .body("books[0].title", equalTo("テスト駆動開発"))
                .body("books[0].author", equalTo("Kent Beck"))
                .body("books[0].publisher", equalTo("オーム社"))
                .body("books[0].price", equalTo(3080))
                .body("books[1].id", equalTo("2"))
                .body("books[1].title", equalTo("アジャイルサムライ"))
                .body("books[1].author", equalTo("Jonathan Rasmusson"))
                .body("books[1].publisher", equalTo("オーム社"))
                .body("books[1].price", equalTo(2860))
                .body("books[2].id", equalTo("3"))
                .body("books[2].title", equalTo("エクストリームプログラミング"))
                .body("books[2].author", equalTo("Kent Beck"))
                .body("books[2].publisher", equalTo("オーム社"))
                .body("books[2].price", equalTo(2420))
                .body("books[3].id", equalTo("4"))
                .body("books[3].title", equalTo("Clean Agile"))
                .body("books[3].author", equalTo("Robert C. Martin"))
                .body("books[3].publisher", equalTo("ドワンゴ"))
                .body("books[3].price", equalTo(2640));
    }

    @Test
    void IDが1の本を取得できる場合() {
        when(getBookUseCase.getBookById("1"))
                .thenReturn(new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080));

        given()
                .when()
                .get("/v1/books/1")
                .then()
                .status(HttpStatus.OK)
                .body("id", equalTo("1"))
                .body("title", equalTo("テスト駆動開発"))
                .body("author", equalTo("Kent Beck"))
                .body("publisher", equalTo("オーム社"))
                .body("price", equalTo(3080));
    }

    @Test
    void IDが2の本を取得できる場合() {
        when(getBookUseCase.getBookById("2"))
                .thenReturn(new Book("2", "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860));

        given()
                .when()
                .get("/v1/books/2")
                .then()
                .status(HttpStatus.OK)
                .body("id", equalTo("2"))
                .body("title", equalTo("アジャイルサムライ"))
                .body("author", equalTo("Jonathan Rasmusson"))
                .body("publisher", equalTo("オーム社"))
                .body("price", equalTo(2860));
    }
}
