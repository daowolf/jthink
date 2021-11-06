package com.jthink.shop.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "jk_delivery_company")
public class DeliveryCompany implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 快递公司名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 联系电话
     */
    @Column(name = "telphone")
    private String telphone;

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
     * 获取快递公司名称
     *
     * @return name - 快递公司名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置快递公司名称
     *
     * @param name 快递公司名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取联系电话
     *
     * @return telphone - 联系电话
     */
    public String getTelphone() {
        return telphone;
    }

    /**
     * 设置联系电话
     *
     * @param telphone 联系电话
     */
    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
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
        sb.append(", name=").append(name);
        sb.append(", telphone=").append(telphone);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}