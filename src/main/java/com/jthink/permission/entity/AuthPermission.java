package com.jthink.permission.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "jk_permissions")
public class AuthPermission implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String TYPE_CATE = "0";
	public static final String TYPE_MENU = "1";

	public static final String TYPE_BUTTON = "2";

	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name = "id")
	private Integer id;

	@Column(name = "pid")
	private Integer pid;

	@Column(name = "name")
	private String name;

	@Column(name = "url")
	private String url;

	@Column(name = "perms")
	private String perms;

	@Column(name = "icon")
	private String icon;

	@Column(name = "type")
	private String type;
	@Column(name = "is_show")
	private Integer isShow;

	@Column(name = "order_num")
	private Integer orderNum;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "update_time")
	private Date updateTime;

	/**
	 * @return URL
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url == null ? "" : url.trim();
	}

	/**
	 * @return PERMS
	 */
	public String getPerms() {
		return perms;
	}

	/**
	 * @param perms
	 */
	public void setPerms(String perms) {
		this.perms = perms == null ? "" : perms.trim();
	}

	/**
	 * @return ICON
	 */
	public String getIcon() {
		return icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	/**
	 * @param icon
	 */
	public void setIcon(String icon) {
		this.icon = icon == null ? "" : icon.trim();
	}

	/**
	 * @return TYPE
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 */
	public void setType(String type) {
		this.type = type == null ? "" : type.trim();
	}

	/**
	 * @return ORDER_NUM
	 */
	public Integer getOrderNum() {
		return orderNum;
	}

	/**
	 * @param orderNum
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * @return CREATE_TIME
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
