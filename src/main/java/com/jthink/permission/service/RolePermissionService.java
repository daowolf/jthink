package com.jthink.permission.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jthink.common.service.BaseService;
import com.jthink.permission.entity.RolePermission;
import com.jthink.permission.mapper.RolePermissionMapper;

import tk.mybatis.mapper.entity.Example;

@Service
public class RolePermissionService extends BaseService<RolePermission> {
	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	public List<RolePermission> getRolePermsByRole(Integer roleId) {
		Example example = new Example(RolePermission.class);
		example.createCriteria().andEqualTo("roleId", roleId);
		return rolePermissionMapper.selectByExample(example);
	}

	public int deleteRolePermission(Integer roleId) {
		Example example = new Example(RolePermission.class);
		example.createCriteria().andEqualTo("roleId", roleId);
		return rolePermissionMapper.deleteByExample(example);
	}

	public int deleteRolePermsByIds(String permIds) {
		List<String> list = Arrays.asList(permIds.split(","));
		return this.batchDelete(list, "permissionId", RolePermission.class);
	}

	public void deleteRolePermissionsByRoleId(String roleIds) {
		List<String> list = Arrays.asList(roleIds.split(","));
		this.batchDelete(list, "roleId", RolePermission.class);
	}

}
