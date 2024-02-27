package com.example.examination2.application.data;

import static java.util.Objects.nonNull;

import com.example.examination2.domain.Book;

/**
 * 更新したい本の情報を保持します.
 *
 * @param id        更新したい本のID
 * @param title     更新したい本のタイトル
 * @param author    更新したい本の著者
 * @param publisher 更新したい本の出版社
 * @param price     更新したい本の価格
 */
public record UpdateBookData(
    String id,
    String title,
    String author,
    String publisher,
    Integer price
) {

  /**
   * 入力された新規情報を用いて、本の情報を更新します. 更新箇所以外は元の情報を保持します.
   *
   * @param book 更新したい本オブジェクト
   * @return 更新された本オブジェクト
   */
  public Book convert(Book book) {
    String updatedTitle = book.title();
    if (nonNull(title)) {
      updatedTitle = title;
    }

    String updatedAuthor = book.author();
    if (nonNull(author)) {
      updatedAuthor = author;
    }

    String updatedPublisher = book.publisher();
    if (nonNull(publisher)) {
      updatedPublisher = publisher;
    }

    Integer updatedPrice = book.price();
    if (nonNull(price)) {
      updatedPrice = price;
    }

    return new Book(book.id(), updatedTitle, updatedAuthor, updatedPublisher, updatedPrice);
  }
}
