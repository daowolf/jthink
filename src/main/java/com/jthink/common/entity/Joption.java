package com.jthink.common.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "jk_options")
public class Joption implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 博客ID，用于多用户博客，默认0
     */
    @Column(name = "blog_id")
    private Integer blogId;

    @Column(name = "option_name")
    private String optionName;

    @Column(name = "option_value")
    private String optionValue;

    /**
     * 在WordPress载入时自动载入（yes/no）
     */
    @Column(name = "autoload")
    private String autoload;

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
     * 获取博客ID，用于多用户博客，默认0
     *
     * @return blog_id - 博客ID，用于多用户博客，默认0
     */
    public Integer getBlogId() {
        return blogId;
    }

    /**
     * 设置博客ID，用于多用户博客，默认0
     *
     * @param blogId 博客ID，用于多用户博客，默认0
     */
    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    /**
     * @return option_name
     */
    public String getOptionName() {
        return optionName;
    }

    /**
     * @param optionName
     */
    public void setOptionName(String optionName) {
        this.optionName = optionName == null ? null : optionName.trim();
    }

    /**
     * @return option_value
     */
    public String getOptionValue() {
        return optionValue;
    }

    /**
     * @param optionValue
     */
    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue == null ? null : optionValue.trim();
    }

    /**
     * 获取在WordPress载入时自动载入（yes/no）
     *
     * @return autoload - 在WordPress载入时自动载入（yes/no）
     */
    public String getAutoload() {
        return autoload;
    }

    /**
     * 设置在WordPress载入时自动载入（yes/no）
     *
     * @param autoload 在WordPress载入时自动载入（yes/no）
     */
    public void setAutoload(String autoload) {
        this.autoload = autoload == null ? null : autoload.trim();
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
        sb.append(", blogId=").append(blogId);
        sb.append(", optionName=").append(optionName);
        sb.append(", optionValue=").append(optionValue);
        sb.append(", autoload=").append(autoload);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}