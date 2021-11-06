package com.jthink.permission.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jthink.common.entity.QueryRequest;
import com.jthink.controller.BaseController;
import com.jthink.permission.entity.Role;
import com.jthink.permission.entity.RolePermission;
import com.jthink.permission.service.RolePermissionService;
import com.jthink.permission.service.RoleService;
import com.jthink.utils.ResponseBo;

@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RoleService roleService;
	@Autowired
	private RolePermissionService rolePermissionService;

	@RequestMapping({ "", "/index" })
	public String index() {
		return "role/role";
	}

	@RequestMapping("/getRole")
	@ResponseBody
	public ResponseBo getRole(Integer roleId) {
		try {
			Role role = this.roleService.getRoleById(roleId);
			List<Integer> permissionIds = this.rolePermissionService.getRolePermsByRole(roleId).stream()
					.map(RolePermission::getPermissionId).collect(Collectors.toList());
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("role", role);
			data.put("permissionIds", permissionIds);
			return ResponseBo.ok(data);
		} catch (Exception e) {
			log.error("获取角色信息失败", e);
			return ResponseBo.error("获取角色信息失败，请联系网站管理员！");
		}
	}

	@RequestMapping("/add")
	@ResponseBody
	public ResponseBo addRole(Role role, Integer[] permissionIds) {
		try {
			role.setCreateTime(new Date());
			this.roleService.addRoleWithPerm(role, permissionIds);
			return ResponseBo.ok("新增角色成功！");
		} catch (Exception e) {
			log.error("新增角色失败", e);
			return ResponseBo.error("新增角色失败，请联系网站管理员！");
		}
	}

	@RequestMapping("/update")
	@ResponseBody
	public ResponseBo updateRole(Role role, Integer[] permissionIds) {
		try {
			role.setUpdateTime(new Date());
			this.roleService.UpdateRoleWithPerm(role, permissionIds);
			return ResponseBo.ok("修改角色成功！");
		} catch (Exception e) {
			log.error("修改角色失败", e);
			return ResponseBo.error("修改角色失败，请联系网站管理员！");
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ResponseBo deleteRoles(String roleIds) {
		try {
			this.roleService.deleteRoles(roleIds);
			return ResponseBo.ok("删除角色成功！");
		} catch (Exception e) {
			log.error("删除角色失败", e);
			return ResponseBo.error("删除角色失败，请联系网站管理员！");
		}
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> roleList(QueryRequest request, Role role) {
		return super.selectByPageNumSize(request, () -> this.roleService.getRoleList(role));
	}
}
