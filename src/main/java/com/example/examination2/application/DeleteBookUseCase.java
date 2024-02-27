package com.example.examination2.application;

import com.example.examination2.application.exception.BookNotFoundException;
import com.example.examination2.domain.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 本を削除するユースケースを表現するサービスクラスです.
 */
@Service
@RequiredArgsConstructor
public class DeleteBookUseCase {

  private final BookRepository bookRepository;

  /**
   * 指定された本IDに対応する本を削除します.
   *
   * @param id 削除対象の本ID
   * @throws BookNotFoundException 指定されたIDに対応する本が見つからない場合にスローされる例外
   */
  @Transactional
  public void deleteBook(String id) {
    if (bookRepository.getBookById(id).isEmpty()) {
      throw new BookNotFoundException(id);
    }
    bookRepository.deleteBook(id);
  }
}
