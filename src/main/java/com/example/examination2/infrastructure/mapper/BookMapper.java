package com.example.examination2.infrastructure.mapper;

import com.example.examination2.domain.Book;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BookMapper {

    @Select("SELECT id, title, author, publisher, price FROM books")
    List<Book> getAllBooks();
}