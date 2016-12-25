package com.cowlib.repository;

import com.cowlib.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserRepository {

    @Insert("insert into user values(default, #{facebookId}, #{profile}, #{name}, false)")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(User user);

    @Select("select * from user where id=#{id} limit 1")
    User select(User user);

    @Select("select * from user where id=#{id}")
    User selectById(int id);

    @Select("select * from user where facebook_id=#{facebookId} limit 1")
    User selectByFacebookId(User user);

    @Delete("delete from user where id=#{id}")
    void delete(User user);
}
