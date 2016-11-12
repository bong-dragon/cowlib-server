package com.cowlib.repository;

import com.cowlib.model.Wait;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface WaitRepository {

    @Insert("insert into wait_history values(default, #{callNumberId}, #{waiterId}, #{status}, default)")
    void insert(Wait wait);

    @Update("update wait_history set status=#{status} where id=#{id}")
    void update(Wait wait);
}
