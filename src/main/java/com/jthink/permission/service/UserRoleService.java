package com.jthink.permission.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jthink.common.service.BaseService;
import com.jthink.permission.entity.UserRole;
import com.jthink.permission.mapper.UserRoleMapper;

import tk.mybatis.mapper.entity.Example;

@Service
public class UserRoleService extends BaseService<UserRole> {
	@Autowired
	private UserRoleMapper userRoleMapper;

	public void deleteUserRolesByRoleId(String roleIds) {
		List<String> list = Arrays.asList(roleIds.split(","));
		this.batchDelete(list, "roleId", UserRole.class);
	}

	public List<UserRole> getUserRoleListByUser(Long userId) {
		Example example = new Example(UserRole.class);
		example.createCriteria().andEqualTo("userId", userId);
		return this.selectByExample(example);
	}

	public int deleteUserRole(Long userId) {
		Example example = new Example(UserRole.class);
		example.createCriteria().andEqualTo("userId", userId);
		return this.deleteByExample(example);
	}

	public int batchDeleteUserRole(String userIds) {
		List<String> ids = Arrays.asList(userIds.split(","));
		return this.batchDelete(ids, "userId", UserRole.class);
	}

	public int deleteByExample(Example example) {
		return this.userRoleMapper.deleteByExample(example);
	}
}
