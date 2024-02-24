package com.example.examination2.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * 新しい本データを作成するために送信するリクエストを表すクラスです.
 *
 * @param title     本のタイトル
 * @param author    本の著者
 * @param publisher 本の出版社
 * @param price     本の価格
 */
public record InsertBookRequest(
        @NotBlank
        @Length(max = 100)
        @JsonProperty("title")
        String title,
        String author,
        String publisher,
        Integer price
)  {

}
