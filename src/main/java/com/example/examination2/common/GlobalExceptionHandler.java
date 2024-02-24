package com.example.examination2.common;

import static java.util.Collections.emptyList;

import com.example.examination2.application.exception.BookNotFoundException;
import com.example.examination2.infrastructure.exception.SqlExecutionException;
import com.example.examination2.presentation.response.ErrorResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * グローバルな例外ハンドリングを行うクラスです.
 */
@Slf4j
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

    /**
     * リクエストの入力に問題があった場合の例外ハンドリングメソッドです.
     *
     * @param e リクエストの入力に問題があった場合に発生する例外
     * @return バリデーションエラーに関する詳細情報を含むエラーレスポンス
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        List<String> details = new ArrayList<>();

        for (FieldError error : e.getFieldErrors()) {
            details.add(String.format("%s %s", error.getField(), error.getDefaultMessage()));
        }
        return new ErrorResponse(
                "0002",
                "request validation error is occurred.",
                details
        );
    }

    /**
     * SQL実行時に問題があった場合の例外ハンドリングメソッドです.
     *
     * @param e SQL実行時に問題があった場合の例外
     * @return エラーレスポンス
     */
    @ExceptionHandler(SqlExecutionException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleSqlExecution(SqlExecutionException e) {
        return new ErrorResponse(
                "0004",
                e.getMessage(),
                emptyList()
        );
    }

    /**
     * データベースへのアクセスが失敗した場合の例外処理を行います.
     *
     * @param e データベースへのアクセスが失敗した場合の例外
     * @return データベースへのアクセスが失敗したことを示すエラーレスポンス
     */
    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleDatabaseAccessException(DataAccessException e) {
        return new ErrorResponse(
                "0001",
                "Failed to access the database.",
                emptyList()
        );
    }

    /**
     * アプリケーション内で発生する予期せぬ例外を処理します.
     *
     * @param e 発生した予期せぬ例外。
     * @return 予期せぬ例外に関する詳細を含むエラーレスポンス
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleOtherException(Exception e) {
        log.error("An unexpected exception occurred: {}", e.getMessage());
        return new ErrorResponse(
                "9999",
                String.format("An unexpected exception occurred: %s", e.getMessage()),
                emptyList()
        );
    }
}
