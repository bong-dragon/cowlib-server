package com.cowlib.repository;

import com.cowlib.model.Borrow;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface BorrowRepository {

    @Insert("insert into borrow_history values(default, #{callNumberId}, #{borrowerId}, #{status}, default)")
    void insert(Borrow borrow);

    @Update("update borrow_history set status=#{status} where id=#{id}")
    void update(Borrow borrow);

}
