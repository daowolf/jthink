package com.jthink.permission.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "jk_role_permission")
public class RolePermission implements Serializable {

	private static final long serialVersionUID = -7573904024872252113L;
	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name = "id")
	private Integer id;

	@Column(name = "role_id")
	private Integer roleId;

	@Column(name = "permission_id")
	private Integer permissionId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

}