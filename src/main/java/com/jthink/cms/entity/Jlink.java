package com.jthink.cms.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "jk_links")
public class Jlink implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 链接URL

     */
    @Column(name = "link_url")
    private String linkUrl;

    /**
     * 链接标题
     */
    @Column(name = "link_name")
    private String linkName;

    /**
     * 链接图片
     */
    @Column(name = "link_image")
    private String linkImage;

    /**
     * 链接打开方式
     */
    @Column(name = "link_target")
    private String linkTarget;

    /**
     * 链接描述
     */
    @Column(name = "link_description")
    private String linkDescription;

    /**
     * 是否可见（Y/N）
     */
    @Column(name = "link_visible")
    private String linkVisible;

    /**
     * 添加者用户ID
     */
    @Column(name = "link_owner")
    private Integer linkOwner;

    /**
     * 评分等级
     */
    @Column(name = "link_rating")
    private Integer linkRating;

    @Column(name = "create_time")
    private Date createTime;

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
     * 获取链接URL

     *
     * @return link_url - 链接URL

     */
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
     * 设置链接URL

     *
     * @param linkUrl 链接URL

     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    /**
     * 获取链接标题
     *
     * @return link_name - 链接标题
     */
    public String getLinkName() {
        return linkName;
    }

    /**
     * 设置链接标题
     *
     * @param linkName 链接标题
     */
    public void setLinkName(String linkName) {
        this.linkName = linkName == null ? null : linkName.trim();
    }

    /**
     * 获取链接图片
     *
     * @return link_image - 链接图片
     */
    public String getLinkImage() {
        return linkImage;
    }

    /**
     * 设置链接图片
     *
     * @param linkImage 链接图片
     */
    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage == null ? null : linkImage.trim();
    }

    /**
     * 获取链接打开方式
     *
     * @return link_target - 链接打开方式
     */
    public String getLinkTarget() {
        return linkTarget;
    }

    /**
     * 设置链接打开方式
     *
     * @param linkTarget 链接打开方式
     */
    public void setLinkTarget(String linkTarget) {
        this.linkTarget = linkTarget == null ? null : linkTarget.trim();
    }

    /**
     * 获取链接描述
     *
     * @return link_description - 链接描述
     */
    public String getLinkDescription() {
        return linkDescription;
    }

    /**
     * 设置链接描述
     *
     * @param linkDescription 链接描述
     */
    public void setLinkDescription(String linkDescription) {
        this.linkDescription = linkDescription == null ? null : linkDescription.trim();
    }

    /**
     * 获取是否可见（Y/N）
     *
     * @return link_visible - 是否可见（Y/N）
     */
    public String getLinkVisible() {
        return linkVisible;
    }

    /**
     * 设置是否可见（Y/N）
     *
     * @param linkVisible 是否可见（Y/N）
     */
    public void setLinkVisible(String linkVisible) {
        this.linkVisible = linkVisible == null ? null : linkVisible.trim();
    }

    /**
     * 获取添加者用户ID
     *
     * @return link_owner - 添加者用户ID
     */
    public Integer getLinkOwner() {
        return linkOwner;
    }

    /**
     * 设置添加者用户ID
     *
     * @param linkOwner 添加者用户ID
     */
    public void setLinkOwner(Integer linkOwner) {
        this.linkOwner = linkOwner;
    }

    /**
     * 获取评分等级
     *
     * @return link_rating - 评分等级
     */
    public Integer getLinkRating() {
        return linkRating;
    }

    /**
     * 设置评分等级
     *
     * @param linkRating 评分等级
     */
    public void setLinkRating(Integer linkRating) {
        this.linkRating = linkRating;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", linkUrl=").append(linkUrl);
        sb.append(", linkName=").append(linkName);
        sb.append(", linkImage=").append(linkImage);
        sb.append(", linkTarget=").append(linkTarget);
        sb.append(", linkDescription=").append(linkDescription);
        sb.append(", linkVisible=").append(linkVisible);
        sb.append(", linkOwner=").append(linkOwner);
        sb.append(", linkRating=").append(linkRating);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}