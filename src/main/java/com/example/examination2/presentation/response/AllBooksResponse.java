package com.example.examination2.presentation.response;

import com.example.examination2.domain.Book;
import java.util.List;

/**
 * すべての本情報を含むレスポンスを表すレコードクラスです.
 *
 * @param books 本のレスポンス用オブジェクトであるBookResponseのリスト
 */
public record AllBooksResponse(List<BookResponse> books) {

  /**
   * 本オブジェクトのリストからAllBookResponseを生成します.
   *
   * @param bookList 本オブジェクトのリスト
   * @return 生成されたAllBookResponse
   */
  public static AllBooksResponse createAllResponse(List<Book> bookList) {
    return new AllBooksResponse(bookList.stream().map(BookResponse::createResponse).toList());
  }
}
