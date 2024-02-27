package com.example.examination2.application;

import com.example.examination2.domain.Book;
import com.example.examination2.domain.repository.BookRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * すべての本を取得するユースケースを表現するサービスクラスです.
 */
@Service
@RequiredArgsConstructor
public class GetAllBooksUseCase {

  private final BookRepository bookRepository;

  /**
   * すべての本を取得します.
   *
   * @return 本のリスト
   */
  public List<Book> getAllBooks() {
    return bookRepository.getAllBooks();
  }
}
