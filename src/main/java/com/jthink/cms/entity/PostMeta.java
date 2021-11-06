package com.jthink.cms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "jk_postmeta")
public class PostMeta implements Serializable {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 对应文章ID
	 */
	@Column(name = "post_id")
	private Integer postId;

	/**
	 * 键名
	 */
	@Column(name = "meta_key")
	private String metaKey;

	/**
	 * 键值
	 */
	@Column(name = "meta_value")
	private String metaValue;

	private static final long serialVersionUID = 1L;

	/**
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取对应文章ID
	 *
	 * @return post_id - 对应文章ID
	 */
	public Integer getPostId() {
		return postId;
	}

	/**
	 * 设置对应文章ID
	 *
	 * @param postId 对应文章ID
	 */
	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	/**
	 * 获取键名
	 *
	 * @return meta_key - 键名
	 */
	public String getMetaKey() {
		return metaKey;
	}

	/**
	 * 设置键名
	 *
	 * @param metaKey 键名
	 */
	public void setMetaKey(String metaKey) {
		this.metaKey = metaKey == null ? null : metaKey.trim();
	}

	/**
	 * 获取键值
	 *
	 * @return meta_value - 键值
	 */
	public String getMetaValue() {
		return metaValue;
	}

	/**
	 * 设置键值
	 *
	 * @param metaValue 键值
	 */
	public void setMetaValue(String metaValue) {
		this.metaValue = metaValue == null ? null : metaValue.trim();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", postId=").append(postId);
		sb.append(", metaKey=").append(metaKey);
		sb.append(", metaValue=").append(metaValue);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}