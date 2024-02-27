package com.example.examination2.application.exception;

import lombok.Getter;

/**
 * 特定のIDの本が見つからなかった際にスローされる例外です.
 */
@Getter
public class BookNotFoundException extends RuntimeException {

  /**
   * 見つからなかった本のID.
   */
  private final String id;

  /**
   * 見つからなかった本IDを用いてBookNotFoundExceptionを初期化します.
   *
   * @param id 見つからなかった本ID
   */
  public BookNotFoundException(String id) {
    super(id);
    this.id = id;
  }
}
