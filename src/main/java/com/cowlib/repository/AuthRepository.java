package com.cowlib.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.cowlib.model.User;

@Mapper
public interface AuthRepository {

	@Select("select * from user limit 1")
	User getUser();
}
