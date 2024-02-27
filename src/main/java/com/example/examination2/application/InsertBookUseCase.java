package com.example.examination2.application;

import com.example.examination2.application.data.InsertBookData;
import com.example.examination2.domain.Book;
import com.example.examination2.domain.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 本の新規登録をするユースケースを表現するサービスクラスです.
 */
@Service
@RequiredArgsConstructor
public class InsertBookUseCase {

  private final BookRepository bookRepository;

  /**
   * 本データを基に本を新規登録します.
   *
   * @param insertBookData 新規登録する本データ
   * @return 作成された新規本情報
   */
  public Book insertBook(InsertBookData insertBookData) {
    return bookRepository.insertBook(insertBookData.convert(bookRepository.getNextId()));
  }
}
