package com.jthink.cms.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "jk_posts")
public class Post implements Serializable {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 对应作者ID
	 */
	@Column(name = "post_author")
	private Integer postAuthor;
	@Column(name = "seo_keywords")
	private String seoKeywords;
	/**
	 * 正文
	 */
	@Column(name = "post_content")
	private String postContent;

	@Column(name = "post_title")
	private String postTitle;

	/**
	 * 摘录
	 */
	@Column(name = "post_excerpt")
	private String postExcerpt;

	/**
	 * 文章状态（publish/auto-draft/inherit等）
	 */
	@Column(name = "post_status")
	private String postStatus;

	/**
	 * 评论状态（open/closed）
	 */
	@Column(name = "comment_status")
	private String commentStatus;

	/**
	 * PING状态（open/closed）
	 */
	@Column(name = "ping_status")
	private String pingStatus;

	/**
	 * 文章密码
	 */
	@Column(name = "post_password")
	private String postPassword;

	@Column(name = "post_name")
	private String postName;

	/**
	 * 已经PING过的链接
	 */
	@Column(name = "pinged")
	private String pinged;

	/**
	 * 父文章，主要用于PAGE,也用于描述导航菜单的父子关系
	 */
	@Column(name = "post_parent")
	private Integer postParent;

	/**
	 * 排序ID
	 */
	@Column(name = "post_order")
	private Integer postOrder;
	/**
	 * 点击量 
	 */
	@Column(name="view_counts")
	private Integer viewCounts;
	/**
	 * 销量 
	 */
	@Column(name="sale_counts")
	private Integer saleCounts;
	@Column(name="price")
    private BigDecimal price;
	@Column(name="origin_price")
    private BigDecimal originPrice;
	/**
	 * 库存 
	 */
	@Column(name="stocks")
	private Integer stocks;
	/**
	 * 文章类型（post/page/nav_menu_item等）
	 */
	@Column(name = "post_type")
	private String postType;
	@Column(name = "template")
	private String template;
	@Column(name="img_preview")
	private String imgPreview;

	/**
	 * MIME类型或者在存储菜单时用于存储分类法字段taxonomy 
	 */
	@Column(name = "post_mime_type")
	private String postMimeType;

