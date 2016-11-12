package com.cowlib.repository;

import com.cowlib.model.CallNumber;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CallNumberRepository {

    @Insert("insert into user_has_book values(default, #{ownerId}, #{bookId}, #{isDeleted})")
    void insert(CallNumber callNumber);

}
