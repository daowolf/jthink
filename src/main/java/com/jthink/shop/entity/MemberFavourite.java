package com.jthink.shop.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "jk_member_favourite")
public class MemberFavourite implements Serializable {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 收藏类型(product,post)
	 */
	@Column(name = "type")
	private String type;

	/**
	 * 内容标题
	 */
	@Column(name = "title")
	private String title;

	/**
	 * 所收藏的内容的主键ID
	 */
	@Column(name = "favourite_id")
	private Integer favouriteId;
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "create_time")
	private Date createTime;

	private static final long serialVersionUID = 1L;

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取收藏类型(product,post)
	 *
	 * @return type - 收藏类型(product,post)
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置收藏类型(product,post)
	 *
	 * @param type 收藏类型(product,post)
	 */
	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	/**
	 * 获取内容标题
	 *
	 * @return title - 内容标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置内容标题
	 *
	 * @param title 内容标题
	 */
	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	/**
	 * 获取所收藏的内容的主键ID
	 *
	 * @return favourite_id - 所收藏的内容的主键ID
	 */
	public Integer getFavouriteId() {
		return favouriteId;
	}

	/**
	 * 设置所收藏的内容的主键ID
	 *
	 * @param favouriteId 所收藏的内容的主键ID
	 */
	public void setFavouriteId(Integer favouriteId) {
		this.favouriteId = favouriteId;
	}

	/**
	 * @return user_id
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return create_time
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


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", type=").append(type);
		sb.append(", title=").append(title);
		sb.append(", favouriteId=").append(favouriteId);
		sb.append(", userId=").append(userId);
		sb.append(", createTime=").append(createTime);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}