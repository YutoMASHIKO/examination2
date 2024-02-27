package com.example.examination2.domain.repository;

import com.example.examination2.domain.Book;
import java.util.List;
import java.util.Optional;

/**
 * 本を管理するためのリポジトリインターフェースです.
 */
public interface BookRepository {

  /**
   * すべての本を取得します.
   *
   * @return すべての本のリスト
   */
  List<Book> getAllBooks();

  /**
   * 本IDにより指定の本を取得します.
   *
   * @param id 本のID。
   * @return 取得したOptionalのBook。見つからない場合は空のOptionalが返ります。
   */
  Optional<Book> getBookById(String id);

  /**
   * 次の利用可能な本IDを取得します.
   *
   * @return 次の本ID。
   */
  Long getNextId();

  /**
   * 本の新規登録をします.
   *
   * @param book 作成する本オブジェクト。
   * @return 作成された本。
   */
  Book insertBook(Book book);

  /**
   * 既存の本を更新します.
   *
   * @param book 更新する本
   */
  void updateBook(Book book);

  /**
   * 指定したIDの本を削除します.
   *
   * @param id 削除する本のID。
   */
  void deleteBook(String id);
}
