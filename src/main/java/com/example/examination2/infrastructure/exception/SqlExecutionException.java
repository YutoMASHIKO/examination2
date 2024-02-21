package com.example.examination2.infrastructure.exception;

/**
 * この例外は、SQLの実行に失敗した場合にスローされます.
 */
public class SqlExecutionException extends RuntimeException {

    /**
     * 指定されたメッセージを用いてSqlExecutionExceptionを初期化します.
     *
     * @param message エラーメッセージ
     */
    public SqlExecutionException(String message) {
        super(message);
    }
}
