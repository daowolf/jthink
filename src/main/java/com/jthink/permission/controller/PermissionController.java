package com.jthink.permission.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jthink.cms.entity.JthinkTree;
import com.jthink.permission.entity.AuthPermission;
import com.jthink.permission.entity.Role;
import com.jthink.permission.service.AuthPermissionService;
import com.jthink.permission.service.RoleService;
import com.jthink.permission.service.UserService;
import com.jthink.permission.utils.TreeUtils;
import com.jthink.utils.ResponseBo;

import cn.dev33.satoken.stp.StpUtil;

@Controller
@RequestMapping("/system/permission")
public class PermissionController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AuthPermissionService authPermissionService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;

	@RequestMapping({ "", "/index" })
	public String permissonList() {
		return "permission/permission";
	}

	@RequestMapping("/list")
	@ResponseBody
	public List<AuthPermission> getPermissionList() {
		List<AuthPermission> permissions = authPermissionService.getAllMenus();
		return permissions;
	}

	@RequestMapping("/add")
	@ResponseBody
	public ResponseBo addMenu(AuthPermission authPermission) {
		String name="未知";
		if (AuthPermission.TYPE_CATE.equals(authPermission.getType())) {
			name = "目录";
		}else if(AuthPermission.TYPE_MENU.equals(authPermission.getType())){
			name = "菜单";
		} else if(AuthPermission.TYPE_BUTTON.equals(authPermission.getType())){
			name = "按钮";
		}
		try {
			authPermission.setCreateTime(new Date());
			authPermissionService.savePermission(authPermission);
			return ResponseBo.ok("新增" + name + "成功！");
		} catch (Exception e) {
			logger.error("新增{}失败", name, e);
			return ResponseBo.error("新增" + name + "失败，请联系网站管理员！");
		}
	}

	@RequestMapping("/update")
	@ResponseBody
	public ResponseBo update(AuthPermission authPermission) {
		String name="未知";
		if (AuthPermission.TYPE_CATE.equals(authPermission.getType())) {
			name = "目录";
		}else if(AuthPermission.TYPE_MENU.equals(authPermission.getType())){
			name = "菜单";
		} else if(AuthPermission.TYPE_BUTTON.equals(authPermission.getType())){
			name = "按钮";
		}
		try {
			authPermission.setUpdateTime(new Date());
			authPermissionService.updatePermission(authPermission);
			return ResponseBo.ok("修改" + name + "成功！");
		} catch (Exception e) {
			logger.error("修改{}失败", name, e);
			return ResponseBo.error("修改" + name + "失败，请联系网站管理员！");
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ResponseBo deletePerms(String permIds) {
		try {
			this.authPermissionService.deletePerms(permIds);
			return ResponseBo.ok("删除成功！");
		} catch (Exception e) {
			logger.error("权限删除失败", e);
			return ResponseBo.error("删除失败，请联系网站管理员！");
		}
	}

	@RequestMapping("/getPermission")
	@ResponseBody
	public ResponseBo getPermissionById(Integer permId) {
		try {
			AuthPermission authPermission = this.authPermissionService.getPermissionById(permId);
			return ResponseBo.ok(authPermission);
		} catch (Exception e) {
			logger.error("获取菜单信息失败", e);
			return ResponseBo.error("获取信息失败，请联系网站管理员！");
		}
	}

	@RequestMapping("/treeList")
	@ResponseBody
	public ResponseBo getPermissionTreeList() {
		List<JthinkTree<AuthPermission>> trees = new ArrayList<>();
		List<AuthPermission> permissions = authPermissionService.getAllMenus();
		buildTrees(trees, permissions);
		JthinkTree<AuthPermission> authTree = TreeUtils.buildTree(trees);
		return ResponseBo.ok(authTree);
	}

	@RequestMapping("/userTreeList")
	@ResponseBody
	public ResponseBo getUserPermissionTreeList() {
		List<JthinkTree<AuthPermission>> trees = new ArrayList<>();
		List<Role> roles = roleService.getRoleListByUser(StpUtil.getLoginIdAsLong());
		List<Integer> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
		if (StpUtil.hasPermission("superadmin")) {
			List<AuthPermission> permissions = authPermissionService
					.getAllUnionPermissionByType(AuthPermission.TYPE_MENU);
			buildTrees(trees, permissions);
			JthinkTree<AuthPermission> authTree = TreeUtils.buildTree(trees);
			return ResponseBo.ok(authTree);
		} else {
			List<AuthPermission> permissions = authPermissionService.getUnionPermissionByType(roleIds,
					AuthPermission.TYPE_MENU);
			buildTrees(trees, permissions);
			JthinkTree<AuthPermission> authTree = TreeUtils.buildTree(trees);
			return ResponseBo.ok(authTree);
		}

	}

	private void buildTrees(List<JthinkTree<AuthPermission>> trees, List<AuthPermission> menus) {
		menus.forEach(menu -> {
			JthinkTree<AuthPermission> tree = new JthinkTree<>();
			tree.setId(menu.getId().toString());
			tree.setParentId(menu.getPid() == null ? "0" : menu.getPid().toString());
			tree.setTitle(menu.getName());
			tree.setUrl(menu.getUrl());
			tree.setIcon(menu.getIcon());
			trees.add(tree);
		});
	}

	@RequestMapping("/checkMenuName")
	@ResponseBody
	public boolean checkMenuName(String name, String type, String oldName) {
		if (StringUtils.isNotBlank(oldName) && StringUtils.equalsIgnoreCase(name, oldName)) {
			return true;
		}
		AuthPermission result = this.authPermissionService.findByNameAndType(name, type);
		return result == null;
	}

	@RequestMapping("/checkMenuCode")
	@ResponseBody
	public boolean checkMenuCode(String permCode, String opType, Integer menuId) {
		if (StringUtils.isBlank(permCode)) {
			return true;
		}
		AuthPermission result = this.authPermissionService.findByPerm(permCode);
		if (opType.equalsIgnoreCase("add")) {
			return result == null;
		}
		if (null == result) {
			return true;
		}
		if (result.getId().equals(menuId)) {
			return true;
		}
		return false;

	}
}
