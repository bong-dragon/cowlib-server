package com.cowlib.repository;

import com.cowlib.model.CallNumber;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CallNumberRepository {

    @Insert("insert into call_number values(default, #{ownerId}, #{bookId}, #{isDeleted})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(CallNumber callNumber);

    @Select("select * from call_number where id=#{id}")
    CallNumber select(CallNumber callNumber);

    @Delete("delete from call_number where id=#{id}")
    void delete(CallNumber callNumber);
}
