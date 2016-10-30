package com.cowlib.repository;

import com.cowlib.model.Book;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BookRepository {
    @Select("select * from book where isbn=#{isbn} or isbn13=#{isbn13}")
    Book select(Book book);

    @Insert("insert into book values(default, #{isbn}, #{isbn13}, #{title}, #{author}, #{description}, #{publisher}, #{coverUrl})")
    void insert(Book book);

    @Delete("delete from book where isbn=#{isbn} or isbn13=#{isbn13}")
    void delete(Book book);
}
