package com.jthink.permission.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jthink.common.config.TkMapper;
import com.jthink.permission.entity.JthinkUser;

public interface UserMapper extends TkMapper<JthinkUser> {
	@Select("select id from jk_user where username =#{username}")
	public Long getUserIdByName(String username);
	@Update("update jk_user set password=#{password} where id=#{userId}")
	public int updatePassword(String password,Integer userId);

}
