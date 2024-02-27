package com.example.examination2.application;

import com.example.examination2.application.data.UpdateBookData;
import com.example.examination2.application.exception.BookNotFoundException;
import com.example.examination2.domain.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 本情報を更新するユースケースを表現するサービスクラスです.
 */
@Service
@RequiredArgsConstructor
public class UpdateBookUseCase {

  private final BookRepository bookRepository;

  /**
   * 本情報を更新します.
   *
   * @param updateBookData 更新対象の本情報
   * @throws BookNotFoundException 指定されたIDに対応する本が見つからない場合にスローされる例外
   */
  public void updateBook(UpdateBookData updateBookData) {
    bookRepository.updateBook(
        updateBookData.convert(
            bookRepository.getBookById(updateBookData.id())
                .orElseThrow(() -> new BookNotFoundException(updateBookData.id()))
        )
    );
  }

}
