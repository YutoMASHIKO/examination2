package com.example.examination2.presentation.response;

import java.util.List;

/**
 * エラーレスポンスを表すレコードクラスです.
 *
 * @param code    エラーコード
 * @param message エラーメッセージ
 * @param details エラーの詳細情報のリスト
 */
public record ErrorResponse(String code, String message, List<String> details) {

}
