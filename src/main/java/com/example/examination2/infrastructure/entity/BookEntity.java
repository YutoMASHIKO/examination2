package com.example.examination2.infrastructure.entity;

import com.example.examination2.domain.Book;

/**
 * 本を表すエンティティです.
 * このエンティティはデータベースからの本情報を保持します.
 *
 * @param id        本のID
 * @param title     本のタイトル
 * @param author    本の著者
 * @param publisher 本の出版社
 * @param price     本の価格
 */
public record BookEntity(Integer id, String title, String author, String publisher, Integer price) {

  /**
   * 本エンティティを本モデルへと変化します.
   *
   * @return 変換された本オブジェクト
   */
  public Book convert() {
    return new Book(String.valueOf(id), title, author, publisher, price);
  }

}
