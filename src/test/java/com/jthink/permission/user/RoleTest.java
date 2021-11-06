package com.jthink.permission.user;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jthink.JthinkApplication;
import com.jthink.permission.entity.AuthPermission;
import com.jthink.permission.entity.Role;
import com.jthink.permission.mapper.AuthPermissionMapper;
import com.jthink.permission.service.RoleService;

@SpringBootTest(classes = JthinkApplication.class)
public class RoleTest {
	@Autowired
	private RoleService roleService;
	@Autowired
	private AuthPermissionMapper authPermissionDao;

	// @Test
	public void queryRoleList() {
		List<Role> roles = roleService.getRoleListByUser(1L);
		System.out.println("角色数量:" + roles.size());
	}

	@Test
	public void queryPermissions() {
		List<Integer> roleIds = new ArrayList<Integer>();
		roleIds.add(1);
		List<AuthPermission> perms = authPermissionDao.getUnionPermission(roleIds);
		perms.stream().forEach((AuthPermission s) -> {
			System.out.println(s.getName());
		});
	}

}
