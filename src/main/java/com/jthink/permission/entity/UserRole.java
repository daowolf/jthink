package com.jthink.permission.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "jk_user_role")
public class UserRole implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8409085050951909901L;

	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name = "id")
	private Integer id;

	@Column(name = "role_id")
	private Integer roleId;
	@Column(name = "user_id")
	private Integer userId;

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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
