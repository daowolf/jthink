package com.jthink.shop.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "jk_member_order_delivery")
public class OrderDelivery implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 操作用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 操作用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 快递公司ID
     */
    @Column(name = "delivery_id")
    private Integer deliveryId;

    /**
     * 快递公司
     */
    @Column(name = "company")
    private String company;

    /**
     * 快递单号
     */
    @Column(name = "delivery_number")
    private String deliveryNumber;

    /**
     * 快递发货时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 快递送达时间
     */
    @Column(name = "finish_time")
    private Date finishTime;

    /**
     * 收货人地址
     */
    @Column(name = "addr_username")
    private String addrUsername;

    /**
     * 收货人手机号（电话）
     */
    @Column(name = "addr_mobile")
    private String addrMobile;

    /**
     * 收件人省
     */
    @Column(name = "addr_province")
    private String addrProvince;

    /**
     * 收件人的城市
     */
    @Column(name = "addr_city")
    private String addrCity;

    /**
     * 收件人的区（县）
     */
    @Column(name = "addr_district")
    private String addrDistrict;

    /**
     * 收件人的详细地址
     */
    @Column(name = "addr_detail")
    private String addrDetail;

    /**
     * 收件人地址邮政编码
     */
    @Column(name = "addr_zipcode")
    private String addrZipcode;

    /**
     * 发货状态1.已发货(待运输)2.运输中3.已收货
     */
    @Column(name = "delivery_status")
    private Byte deliveryStatus;

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
     * 运输信息备注
     */
    @Column(name = "remarks")
    private String remarks;

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
     * 获取操作用户名
     *
     * @return username - 操作用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置操作用户名
     *
     * @param username 操作用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取操作用户ID
     *
     * @return user_id - 操作用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置操作用户ID
     *
     * @param userId 操作用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取订单ID
     *
     * @return order_id - 订单ID
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单ID
     *
     * @param orderId 订单ID
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取快递公司ID
     *
     * @return delivery_id - 快递公司ID
     */
    public Integer getDeliveryId() {
        return deliveryId;
    }

    /**
     * 设置快递公司ID
     *
     * @param deliveryId 快递公司ID
     */
    public void setDeliveryId(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    /**
     * 获取快递公司
     *
     * @return company - 快递公司
     */
    public String getCompany() {
        return company;
    }

    /**
     * 设置快递公司
     *
     * @param company 快递公司
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    /**
     * 获取快递单号
     *
     * @return delivery_number - 快递单号
     */
    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    /**
     * 设置快递单号
     *
     * @param deliveryNumber 快递单号
     */
    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber == null ? null : deliveryNumber.trim();
    }

    /**
     * 获取快递发货时间
     *
     * @return start_time - 快递发货时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置快递发货时间
     *
     * @param startTime 快递发货时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取快递送达时间
     *
     * @return finish_time - 快递送达时间
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * 设置快递送达时间
     *
     * @param finishTime 快递送达时间
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * 获取收货人地址
     *
     * @return addr_username - 收货人地址
     */
    public String getAddrUsername() {
        return addrUsername;
    }

    /**
     * 设置收货人地址
     *
     * @param addrUsername 收货人地址
     */
    public void setAddrUsername(String addrUsername) {
        this.addrUsername = addrUsername == null ? null : addrUsername.trim();
    }

    /**
     * 获取收货人手机号（电话）
     *
     * @return addr_mobile - 收货人手机号（电话）
     */
    public String getAddrMobile() {
        return addrMobile;
    }

    /**
     * 设置收货人手机号（电话）
     *
     * @param addrMobile 收货人手机号（电话）
     */
    public void setAddrMobile(String addrMobile) {
        this.addrMobile = addrMobile == null ? null : addrMobile.trim();
    }

    /**
     * 获取收件人省
     *
     * @return addr_province - 收件人省
     */
    public String getAddrProvince() {
        return addrProvince;
    }

    /**
     * 设置收件人省
     *
     * @param addrProvince 收件人省
     */
    public void setAddrProvince(String addrProvince) {
        this.addrProvince = addrProvince == null ? null : addrProvince.trim();
    }

    /**
     * 获取收件人的城市
     *
     * @return addr_city - 收件人的城市
     */
    public String getAddrCity() {
        return addrCity;
    }

    /**
     * 设置收件人的城市
     *
     * @param addrCity 收件人的城市
     */
    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity == null ? null : addrCity.trim();
    }

    /**
     * 获取收件人的区（县）
     *
     * @return addr_district - 收件人的区（县）
     */
    public String getAddrDistrict() {
        return addrDistrict;
    }

    /**
     * 设置收件人的区（县）
     *
     * @param addrDistrict 收件人的区（县）
     */
    public void setAddrDistrict(String addrDistrict) {
        this.addrDistrict = addrDistrict == null ? null : addrDistrict.trim();
    }

    /**
     * 获取收件人的详细地址
     *
     * @return addr_detail - 收件人的详细地址
     */
    public String getAddrDetail() {
        return addrDetail;
    }

    /**
     * 设置收件人的详细地址
     *
     * @param addrDetail 收件人的详细地址
     */
    public void setAddrDetail(String addrDetail) {
        this.addrDetail = addrDetail == null ? null : addrDetail.trim();
    }

    /**
     * 获取收件人地址邮政编码
     *
     * @return addr_zipcode - 收件人地址邮政编码
     */
    public String getAddrZipcode() {
        return addrZipcode;
    }

    /**
     * 设置收件人地址邮政编码
     *
     * @param addrZipcode 收件人地址邮政编码
     */
    public void setAddrZipcode(String addrZipcode) {
        this.addrZipcode = addrZipcode == null ? null : addrZipcode.trim();
    }

    /**
     * 获取发货状态1.已发货(待运输)2.运输中3.已收货
     *
     * @return delivery_status - 发货状态1.已发货(待运输)2.运输中3.已收货
     */
    public Byte getDeliveryStatus() {
        return deliveryStatus;
    }

    /**
     * 设置发货状态1.已发货(待运输)2.运输中3.已收货
     *
     * @param deliveryStatus 发货状态1.已发货(待运输)2.运输中3.已收货
     */
    public void setDeliveryStatus(Byte deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
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
     * 获取运输信息备注
     *
     * @return remarks - 运输信息备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置运输信息备注
     *
     * @param remarks 运输信息备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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
        sb.append(", username=").append(username);
        sb.append(", userId=").append(userId);
        sb.append(", orderId=").append(orderId);
        sb.append(", deliveryId=").append(deliveryId);
        sb.append(", company=").append(company);
        sb.append(", deliveryNumber=").append(deliveryNumber);
        sb.append(", startTime=").append(startTime);
        sb.append(", finishTime=").append(finishTime);
        sb.append(", addrUsername=").append(addrUsername);
        sb.append(", addrMobile=").append(addrMobile);
        sb.append(", addrProvince=").append(addrProvince);
        sb.append(", addrCity=").append(addrCity);
        sb.append(", addrDistrict=").append(addrDistrict);
        sb.append(", addrDetail=").append(addrDetail);
        sb.append(", addrZipcode=").append(addrZipcode);
        sb.append(", deliveryStatus=").append(deliveryStatus);
        sb.append(", modifiedTime=").append(modifiedTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", remarks=").append(remarks);
        sb.append(", options=").append(options);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}