package com.jthink.cms.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "jk_term")
public class Term implements Serializable {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 分类名
	 */
	@Column(name = "name")
	private String name;
	@Column(name = "icon")
	private String icon;
    /**
     * 链接图片
     */
    @Column(name = "link_image")
    private String linkImage;
	/**
	 * 缩略名
	 */
	@Column(name = "slug")
	private String slug;
	@Column(name = "template")
	private String template;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "update_time")
	private Date updateTime;
	@Column(name = "term_order")
	private Integer termOrder;
    /**
     * 分类方法,有category、link_category、tag、nav、meau 四种分类类型 
     */
    @Column(name = "taxonomy")
    private String taxonomy;

    /**
     * 分类项目的分级分类项目
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 分类项目中包含的文章数量
     */
    @Column(name = "counts")
    private Long counts;

    @Column(name = "description")
    private String description;
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
	 * 获取分类名
	 *
	 * @return name - 分类名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置分类名
	 *
	 * @param name 分类名
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * 获取缩略名
	 *
	 * @return slug - 缩略名
	 */
	public String getSlug() {
		return slug;
	}

	/**
	 * 设置缩略名
	 *
	 * @param slug 缩略名
	 */
	public void setSlug(String slug) {
		this.slug = slug == null ? null : slug.trim();
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

	/**
	 * @return update_time
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getTermOrder() {
		return termOrder;
	}

	public void setTermOrder(Integer termOrder) {
		this.termOrder = termOrder;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTaxonomy() {
		return taxonomy;
	}

	public void setTaxonomy(String taxonomy) {
		this.taxonomy = taxonomy;
	}

	public Long getCounts() {
		return counts;
	}

	public void setCounts(Long counts) {
		this.counts = counts;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getLinkImage() {
		return linkImage;
	}

	public void setLinkImage(String linkImage) {
		this.linkImage = linkImage;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", name=").append(name);
		sb.append(", slug=").append(slug);
		sb.append(", template=").append(template);
		sb.append(", createTime=").append(createTime);
		sb.append(", updateTime=").append(updateTime);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}