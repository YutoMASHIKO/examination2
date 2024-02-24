package com.example.examination2.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

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
