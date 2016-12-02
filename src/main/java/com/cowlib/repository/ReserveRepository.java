package com.cowlib.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.cowlib.model.Reserve;

@Mapper
public interface ReserveRepository {

    @Insert("insert into reserve_history values(default, #{callNumberId}, #{reserverId}, #{status}, default)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Reserve reserve);

    @Select("select * from reserve_history where id=#{id}")
    Reserve select(Reserve reserve);

    @Select("select * from reserve_history where call_number_id=#{callNumberId} and reserver_id=#{reserverId} and status=#{status}")
    Reserve selectByCallNumberIdAndReserverIdAndStatus(Reserve reserve);

    @Update("update reserve_history set status=#{status} where id=#{id}")
    void update(Reserve reserve);

    @Update("update reserve_history set status=#{status} where call_number_id=#{callNumberId}")
    void updateByCallNumberId(Reserve reserve);

    @Delete("delete from reserve_history where id=#{id}")
    void delete(Reserve reserve);

    @Select("select * from reserve_history where call_number_id=#{callNumberId} and status=#{status} order by call_number_id desc")
    List<Reserve> selectByCallNumberIdAndStatus(Reserve reserve);
}
