package com.cowlib.repository;

import com.cowlib.model.CallNumber;
import org.apache.ibatis.annotations.*;
import java.util.List;
@Mapper
public interface CallNumberRepository {

    @Insert("insert into call_number values(default, #{ownerId}, #{bookMetaId}, #{isDeleted})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(CallNumber callNumber);

    @Select("select * from call_number where id=#{id}")
    CallNumber select(CallNumber callNumber);

    @Delete("delete from call_number where id=#{id}")
    void delete(CallNumber callNumber);

    @Select("select * from call_number where owner_id=#{ownerId}")
    List<CallNumber> selectByOwnerId(int ownerId);
}
