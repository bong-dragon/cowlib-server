package com.cowlib.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface BorrowRepository {

    @Insert("insert into borrow_history values(" +
            "(select id from user_has_book where user_id=#{userId} and book_id=#{bookId}), " +
            "#{borrowerId}, default, default)")
    void insert(@Param("userId") String userId, @Param("bookId") String bookId, @Param("borrowerId") String borrowerId);

    @Update("update borrow_history set status=#{status} where user_has_book_id in " +
            "(select * from (select id from user_has_book where user_id=#{userId} and book_id=#{bookId} order by id desc limit 1) as t) " +
            "and borrower=#{borrowerId} limit 1")
    void update(@Param("userId") String userId, @Param("bookId") String bookId, @Param("borrowerId") String borrowerId, @Param("status") String status);

}
