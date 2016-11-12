package com.cowlib.repository;

import com.cowlib.model.Borrow;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BorrowRepository {

    @Insert("insert into borrow_history values(default, #{callNumberId}, #{borrowerId}, #{status}, default)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Borrow borrow);

    @Select("select * from borrow_history where id=#{id}")
    Borrow select(Borrow borrow);

    @Update("update borrow_history set status=#{status} where id=#{id}")
    void update(Borrow borrow);

    @Delete("delete from borrow_history where id=#{id}")
    void delete(Borrow borrow);
}
