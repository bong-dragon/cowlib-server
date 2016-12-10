package com.cowlib.repository;

import com.cowlib.model.Borrow;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BorrowRepository {

    @Insert("insert into borrow_history values(default, #{callNumberId}, #{borrowerId}, #{status}, default)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Borrow borrow);

    @Select("select * from borrow_history where id=#{id}")
    Borrow select(Borrow borrow);

    @Update("update borrow_history set status=#{status} where id=#{id}")
    void update(Borrow borrow);

    @Delete("delete from borrow_history where id=#{id}")
    void delete(Borrow borrow);

    @Select("select * from borrow_history where call_number_id=#{callNumberId} and status=#{status} order by id desc limit 1")
    Borrow selectByCallNumberIdAndStatus(Borrow borrow);
}
