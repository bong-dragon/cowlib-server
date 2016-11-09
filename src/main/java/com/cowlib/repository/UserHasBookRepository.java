package com.cowlib.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserHasBookRepository {

    @Insert("insert into user_has_book values(default, #{userId}, #{bookId}, default)")
    void insert(@Param("userId") String userId, @Param("bookId")String bookId);

}
