package com.example.examination2.presentation.controller;

import com.example.examination2.application.GetAllBooksUseCase;
import com.example.examination2.presentation.response.AllBooksResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class BookRestController {
    private final GetAllBooksUseCase getAllBooksUseCase;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void accessRoot() {
        // 何もしない
    }

    @GetMapping("v1/books")
    @ResponseStatus(HttpStatus.OK)
    public AllBooksResponse getAllBooks() {
        return AllBooksResponse.createAllResponse(getAllBooksUseCase.getAllBooks());
    }
}
