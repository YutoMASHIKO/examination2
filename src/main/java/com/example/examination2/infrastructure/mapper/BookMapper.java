package com.example.examination2.infrastructure.mapper;

import com.example.examination2.infrastructure.entity.BookEntity;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 本情報をデータベースとやり取りするためのマッパーインターフェースです.
 */
@Mapper
public interface BookMapper {

  /**
   * すべての本を取得します.
   *
   * @return 本のリスト
   */
  @Select("SELECT id, title, author, publisher, price FROM books")
  List<BookEntity> getAllBooks();

  /**
   * 指定されたIDの本を取得します.
   *
   * @param id 取得したい本のID
   * @return 本エンティティ
   */
  @Select("SELECT id, title, author, publisher, price FROM books WHERE id = #{id}")
  BookEntity getBookById(Integer id);

  /**
   * 次の本のIDを取得します.
   *
   * @return 次の本の ID
   */
  @Select("SELECT nextval('BOOK_ID_SEQ')")
  @Options(flushCache = Options.FlushCachePolicy.TRUE)
  Long getNextId();

  /**
   * 本を新規登録します.
   *
   * @param bookEntity 新規登録をしたい本エンティティ
   * @return 新規登録した本の件数
   */
  @Insert("INSERT INTO books (id, title, author, publisher, price)"
      + "VALUES(#{id}, #{title}, #{author}, #{publisher}, #{price})")
  Integer insert(BookEntity bookEntity);

  /**
   * 指定したIDの本情報を更新します.
   *
   * @param bookEntity 更新する本エンティティ
   * @return 更新した件数
   */
  @Update("UPDATE books SET title = #{title}, author = #{author},"
      + "publisher = #{publisher}, price = #{price} WHERE id = #{id}")
  Integer update(BookEntity bookEntity);

  /**
   * 指定されたIDの本エンティティを削除します.
   *
   * @param id 削除する本のID
   * @return 削除する件数
   */
  @Delete("DELETE FROM books WHERE id = #{id}")
  Integer delete(Integer id);
}
