package com.example.examination2.presentation.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;

import com.example.examination2.application.GetAllBooksUseCase;
import com.example.examination2.application.GetBookUseCase;
import com.example.examination2.application.InsertBookUseCase;
import com.example.examination2.application.UpdateBookUseCase;
import com.example.examination2.application.data.InsertBookData;
import com.example.examination2.application.exception.BookNotFoundException;
import com.example.examination2.domain.Book;
import com.example.examination2.presentation.request.InsertBookRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@Slf4j
class BookRestControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    GetAllBooksUseCase getAllBooksUseCase;

    @MockBean
    GetBookUseCase getBookUseCase;

    @MockBean
    InsertBookUseCase insertBookUseCase;

    @MockBean
    UpdateBookUseCase updateBookUseCase;

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

    @Nested
    class ID検索 {
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

        @Test
        void 存在しないIDで本の検索を行う場合() {
            when(getBookUseCase.getBookById("99"))
                    .thenThrow(new BookNotFoundException("99"));

            given()
                    .when()
                    .get("/v1/books/99")
                    .then()
                    .statusCode(400)
                    .body("code", equalTo("0003"))
                    .body("message", equalTo("specified book [id = 99] is not found."))
                    .body("details", equalTo(emptyList()));
        }
    }

    @Nested
    class 本の新規登録 {

        @Test
        void 成功する場合() {
            when(insertBookUseCase.insertBook(new InsertBookData("Clean Agile", "Robert C. Martin", "ドワンゴ", 2640)))
                    .thenReturn(new Book("4", "Clean Agile", "Robert C. Martin", "ドワンゴ", 2640));

            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body("""
                            {
                            "title": "Clean Agile",
                            "author": "Robert C. Martin",
                            "publisher": "ドワンゴ",
                            "price": 2640
                            }
                            """)
                    .when()
                    .post("/v1/books")
                    .then()
                    .statusCode(201)
                    .header("Location", equalTo("http://localhost/v1/books/4"));
        }

        @ParameterizedTest(name = "{4} の場合")
        @CsvSource(delimiter = '|', textBlock = """
           #       TITLE |    AUTHOR | PUBLISHER | PRICE | TESTNAME
                     ' ' | Kent Beck |   オーム社 |  3080 | タイトルが空白
             テスト駆動開発 |       ' ' |   オーム社 |  3080 | 著者が空白
             テスト駆動開発 | Kent Beck |      ' ' |  3080 | 出版社が空白
             テスト駆動開発 | Kent Beck |   オーム社 |       | 値段がnull
             テスト駆動開発 | Kent Beck |   オーム社 | -3080 | 値段がマイナス
           """)
        void 失敗する場合(String title, String author, String publisher, Integer price, String testName) {
            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(marshalToJson(new InsertBookRequest(title, author, publisher, price)))
                    .when()
                    .post("/v1/books")
                    .then()
                    .statusCode(400)
                    .body("code", equalTo("0002"))
                    .body("message", equalTo("request validation error is occurred."))
                    .body("details", hasSize(1));
        }

    }

    @Test
    void 更新を行う場合() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new UpdateBookRequest("", "", "", 0))
                .when()
                .patch("/v1/books/1")
                .then()
                .status(HttpStatus.NO_CONTENT)
                .body(equalTo(""));
    }

    private static String marshalToJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.info("JSONへの変換に失敗しました。", e);
        }
        return "";
    }
}
