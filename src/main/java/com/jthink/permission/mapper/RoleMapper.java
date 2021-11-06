package com.jthink.permission.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.jthink.common.config.TkMapper;
import com.jthink.permission.entity.Role;

public interface RoleMapper extends TkMapper<Role> {
	@Results(id = "roleMap", value = { @Result(property = "id", column = "id"),
			@Result(property = "name", column = "name"), @Result(property = "note", column = "note"),
			@Result(property = "createTime", column = "create_time"),
			@Result(property = "updateTime", column = "update_time") })

	@Select("SELECT tr.* FROM jk_role tr left join jk_user_role ur on tr.id = ur.role_id where ur.user_id=#{userId}")
	public List<Role> getRoleListByUser(Serializable userId);

}
