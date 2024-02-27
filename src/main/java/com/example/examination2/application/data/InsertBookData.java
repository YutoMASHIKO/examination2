package com.example.examination2.application.data;

import com.example.examination2.domain.Book;

/**
 * 本を新規登録するためのデータを保持します.
 *
 * @param title     本のタイトル
 * @param author    本の著者
 * @param publisher 本の出版社
 * @param price     本の価格
 */
public record InsertBookData(String title, String author, String publisher, Integer price) {

  /**
   * データを指定されたIDを用いて、新しいBookオブジェクトに変換します.
   *
   * @param id 新規登録する本のID
   * @return 本オブジェクト
   */
  public Book convert(Long id) {
    return new Book(String.valueOf(id), title, author, publisher, price);
  }
}
