package com.cowlib.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.cowlib.model.User;

@Mapper
public interface UserRepository {

    @Insert("insert into user values(default, #{facebookId}, #{profile}, #{name}, false)")
    void insert(User user);

    @Select("select * from user where id=#{id} limit 1")
    User select(User user);

    @Select("select * from user where facebook_id=#{facebookId} limit 1")
    User selectByFacebookId(User user);
}
