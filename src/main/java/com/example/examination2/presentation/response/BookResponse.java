package com.example.examination2.presentation.response;

import com.example.examination2.domain.Book;

/**
 * 本を表すレスポンス用のオブジェクト.
 *
 * @param id        本のID
 * @param title     本のタイトル
 * @param author    本の著者
 * @param publisher 本の出版社
 * @param price     本の価格
 */
public record BookResponse(
    String id,
    String title,
    String author,
    String publisher,
    Integer price
) {

  /**
   * BookResponseを作成します.
   *
   * @param book 本オブジェクト
   * @return 作成されたBookResponse
   */
  public static BookResponse createResponse(Book book) {
    return new BookResponse(
        book.id(),
        book.title(),
        book.author(),
        book.publisher(),
        book.price()
    );
  }
}
