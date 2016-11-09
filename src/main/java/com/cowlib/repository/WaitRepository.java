package com.cowlib.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface WaitRepository {

    @Insert("insert into wait_history values(" +
            "(select id from user_has_book where user_id=#{userId} and book_id=#{bookId}), " +
            "#{waiterId}, default, default)")
    void insert(@Param("userId") String userId, @Param("bookId") String bookId, @Param("waiterId") String waiterId);

    @Update("update wait_history set status=#{status} where user_has_book_id in " +
            "(select * from (select id from user_has_book where user_id=#{userId} and book_id=#{bookId} order by id desc limit 1) as t) " +
            "and waiter=#{waiterId} limit 1")
    void update(@Param("userId") String userId, @Param("bookId") String bookId, @Param("waiterId") String waiterId,  @Param("status") String status);
}
