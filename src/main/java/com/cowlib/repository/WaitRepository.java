package com.cowlib.repository;

import com.cowlib.model.Wait;
import org.apache.ibatis.annotations.*;

@Mapper
public interface WaitRepository {

    @Insert("insert into wait_history values(default, #{callNumberId}, #{waiterId}, #{status}, default)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Wait wait);

    @Select("select * from wait_history where id=#{id}")
    Wait select(Wait wait);

    @Update("update wait_history set status=#{status} where id=#{id}")
    void update(Wait wait);

    @Delete("delete from wait_history where id=#{id}")
    void delete(Wait wait);
}
