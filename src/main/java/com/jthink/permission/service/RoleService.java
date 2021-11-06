package com.jthink.permission.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jthink.common.service.BaseService;
import com.jthink.permission.entity.Role;
import com.jthink.permission.entity.RolePermission;
import com.jthink.permission.mapper.RoleMapper;
import com.jthink.permission.mapper.RolePermissionMapper;

import tk.mybatis.mapper.entity.Example;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleService extends BaseService<Role>{
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RolePermissionMapper rolePermissionMapper;
	@Autowired
	private RolePermissionService rolePermissionService;
	@Autowired
	private UserRoleService userRoleService;

	public void addRoleWithPerm(Role role, Integer[] permissionIds) {
		roleMapper.insertUseGeneratedKeys(role);
		Arrays.stream(permissionIds).forEach(permissionId -> {
			RolePermission rolePerm = new RolePermission();
			rolePerm.setPermissionId(permissionId);
			rolePerm.setRoleId(role.getId());
			rolePermissionMapper.insert(rolePerm);
		});
	}

	public void UpdateRoleWithPerm(Role role, Integer[] permissionIds) {
		roleMapper.updateByPrimaryKeySelective(role);
		Example example = new Example(RolePermission.class);
		example.createCriteria().andEqualTo("roleId", role.getId());
		rolePermissionMapper.deleteByExample(example);
		Arrays.stream(permissionIds).forEach(permissionId -> {
			RolePermission rolePerm = new RolePermission();
			rolePerm.setPermissionId(permissionId);
			rolePerm.setRoleId(role.getId());
			rolePermissionMapper.insert(rolePerm);
		});
	}

	@Transactional
	public void deleteRoles(String roleIds) {
		List<String> list = Arrays.asList(roleIds.split(","));
		this.batchDelete(list, "id");
		this.rolePermissionService.deleteRolePermissionsByRoleId(roleIds);
		this.userRoleService.deleteUserRolesByRoleId(roleIds);
	}

	@Transactional
	public int batchDelete(List<String> list, String property) {
		Example example = new Example(Role.class);
		example.createCriteria().andIn(property, list);
		return this.roleMapper.deleteByExample(example);
	}

	public boolean updateRole(Role role) {
		return roleMapper.updateByPrimaryKeySelective(role) > 0;
	}

	public List<Role> getRoleListByUser(Serializable userId) {
		List<Role> roles = roleMapper.getRoleListByUser(userId);
		return roles;
	}

	public Role getRoleById(Integer roleId) {
		return roleMapper.selectByPrimaryKey(roleId);
	}

	public List<Role> getRoleList(Role role) {
		try {
			Example example = new Example(Role.class);
			if (StringUtils.isNotBlank(role.getName())) {
				example.createCriteria().andCondition("name=", role.getName());
			}
			example.setOrderByClause("create_time");
			return roleMapper.selectByExample(example);
		} catch (Exception e) {
			log.error("获取角色信息失败", e);
			return new ArrayList<>();
		}
	}

}
