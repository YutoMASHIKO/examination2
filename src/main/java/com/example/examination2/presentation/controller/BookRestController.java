package com.example.examination2.presentation.controller;

import com.example.examination2.application.DeleteBookUseCase;
import com.example.examination2.application.GetAllBooksUseCase;
import com.example.examination2.application.GetBookUseCase;
import com.example.examination2.application.InsertBookUseCase;
import com.example.examination2.application.UpdateBookUseCase;
import com.example.examination2.application.data.InsertBookData;
import com.example.examination2.application.data.UpdateBookData;
import com.example.examination2.domain.Book;
import com.example.examination2.presentation.request.InsertBookRequest;
import com.example.examination2.presentation.request.UpdateBookRequest;
import com.example.examination2.presentation.response.AllBooksResponse;
import com.example.examination2.presentation.response.BookResponse;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * 本情報を管理するためのコントローラー.
 */
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class BookRestController {

  private final GetAllBooksUseCase getAllBooksUseCase;
  private final GetBookUseCase getBookUseCase;
  private final InsertBookUseCase insertBookUseCase;
  private final UpdateBookUseCase updateBookUseCase;
  private final DeleteBookUseCase deleteBookUseCase;

  /**
   * rootURLにアクセスされた際に、ステータスコード200を返します.
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public void accessRoot() {
    // 何もしない
  }

  /**
   * すべての本を取得します.
   *
   * @return すべての本情報を含むAllBookResponse
   */
  @GetMapping("v1/books")
  @ResponseStatus(HttpStatus.OK)
  public AllBooksResponse getAllBooks() {
    return AllBooksResponse.createAllResponse(getAllBooksUseCase.getAllBooks());
  }

  /**
   * 指定したIDを持つ本の情報を取得します.
   *
   * @param id 取得したい本のID
   * @return 取得した本情報を含むBookResponse
   */
  @GetMapping("v1/books/{id}")
  @ResponseStatus(HttpStatus.OK)
  public BookResponse getBookById(@PathVariable String id) {
    return BookResponse.createResponse(getBookUseCase.getBookById(id));
  }

  /**
   * 本を新しく登録します.
   *
   * @param request 新しい本の情報を含むリクエストデータ
   * @return 作成された本のURIを含むレスポンス
   */
  @PostMapping("v1/books")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Void> insertBook(@RequestBody @Validated InsertBookRequest request) {
    Book book = insertBookUseCase.insertBook(
        new InsertBookData(request.title(), request.author(), request.publisher(), request.price())
    );

    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .pathSegment(book.id())
        .build()
        .encode()
        .toUri();

    return ResponseEntity.created(location).build();
  }

  /**
   * 本の情報を部分的に更新します.
   *
   * @param id      更新したい本のID
   * @param request 更新する情報を含むリクエストデータ
   */
  @PatchMapping("v1/books/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateBook(@PathVariable("id") String id, @RequestBody UpdateBookRequest request) {
    updateBookUseCase.updateBook(
        new UpdateBookData(id, request.title(), request.author(), request.publisher(),
            request.price())
    );
  }

  /**
   * 指定したIDの本情報を削除します.
   *
   * @param id 削除したい本のID
   */
  @DeleteMapping("v1/books/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteBook(@PathVariable("id") String id) {
    deleteBookUseCase.deleteBook(id);
  }
}
