package com.jthink.permission.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "jk_user")
public class JthinkUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6095244651793635591L;
	/**
	 * 账户状态
	 */
	public static final String STATUS_VALID = "1";

	public static final String STATUS_LOCK = "0";

	public static final String DEFAULT_THEME = "green";

	public static final String DEFAULT_AVATAR = "default.jpg";

	/**
	 * 性别
	 */
	public static final String SEX_MALE = "0";

	public static final String SEX_FEMALE = "1";

	public static final String SEX_UNKNOW = "2";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "username")
	private String username;
	@Column(name = "nickname")
	private String nickname;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;
	@Column(name = "ssex")
	private String ssex;

	@Column(name = "remarks")
	private String remarks;
	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "update_time")
	private Date updateTime;

	@Column(name = "last_login_time")
	private Date lastLoginTime;
	@Column(name = "sstate")
	private String status = STATUS_VALID;
	private List<Role> roles = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getSsex() {
		return ssex;
	}

	public void setSsex(String ssex) {
		this.ssex = ssex;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
