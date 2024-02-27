package com.example.examination2.application;

import com.example.examination2.application.exception.BookNotFoundException;
import com.example.examination2.domain.Book;
import com.example.examination2.domain.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 本IDを指定して本情報を取得するユースケースを表現するサービスクラスです.
 */
@Service
@RequiredArgsConstructor
public class GetBookUseCase {

  private final BookRepository bookRepository;

  /**
   * 指定された本IDに対応する本情報を取得します.
   *
   * @param id 本ID
   * @return 指定されたIDに対応する本の情報
   * @throws BookNotFoundException 指定されたIDに対応する本が見つからない場合にスローされる例外
   */
  public Book getBookById(String id) {
    return bookRepository.getBookById(id)
        .orElseThrow(() -> new BookNotFoundException(id));
  }
}
