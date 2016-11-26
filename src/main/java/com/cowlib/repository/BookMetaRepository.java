package com.cowlib.repository;

import com.cowlib.model.BookMeta;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BookMetaRepository {
    @Select("select * from book_meta where isbn=#{isbn} or isbn13=#{isbn13}")
    BookMeta select(BookMeta bookMeta);

    @Insert("insert into book_meta values(default, #{isbn}, #{isbn13}, #{title}, #{author}, #{description}, #{publisher}, #{coverUrl})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(BookMeta bookMeta);

    @Delete("delete from book_meta where isbn=#{isbn} or isbn13=#{isbn13}")
    void delete(BookMeta bookMeta);

    @Select("select * from book_meta where id=#{id}")
    BookMeta selectById(int id);

    @Select("select * from book_meta where isbn=#{isbn}")
    BookMeta selectByIsbn(BookMeta bookMeta);

    @Select("select * from book_meta where isbn13=#{isbn13}")
    BookMeta selectByIsbn13(BookMeta bookMeta);
}
