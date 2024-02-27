package com.example.examination2.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

/**
 * 本をを更新するためのリクエストを表します.
 *
 * @param title     更新する本のタイトル
 * @param author    更新する本の著者
 * @param publisher 更新する本の出版社
 * @param price     更新する本の価格
 */
public record UpdateBookRequest(
    @Length(max = 100)
    @JsonProperty("title")
    String title,

    @Length(max = 100)
    @JsonProperty("author")
    String author,

    @Length(max = 100)
    @JsonProperty("publisher")
    String publisher,

    @Min(1)
    @JsonProperty("price")
    Integer price
) {

}
