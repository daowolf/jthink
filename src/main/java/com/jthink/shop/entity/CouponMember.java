package com.jthink.shop.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "jk_coupon_member")
public class CouponMember implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 类型ID
     */
    @Column(name = "coupon_id")
    private Integer couponId;

    /**
     * 优惠券标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 领取用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 状态 1 有人领取、正常使用  2 未有人领取不能使用  3 已经使用，不能被再次使用  9 已经被认为标识不可用
     */
    @Column(name = "cou_status")
    private Integer couStatus;

    /**
     * 订单ID
     */
    @Column(name = "used_order_id")
    private Integer usedOrderId;

    /**
     * 订单号
     */
    @Column(name = "order_ns")
    private String orderNs;

    /**
     * 支付的ID
     */
    @Column(name = "user_payment_id")
    private Integer userPaymentId;

    /**
     * 使用时间
     */
    @Column(name = "use_time")
    private Date useTime;

    /**
     * 领取时间
     */
    @Column(name = "get_time")
    private Date getTime;

    /**
     * 如果是由后台管理员发放，则记录下由哪位管理员发放的。前台用户自己领取的，此字段将为空。
     */
    @Column(name = "send_uid")
    private Integer sendUid;

    /**
     * 创建时间，创建时可能不会有人领取
     */
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
     * 获取类型ID
     *
     * @return coupon_id - 类型ID
     */
    public Integer getCouponId() {
        return couponId;
    }

    /**
     * 设置类型ID
     *
     * @param couponId 类型ID
     */
    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    /**
     * 获取优惠券标题
     *
     * @return title - 优惠券标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置优惠券标题
     *
     * @param title 优惠券标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取领取用户ID
     *
     * @return user_id - 领取用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置领取用户ID
     *
     * @param userId 领取用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取用户名
     *
     * @return user_name - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取状态 1 有人领取、正常使用  2 未有人领取不能使用  3 已经使用，不能被再次使用  9 已经被认为标识不可用
     *
     * @return cou_status - 状态 1 有人领取、正常使用  2 未有人领取不能使用  3 已经使用，不能被再次使用  9 已经被认为标识不可用
     */
    public Integer getCouStatus() {
        return couStatus;
    }

    /**
     * 设置状态 1 有人领取、正常使用  2 未有人领取不能使用  3 已经使用，不能被再次使用  9 已经被认为标识不可用
     *
     * @param couStatus 状态 1 有人领取、正常使用  2 未有人领取不能使用  3 已经使用，不能被再次使用  9 已经被认为标识不可用
     */
    public void setCouStatus(Integer couStatus) {
        this.couStatus = couStatus;
    }

    /**
     * 获取订单ID
     *
     * @return used_order_id - 订单ID
     */
    public Integer getUsedOrderId() {
        return usedOrderId;
    }

    /**
     * 设置订单ID
     *
     * @param usedOrderId 订单ID
     */
    public void setUsedOrderId(Integer usedOrderId) {
        this.usedOrderId = usedOrderId;
    }

    /**
     * 获取订单号
     *
     * @return order_ns - 订单号
     */
    public String getOrderNs() {
        return orderNs;
    }

    /**
     * 设置订单号
     *
     * @param orderNs 订单号
     */
    public void setOrderNs(String orderNs) {
        this.orderNs = orderNs == null ? null : orderNs.trim();
    }

    /**
     * 获取支付的ID
     *
     * @return user_payment_id - 支付的ID
     */
    public Integer getUserPaymentId() {
        return userPaymentId;
    }

    /**
     * 设置支付的ID
     *
     * @param userPaymentId 支付的ID
     */
    public void setUserPaymentId(Integer userPaymentId) {
        this.userPaymentId = userPaymentId;
    }

    /**
     * 获取使用时间
     *
     * @return use_time - 使用时间
     */
    public Date getUseTime() {
        return useTime;
    }

    /**
     * 设置使用时间
     *
     * @param useTime 使用时间
     */
    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    /**
     * 获取领取时间
     *
     * @return get_time - 领取时间
     */
    public Date getGetTime() {
        return getTime;
    }

    /**
     * 设置领取时间
     *
     * @param getTime 领取时间
     */
    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

    /**
     * 获取如果是由后台管理员发放，则记录下由哪位管理员发放的。前台用户自己领取的，此字段将为空。
     *
     * @return send_uid - 如果是由后台管理员发放，则记录下由哪位管理员发放的。前台用户自己领取的，此字段将为空。
     */
    public Integer getSendUid() {
        return sendUid;
    }

    /**
     * 设置如果是由后台管理员发放，则记录下由哪位管理员发放的。前台用户自己领取的，此字段将为空。
     *
     * @param sendUid 如果是由后台管理员发放，则记录下由哪位管理员发放的。前台用户自己领取的，此字段将为空。
     */
    public void setSendUid(Integer sendUid) {
        this.sendUid = sendUid;
    }

    /**
     * 获取创建时间，创建时可能不会有人领取
     *
     * @return create_time - 创建时间，创建时可能不会有人领取
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间，创建时可能不会有人领取
     *
     * @param createTime 创建时间，创建时可能不会有人领取
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
        sb.append(", couponId=").append(couponId);
        sb.append(", title=").append(title);
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", couStatus=").append(couStatus);
        sb.append(", usedOrderId=").append(usedOrderId);
        sb.append(", orderNs=").append(orderNs);
        sb.append(", userPaymentId=").append(userPaymentId);
        sb.append(", useTime=").append(useTime);
        sb.append(", getTime=").append(getTime);
        sb.append(", sendUid=").append(sendUid);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}