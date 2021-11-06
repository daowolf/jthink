package com.jthink.security.config;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jthink.permission.entity.AuthPermission;
import com.jthink.permission.entity.Role;
import com.jthink.permission.service.AuthPermissionService;
import com.jthink.permission.service.RoleService;
import com.jthink.security.util.JthinkConstant;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;

/**
 * 自定义权限验证接口扩展
 */
@Component // 保证此类被SpringBoot扫描，完成sa-token的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RoleService roleService;
	@Autowired
	private AuthPermissionService authPermissionService;

	@Override
	public List<String> getPermissionList(Object loginId, String loginKey) {
		// 判断当线会话中是否有缓存
		SaSession session = StpUtil.getSession();
		if (null != session.get(JthinkConstant.KEY_PERMS)) {
			List<String> perms = (List<String>) session.get(JthinkConstant.KEY_PERMS);
			return perms;
		}
		// 通过login key区分前台会员和后台管理员
		if (loginKey.equals("login")) {
			List<Role> roles = roleService.getRoleListByUser(loginId.toString());
			List<Integer> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
			List<AuthPermission> permissions = authPermissionService.getUnionPermission(roleIds);
			List<String> perms = permissions.stream().map(AuthPermission::getPerms).collect(Collectors.toList());
			log.debug("loginId=" + loginId + ",permissons=" + perms.size());
			session.set(JthinkConstant.KEY_PERMS, perms);
			return perms;
		}
		return null;
	}

	@Override
	public List<String> getRoleList(Object loginId, String loginKey) {
		// 判断当前会话中是否有缓存
		SaSession session = StpUtil.getSession();
		if (null != session.get(JthinkConstant.KEY_ROLENAMES)) {
			List<String> roleNames = (List<String>) session.get(JthinkConstant.KEY_ROLENAMES);
			return roleNames;
		}
		// 通过login key区分前台会员和后台管理员
		if (loginKey.equals("login")) {
			List<Role> roles = roleService.getRoleListByUser(loginId.toString());
			List<String> roleNames = roles.stream().map(Role::getName).collect(Collectors.toList());
			log.debug("loginId=" + loginId + ",roleNames=" + roleNames.size());
			session.set(JthinkConstant.KEY_ROLENAMES, roleNames);
			return roleNames;
		}
		return null;
	}

}
