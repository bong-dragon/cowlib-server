package com.cowlib.repository;

import com.cowlib.model.Reserve;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ReserveRepository {

    @Insert("insert into reserve_history values(default, #{callNumberId}, #{reserverId}, #{status}, default)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Reserve reserve);

    @Select("select * from reserve_history where id=#{id}")
    Reserve select(Reserve reserve);

    @Update("update reserve_history set status=#{status} where id=#{id}")
    void update(Reserve reserve);

    @Delete("delete from reserve_history where id=#{id}")
    void delete(Reserve reserve);

    @Select("select * from reserve_history where id=#{callNumberId} and status=#{status} order by id desc")
    List<Reserve> selectByCallNumberIdAndStatus(Reserve reserve);
}
