package com.jthink.permission.service;

import com.jthink.common.service.BaseService;
import com.jthink.permission.entity.AuthPermission;
import com.jthink.permission.mapper.AuthPermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AuthPermissionService extends BaseService<AuthPermission> {
	@Autowired
	private AuthPermissionMapper authPermissionMapper;
	@Autowired
	private RolePermissionService authRolePermissionService;

	public List<AuthPermission> getRolePermissions(Integer roleId) {
		return authPermissionMapper.getRolePermissions(roleId);
	}

	public List<AuthPermission> getUnionPermission(List<Integer> roleIds) {
		return authPermissionMapper.getUnionPermission(roleIds);
	}

	public List<AuthPermission> getUnionPermissionByType(List<Integer> roleIds, String type) {
		return authPermissionMapper.getUnionPermissionByType(roleIds, type);
	}

	public List<AuthPermission> getAllUnionPermissionByType(String type) {
		return authPermissionMapper.getAllUnionPermissionByType(type);
	}

	public List<AuthPermission> getAllMenus() {
		return authPermissionMapper.selectAll();
	}

	public int savePermission(AuthPermission authPermission) {
		return authPermissionMapper.insert(authPermission);
	}

	public int updatePermission(AuthPermission authPermission) {
		return authPermissionMapper.updateByPrimaryKeySelective(authPermission);
	}

	public AuthPermission getPermissionById(Integer permId) {
		return authPermissionMapper.selectByPrimaryKey(permId);
	}

	public void deletePerms(String permIds) {
		List<String> list = Arrays.asList(permIds.split(","));
		this.batchDelete(list, "id");
		this.authRolePermissionService.deleteRolePermsByIds(permIds);
		authPermissionMapper.changeToTop(list);
	}

	public AuthPermission findByNameAndType(String name, String type) {
		Example example = new Example(AuthPermission.class);
		example.createCriteria().andEqualTo("name", name).andEqualTo("type", type);
		return authPermissionMapper.selectOneByExample(example);
	}

	public AuthPermission findByPerm(String permCode) {
		Example example = new Example(AuthPermission.class);
		example.createCriteria().andEqualTo("perms", permCode);
		return authPermissionMapper.selectOneByExample(example);
	}

	public int batchDelete(List<String> list, String property) {
		Example example = new Example(AuthPermission.class);
		example.createCriteria().andIn(property, list);
		return this.authPermissionMapper.deleteByExample(example);
	}
}
