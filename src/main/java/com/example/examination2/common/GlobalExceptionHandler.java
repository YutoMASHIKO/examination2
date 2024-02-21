package com.example.examination2.common;

import static java.util.Collections.emptyList;

import com.example.examination2.application.exception.BookNotFoundException;
import com.example.examination2.presentation.response.ErrorResponse;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * グローバルな例外ハンドリングを行うクラスです.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * idによる検索に失敗した場合の例外ハンドリングメソッドです.
     *
     * @param e idによる検索に失敗した場合の例外
     * @return エラーレスポンス
     */
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBookNotFoundException(BookNotFoundException e) {
        return new ErrorResponse(
                "0003",
                String.format("specified book [id = %s] is not found.", e.getId()),
                emptyList()
        );
    }
}
