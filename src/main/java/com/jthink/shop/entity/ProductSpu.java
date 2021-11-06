package com.jthink.shop.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "jk_spu")
public class ProductSpu implements Serializable {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 商品规格名称
	 */
	@Column(name = "spu")
	private String spu;

	/**
	 * 商品规格值
	 */
	@Column(name = "value")
	private String value;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 更新时间
	 */
	@Column(name = "update_time")
	private Date updateTime;

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
	 * 获取商品规格名称
	 *
	 * @return specification - 商品规格名称
	 */
	public String getSpu() {
		return spu;
	}

	/**
	 * 设置商品规格名称
	 *
	 * @param specification 商品规格名称
	 */
	public void setSpu(String spu) {
		this.spu = spu == null ? null : spu.trim();
	}

	/**
	 * 获取商品规格值
	 *
	 * @return value - 商品规格值
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 设置商品规格值
	 *
	 * @param value 商品规格值
	 */
	public void setValue(String value) {
		this.value = value == null ? null : value.trim();
	}

	/**
	 * 获取创建时间
	 *
	 * @return create_time - 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 *
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取更新时间
	 *
	 * @return update_time - 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置更新时间
	 *
	 * @param updateTime 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", spu=").append(spu);
		sb.append(", value=").append(value);
		sb.append(", createTime=").append(createTime);
		sb.append(", updateTime=").append(updateTime);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}