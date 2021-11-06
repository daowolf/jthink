package com.jthink.shop.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "jk_member_order_invoice")
public class OrderInvoice implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 发票类型(普通发票、增值税专用发票)
     */
    @Column(name = "type")
    private String type;

    /**
     * 发票抬头
     */
    @Column(name = "title")
    private String title;

    /**
     * 发票内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 纳税人识别号
     */
    @Column(name = "identity")
    private String identity;

    /**
     * 单位名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 发票收取人手机号
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 发票收取人邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 发票状态
     */
    @Column(name = "invoice_status")
    private Byte invoiceStatus;

    /**
     * 修改时间
     */
    @Column(name = "modified_time")
    private Date modifiedTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * json字段扩展
     */
    @Column(name = "options")
    private String options;

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
     * 获取发票类型(普通发票、增值税专用发票)
     *
     * @return type - 发票类型(普通发票、增值税专用发票)
     */
    public String getType() {
        return type;
    }

    /**
     * 设置发票类型(普通发票、增值税专用发票)
     *
     * @param type 发票类型(普通发票、增值税专用发票)
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取发票抬头
     *
     * @return title - 发票抬头
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置发票抬头
     *
     * @param title 发票抬头
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取发票内容
     *
     * @return content - 发票内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置发票内容
     *
     * @param content 发票内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取纳税人识别号
     *
     * @return identity - 纳税人识别号
     */
    public String getIdentity() {
        return identity;
    }

    /**
     * 设置纳税人识别号
     *
     * @param identity 纳税人识别号
     */
    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }

    /**
     * 获取单位名称
     *
     * @return name - 单位名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置单位名称
     *
     * @param name 单位名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取发票收取人手机号
     *
     * @return mobile - 发票收取人手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置发票收取人手机号
     *
     * @param mobile 发票收取人手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 获取发票收取人邮箱
     *
     * @return email - 发票收取人邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置发票收取人邮箱
     *
     * @param email 发票收取人邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取发票状态
     *
     * @return invoice_status - 发票状态
     */
    public Byte getInvoiceStatus() {
        return invoiceStatus;
    }

    /**
     * 设置发票状态
     *
     * @param invoiceStatus 发票状态
     */
    public void setInvoiceStatus(Byte invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    /**
     * 获取修改时间
     *
     * @return modified_time - 修改时间
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifiedTime 修改时间
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
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
     * 获取json字段扩展
     *
     * @return options - json字段扩展
     */
    public String getOptions() {
        return options;
    }

    /**
     * 设置json字段扩展
     *
     * @param options json字段扩展
     */
    public void setOptions(String options) {
        this.options = options == null ? null : options.trim();
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
        sb.append(", content=").append(content);
        sb.append(", identity=").append(identity);
        sb.append(", name=").append(name);
        sb.append(", mobile=").append(mobile);
        sb.append(", email=").append(email);
        sb.append(", invoiceStatus=").append(invoiceStatus);
        sb.append(", modifiedTime=").append(modifiedTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", options=").append(options);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}