	/**
	 * 评论总数
	 */
	@Column(name = "comment_count")
	private Integer commentCount;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "update_time")
	private Date updateTime;
	private List<Post> children;
	

	private static final long serialVersionUID = 1L;

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	public Integer getStocks() {
		return stocks;
	}

	public void setStocks(Integer stocks) {
		this.stocks = stocks;
	}

	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取对应作者ID
	 *
	 * @return post_author - 对应作者ID
	 */
	public Integer getPostAuthor() {
		return postAuthor;
	}

	/**
	 * 设置对应作者ID
	 *
	 * @param postAuthor 对应作者ID
	 */
	public void setPostAuthor(Integer postAuthor) {
		this.postAuthor = postAuthor;
	}

	/**
	 * 获取正文
	 *
	 * @return post_content - 正文
	 */
	public String getPostContent() {
		return postContent;
	}

	/**
	 * 设置正文
	 *
	 * @param postContent 正文
	 */
	public void setPostContent(String postContent) {
		this.postContent = postContent == null ? null : postContent.trim();
	}

	/**
	 * @return post_title
	 */
	public String getPostTitle() {
		return postTitle;
	}

	/**
	 * @param postTitle
	 */
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle == null ? null : postTitle.trim();
	}

	/**
	 * 获取摘录
	 *
	 * @return post_excerpt - 摘录
	 */
	public String getPostExcerpt() {
		return postExcerpt;
	}

	/**
	 * 设置摘录
	 *
	 * @param postExcerpt 摘录
	 */
	public void setPostExcerpt(String postExcerpt) {
		this.postExcerpt = postExcerpt == null ? null : postExcerpt.trim();
	}

	/**
	 * 获取文章状态（publish/auto-draft/inherit等）
	 *
	 * @return post_status - 文章状态（publish/auto-draft/inherit等）
	 */
	public String getPostStatus() {
		return postStatus;
	}

	/**
	 * 设置文章状态（publish/auto-draft/inherit等）
	 *
	 * @param postStatus 文章状态（publish/auto-draft/inherit等）
	 */
	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus == null ? null : postStatus.trim();
	}

	/**
	 * 获取评论状态（open/closed）
	 *
	 * @return comment_status - 评论状态（open/closed）
	 */
	public String getCommentStatus() {
		return commentStatus;
	}

	/**
	 * 设置评论状态（open/closed）
	 *
	 * @param commentStatus 评论状态（open/closed）
	 */
	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus == null ? null : commentStatus.trim();
	}

	/**
	 * 获取PING状态（open/closed）
	 *
	 * @return ping_status - PING状态（open/closed）
	 */
	public String getPingStatus() {
		return pingStatus;
	}

	/**
	 * 设置PING状态（open/closed）
	 *
	 * @param pingStatus PING状态（open/closed）
	 */
	public void setPingStatus(String pingStatus) {
		this.pingStatus = pingStatus == null ? null : pingStatus.trim();
	}

	/**
	 * 获取文章密码
	 *
	 * @return post_password - 文章密码
	 */
	public String getPostPassword() {
		return postPassword;
	}

	/**
	 * 设置文章密码
	 *
	 * @param postPassword 文章密码
	 */
	public void setPostPassword(String postPassword) {
		this.postPassword = postPassword == null ? null : postPassword.trim();
	}

	/**
	 * @return post_name
	 */
	public String getPostName() {
		return postName;
	}

	/**
	 * @param postName
	 */
	public void setPostName(String postName) {
		this.postName = postName == null ? null : postName.trim();
	}

	/**
	 * 获取已经PING过的链接
	 *
	 * @return pinged - 已经PING过的链接
	 */
	public String getPinged() {
		return pinged;
	}

	/**
	 * 设置已经PING过的链接
	 *
	 * @param pinged 已经PING过的链接
	 */
	public void setPinged(String pinged) {
		this.pinged = pinged == null ? null : pinged.trim();
	}

	/**
	 * 获取父文章，主要用于PAGE
	 *
	 * @return post_parent - 父文章，主要用于PAGE
	 */
	public Integer getPostParent() {
		return postParent;
	}

	/**
	 * 设置父文章，主要用于PAGE
	 *
	 * @param postParent 父文章，主要用于PAGE
	 */
	public void setPostParent(Integer postParent) {
		this.postParent = postParent == null ? null : postParent;
	}

	/**
	 * 获取排序ID
	 *
	 * @return post_order - 排序ID
	 */
	public Integer getPostOrder() {
		return postOrder;
	}

	/**
	 * 设置排序ID
	 *
	 * @param postOrder 排序ID
	 */
	public void setPostOrder(Integer postOrder) {
		this.postOrder = postOrder;
	}

	/**
	 * 获取文章类型（post/page等）
	 *
	 * @return post_type - 文章类型（post/page等）
	 */
	public String getPostType() {
		return postType;
	}

	/**
	 * 设置文章类型（post/page等）
	 *
	 * @param postType 文章类型（post/page等）
	 */
	public void setPostType(String postType) {
		this.postType = postType == null ? null : postType.trim();
	}

	/**
	 * 获取MIME类型
	 *
	 * @return post_mime_type - MIME类型
	 */
	public String getPostMimeType() {
		return postMimeType;
	}

	/**
	 * 设置MIME类型
	 *
	 * @param postMimeType MIME类型
	 */
	public void setPostMimeType(String postMimeType) {
		this.postMimeType = postMimeType == null ? null : postMimeType.trim();
	}

	/**
	 * 获取评论总数
	 *
	 * @return comment_count - 评论总数
	 */
	public Integer getCommentCount() {
		return commentCount;
	}

	/**
	 * 设置评论总数
	 *
	 * @param commentCount 评论总数
	 */
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
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

	public String getSeoKeywords() {
		return seoKeywords;
	}

	public void setSeoKeywords(String seoKeywords) {
		this.seoKeywords = seoKeywords;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Integer getViewCounts() {
		return viewCounts;
	}

	public void setViewCounts(Integer viewCounts) {
		this.viewCounts = viewCounts;
	}

	public String getImgPreview() {
		return imgPreview;
	}

	public void setImgPreview(String imgPreview) {
		this.imgPreview = imgPreview;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getOriginPrice() {
		return originPrice;
	}

	public void setOriginPrice(BigDecimal originPrice) {
		this.originPrice = originPrice;
	}

	public List<Post> getChildren() {
		return children;
	}

	public void setChildren(List<Post> children) {
		this.children = children;
	}

	public Integer getSaleCounts() {
		return saleCounts;
	}

	public void setSaleCounts(Integer saleCounts) {
		this.saleCounts = saleCounts;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", postAuthor=").append(postAuthor);
		sb.append(", postContent=").append(postContent);
		sb.append(", postTitle=").append(postTitle);
		sb.append(", postExcerpt=").append(postExcerpt);
		sb.append(", postStatus=").append(postStatus);
		sb.append(", commentStatus=").append(commentStatus);
		sb.append(", pingStatus=").append(pingStatus);
		sb.append(", postPassword=").append(postPassword);
		sb.append(", postName=").append(postName);
		sb.append(", pinged=").append(pinged);
		sb.append(", postParent=").append(postParent);
		sb.append(", postOrder=").append(postOrder);
		sb.append(", postType=").append(postType);
		sb.append(", postMimeType=").append(postMimeType);
		sb.append(", commentCount=").append(commentCount);
		sb.append(", viewCounts=").append(viewCounts);
		sb.append(", createTime=").append(createTime);
		sb.append(", updateTime=").append(updateTime);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}