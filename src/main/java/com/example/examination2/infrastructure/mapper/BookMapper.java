package com.example.examination2.infrastructure.mapper;

import com.example.examination2.infrastructure.entity.BookEntity;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BookMapper {

    @Select("SELECT id, title, author, publisher, price FROM books")
    List<BookEntity> getAllBooks();

    @Select("SELECT id, title, author, publisher, price FROM books WHERE id = #{id}")
    BookEntity getBookById(Integer id);

    @Select("SELECT nextval('BOOK_ID_SEQ')")
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    Long getNextId();

    @Insert("INSERT INTO books (id, title, author, publisher, price) VALUES(#{id}, #{title}, #{author}, #{publisher}, #{price})")
    Integer insert(BookEntity bookEntity);
}
