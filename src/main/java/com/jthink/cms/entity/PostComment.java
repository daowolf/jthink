package com.jthink.cms.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "jk_comments")
public class PostComment implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 对应文章ID
     */
    @Column(name = "comment_post_ID")
    private Integer commentPostId;

    /**
     * 评论者
     */
    @Column(name = "comment_author")
    private String commentAuthor;

    /**
     * 评论者邮箱
     */
    @Column(name = "comment_author_email")
    private String commentAuthorEmail;

    /**
     * 评论者网址
     */
    @Column(name = "comment_author_url")
    private String commentAuthorUrl;

    /**
     * 评论者IP
     */
    @Column(name = "comment_author_IP")
    private String commentAuthorIp;

    @Column(name = "comment_date")
    private Date commentDate;

    /**
     * 评论正文
     */
    @Column(name = "comment_content")
    private String commentContent;

    /**
     * 评论是否被批准
     */
    @Column(name = "comment_approved")
    private String commentApproved;

    /**
     * 父评论ID
     */
    @Column(name = "comment_parent_id")
    private Long commentParentId;
    
    @Column(name = "taxonomy")
    private String taxonomy;
    /**
     * 评论者用户ID（不一定存在）
     */
    @Column(name = "user_id")
    private Integer userId;

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
     * 获取对应文章ID
     *
     * @return comment_post_ID - 对应文章ID
     */
    public Integer getCommentPostId() {
        return commentPostId;
    }

    /**
     * 设置对应文章ID
     *
     * @param commentPostId 对应文章ID
     */
    public void setCommentPostId(Integer commentPostId) {
        this.commentPostId = commentPostId;
    }

    /**
     * 获取评论者
     *
     * @return comment_author - 评论者
     */
    public String getCommentAuthor() {
        return commentAuthor;
    }

    /**
     * 设置评论者
     *
     * @param commentAuthor 评论者
     */
    public void setCommentAuthor(String commentAuthor) {
        this.commentAuthor = commentAuthor == null ? null : commentAuthor.trim();
    }

    /**
     * 获取评论者邮箱
     *
     * @return comment_author_email - 评论者邮箱
     */
    public String getCommentAuthorEmail() {
        return commentAuthorEmail;
    }

    /**
     * 设置评论者邮箱
     *
     * @param commentAuthorEmail 评论者邮箱
     */
    public void setCommentAuthorEmail(String commentAuthorEmail) {
        this.commentAuthorEmail = commentAuthorEmail == null ? null : commentAuthorEmail.trim();
    }

    /**
     * 获取评论者网址
     *
     * @return comment_author_url - 评论者网址
     */
    public String getCommentAuthorUrl() {
        return commentAuthorUrl;
    }

    /**
     * 设置评论者网址
     *
     * @param commentAuthorUrl 评论者网址
     */
    public void setCommentAuthorUrl(String commentAuthorUrl) {
        this.commentAuthorUrl = commentAuthorUrl == null ? null : commentAuthorUrl.trim();
    }

    /**
     * 获取评论者IP
     *
     * @return comment_author_IP - 评论者IP
     */
    public String getCommentAuthorIp() {
        return commentAuthorIp;
    }

    /**
     * 设置评论者IP
     *
     * @param commentAuthorIp 评论者IP
     */
    public void setCommentAuthorIp(String commentAuthorIp) {
        this.commentAuthorIp = commentAuthorIp == null ? null : commentAuthorIp.trim();
    }

    /**
     * @return comment_date
     */
    public Date getCommentDate() {
        return commentDate;
    }

    /**
     * @param commentDate
     */
    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    /**
     * 获取评论正文
     *
     * @return comment_content - 评论正文
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * 设置评论正文
     *
     * @param commentContent 评论正文
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    /**
     * 获取评论是否被批准
     *
     * @return comment_approved - 评论是否被批准
     */
    public String getCommentApproved() {
        return commentApproved;
    }

    /**
     * 设置评论是否被批准
     *
     * @param commentApproved 评论是否被批准
     */
    public void setCommentApproved(String commentApproved) {
        this.commentApproved = commentApproved == null ? null : commentApproved.trim();
    }

    /**
     * 获取父评论ID
     *
     * @return comment_parent_id - 父评论ID
     */
    public Long getCommentParentId() {
        return commentParentId;
    }

    /**
     * 设置父评论ID
     *
     * @param commentParentId 父评论ID
     */
    public void setCommentParentId(Long commentParentId) {
        this.commentParentId = commentParentId;
    }

    /**
     * 获取评论者用户ID（不一定存在）
     *
     * @return user_id - 评论者用户ID（不一定存在）
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置评论者用户ID（不一定存在）
     *
     * @param userId 评论者用户ID（不一定存在）
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

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    public String getTaxonomy() {
		return taxonomy;
	}

	public void setTaxonomy(String taxonomy) {
		this.taxonomy = taxonomy;
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
        sb.append(", commentPostId=").append(commentPostId);
        sb.append(", commentAuthor=").append(commentAuthor);
        sb.append(", commentAuthorEmail=").append(commentAuthorEmail);
        sb.append(", commentAuthorUrl=").append(commentAuthorUrl);
        sb.append(", commentAuthorIp=").append(commentAuthorIp);
        sb.append(", commentDate=").append(commentDate);
        sb.append(", commentContent=").append(commentContent);
        sb.append(", commentApproved=").append(commentApproved);
        sb.append(", commentParentId=").append(commentParentId);
        sb.append(", userId=").append(userId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}