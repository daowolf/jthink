package com.jthink.permission.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jthink.common.service.BaseService;
import com.jthink.permission.entity.AuthPermission;
import com.jthink.permission.entity.JthinkUser;
import com.jthink.permission.entity.Role;
import com.jthink.permission.entity.UserRole;
import com.jthink.permission.mapper.UserMapper;
import com.jthink.security.constants.JthinkSecurity;
import com.jthink.security.util.LoginUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserService extends BaseService<JthinkUser> {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private AuthPermissionService authPermissionService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleService userRoleService;

	public Long getUserIdByName(String username) {
		return userMapper.getUserIdByName(username);
	}

	public boolean addUser(JthinkUser user, Integer[] roleIds) {
		int count = this.userMapper.insertUseGeneratedKeys(user);
		log.warn("更新用户角色为空");
		if (null != roleIds) {
			Arrays.stream(roleIds).forEach(roleId -> {
				UserRole ur = new UserRole();
				ur.setUserId(user.getId());
				ur.setRoleId(roleId);
				this.userRoleService.save(ur);
			});
		}
		return count > 0;
	}

	public boolean updateUser(JthinkUser user, Integer[] roleIds) {
		int count = this.updateNotNull(user);
		Example example = new Example(UserRole.class);
		example.createCriteria().andEqualTo("userId", user.getId());
		this.userRoleService.deleteByExample(example);
		log.warn("更新用户角色为空");
		if (null != roleIds) {
			Arrays.stream(roleIds).forEach(roleId -> {
				UserRole ur = new UserRole();
				ur.setUserId(user.getId());
				ur.setRoleId(roleId);
				this.userRoleService.save(ur);
			});
		}

		return count > 0;
	}

	public boolean updateUserProfile(JthinkUser user) {
		int count = this.updateNotNull(user);
		return count > 0;
	}

	public int deleteUsersByIds(String userIds) {
		List<String> ids = Arrays.asList(userIds.split(","));
		return this.batchDelete(ids, "id", JthinkUser.class);
	}

	public List<JthinkUser> getUserList(JthinkUser user) {
		Example example = new Example(JthinkUser.class);
		example.createCriteria().andEqualTo("username", user.getUsername()).andEqualTo("email", user.getEmail())
				.andEqualTo("phone", user.getPhone());
		return userMapper.selectByExample(example);
	}

	public JthinkUser findByUsernameOrEmail(String username, String email) {
		Example example = new Example(JthinkUser.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("username", username);
		criteria.orEqualTo("email", email);
		JthinkUser user = userMapper.selectOneByExample(example);
		return user;
	}

	public List<AuthPermission> getUserMenus(Long userId) {
		List<Role> roles = roleService.getRoleListByUser(userId);
		List<Integer> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
		List<AuthPermission> permissions = authPermissionService.getUnionPermissionByType(roleIds,
				JthinkSecurity.MenuType);
		return permissions;
	}

	public int updatePassword(String newPassword, Integer userId) {
		return this.userMapper.updatePassword(LoginUtil.encodePwd(newPassword), userId);
	}
}
