package com.example.examination2.common;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

import com.example.examination2.application.DeleteBookUseCase;
import com.example.examination2.application.GetAllBooksUseCase;
import com.example.examination2.application.GetBookUseCase;
import com.example.examination2.application.InsertBookUseCase;
import com.example.examination2.application.UpdateBookUseCase;
import com.example.examination2.domain.Book;
import com.example.examination2.infrastructure.entity.BookEntity;
import com.example.examination2.infrastructure.mapper.BookMapper;
import com.example.examination2.infrastructure.repository.BookRepositoryImpl;
import com.example.examination2.presentation.request.UpdateBookRequest;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class GlobalExceptionHandlerTest {
    @Autowired
    MockMvc mockMvc;

    @SpyBean
    GetAllBooksUseCase getAllBooksUseCase;

    @MockBean
    GetBookUseCase getBookUseCase;

    @MockBean
    InsertBookUseCase insertBookUseCase;

    @SpyBean
    UpdateBookUseCase updateBookUseCase;

    @MockBean
    DeleteBookUseCase deleteBookUseCase;

    @SpyBean
    BookRepositoryImpl bookRepository;

    @MockBean
    BookMapper bookMapper;

    @BeforeEach
    void setup() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void SqlExecutionExceptionが発生した場合() {
        when(bookRepository.getBookById("1"))
                .thenReturn(Optional.of(new Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080)));
        when(bookMapper.update(new BookEntity(1, "テスト駆動開発", "Uncle Bob", "オーム社", 3080)))
                .thenReturn(0);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new UpdateBookRequest("テスト駆動開発", "Uncle Bob", "オーム社", 3080))
                .when()
                .patch("/v1/books/1")
                .then()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("code", equalTo("0004"))
                .body("message", equalTo("SQLの実行に失敗しました"))
                .body("details", equalTo(emptyList()));
    }

    @Test
    void DataAccessExceptionが発生した場合() {
        when(getAllBooksUseCase.getAllBooks()).thenThrow(new DataAccessException("") {});

        given()
                .when()
                .get("/v1/books")
                .then()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("code", equalTo("0001"))
                .body("message", equalTo("Failed to access the database."))
                .body("details", equalTo(emptyList()));
    }

    @Test
    void その他予期しない例外が発生した場合() {
        when(getAllBooksUseCase.getAllBooks()).thenThrow(RuntimeException.class);

        given()
                .when()
                .get("/v1/books")
                .then()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("code", equalTo("9999"))
                .body("message", equalTo("An unexpected exception occurred: null"))
                .body("details", equalTo(emptyList()));
    }
}
