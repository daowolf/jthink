package com.jthink.shop.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "jk_member_order")
public class MemberOrder implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单号
     */
    @Column(name = "ns")
    private String ns;

    /**
     * 商品的类型
     */
    @Column(name = "product_type")
    private String productType;

    /**
     * 商品的名称
     */
    @Column(name = "order_title")
    private String orderTitle;
    /**
     * 订单备注 
     */
    @Column(name = "order_summary")
    private String orderSummary;

    /**
     * 购买人
     */
    @Column(name = "buyer_id")
    private Integer buyerId;

    /**
     * 购买人昵称
     */
    @Column(name = "buyer_nickname")
    private String buyerNickname;

    /**
     * 用户留言
     */
    @Column(name = "buyer_msg")
    private String buyerMsg;

    /**
     * 快递费
     */
    @Column(name = "delivery_fee")
    private BigDecimal deliveryFee;

    /**
     * 订单总金额，购买人员应该付款的金额(包含快递费delivery_fee)
     */
    @Column(name = "order_total_amount")
    private BigDecimal orderTotalAmount;

    /**
     * 订单的真实金额，销售人员可以在后台修改支付金额，一般情况下 order_real_amount = order_total_amount
     */
    @Column(name = "order_real_amount")
    private BigDecimal orderRealAmount;

    /**
     * 优惠券ID
     */
    @Column(name = "coupon_user_id")
    private Integer couponUserId;

    /**
     * 优惠金额
     */
    @Column(name = "coupon_amount")
    private BigDecimal couponAmount;

    /**
     * 支付状态：1未付款、 2支付宝、3微信支付 、4网银（线上支付)5、余额支付
     */
    @Column(name = "pay_status")
    private Integer payStatus;

    /**
     * 支付成功的金额
     */
    @Column(name = "pay_success_amount")
    private BigDecimal paySuccessAmount;

    /**
     * 支付时间
     */
    @Column(name = "pay_success_time")
    private Date paySuccessTime;

    /**
     * 支付证明，手动入账时需要截图
     */
    @Column(name = "pay_success_proof")
    private String paySuccessProof;

    /**
     * 支付备注
     */
    @Column(name = "pay_success_remarks")
    private String paySuccessRemarks;

    /**
     * 支付记录
     */
    @Column(name = "payment_id")
    private Integer paymentId;

    /**
     * 第三方订单号
     */
    @Column(name = "payment_outer_id")
    private String paymentOuterId;

    /**
     * 第三方支付用户，一般情况下是用户的openId
     */
    @Column(name = "payment_outer_user")
    private String paymentOuterUser;

    /**
     * 收货人地址
     */
    @Column(name = "delivery_addr_username")
    private String deliveryAddrUsername;

    /**
     * 收货人手机号（电话）
     */
    @Column(name = "delivery_addr_mobile")
    private String deliveryAddrMobile;

    /**
     * 收件人省
     */
    @Column(name = "delivery_addr_province")
    private String deliveryAddrProvince;

    /**
     * 收件人的城市
     */
    @Column(name = "delivery_addr_city")
    private String deliveryAddrCity;

    /**
     * 收件人的区（县）
     */
    @Column(name = "delivery_addr_district")
    private String deliveryAddrDistrict;

    /**
     * 收件人的详细地址
     */
    @Column(name = "delivery_addr_detail")
    private String deliveryAddrDetail;

    /**
     * 收件人地址邮政编码
     */
    @Column(name = "delivery_addr_zipcode")
    private String deliveryAddrZipcode;

    /**
     * 发票
     */
    @Column(name = "invoice_id")
    private Long invoiceId;

    /**
     * 发票开具状态：1 未申请发票、 2 发票申请中、 3 发票开具中、 8 无需开具发票、 9发票已经开具
     */
    @Column(name = "invoice_status")
    private Integer invoiceStatus;

    /**
     * 交易状态：1交易中、 2交易完成（但是可以申请退款） 、3取消交易 、4申请退款、 5拒绝退款、 6退款中、 7退款完成、 9交易结束
     */
    @Column(name = "trade_status")
    private Integer tradeStatus;

    /**
     * 删除状态：1 正常 ，2 回收站 3 已经删除
     */
    @Column(name = "del_status")
    private Integer delStatus;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 管理员后台备注
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
     * 获取订单号
     *
     * @return ns - 订单号
     */
    public String getNs() {
        return ns;
    }

    /**
     * 设置订单号
     *
     * @param ns 订单号
     */
    public void setNs(String ns) {
        this.ns = ns == null ? null : ns.trim();
    }

    /**
     * 获取商品的类型
     *
     * @return product_type - 商品的类型
     */
    public String getProductType() {
        return productType;
    }

    /**
     * 设置商品的类型
     *
     * @param productType 商品的类型
     */
    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    /**
     * 获取商品的名称
     *
     * @return order_title - 商品的名称
     */
    public String getOrderTitle() {
        return orderTitle;
    }

    /**
     * 设置商品的名称
     *
     * @param orderTitle 商品的名称
     */
    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle == null ? null : orderTitle.trim();
    }

    /**
     * @return order_summary
     */
    public String getOrderSummary() {
        return orderSummary;
    }

    /**
     * @param orderSummary
     */
    public void setOrderSummary(String orderSummary) {
        this.orderSummary = orderSummary == null ? null : orderSummary.trim();
    }

    /**
     * 获取购买人
     *
     * @return buyer_id - 购买人
     */
    public Integer getBuyerId() {
        return buyerId;
    }

    /**
     * 设置购买人
     *
     * @param buyerId 购买人
     */
    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * 获取购买人昵称
     *
     * @return buyer_nickname - 购买人昵称
     */
    public String getBuyerNickname() {
        return buyerNickname;
    }

    /**
     * 设置购买人昵称
     *
     * @param buyerNickname 购买人昵称
     */
    public void setBuyerNickname(String buyerNickname) {
        this.buyerNickname = buyerNickname == null ? null : buyerNickname.trim();
    }

    /**
     * 获取用户留言
     *
     * @return buyer_msg - 用户留言
     */
    public String getBuyerMsg() {
        return buyerMsg;
    }

    /**
     * 设置用户留言
     *
     * @param buyerMsg 用户留言
     */
    public void setBuyerMsg(String buyerMsg) {
        this.buyerMsg = buyerMsg == null ? null : buyerMsg.trim();
    }

    /**
     * 获取快递费
     *
     * @return delivery_fee - 快递费
     */
    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    /**
     * 设置快递费
     *
     * @param deliveryFee 快递费
     */
    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    /**
     * 获取订单总金额，购买人员应该付款的金额(包含快递费delivery_fee)
     *
     * @return order_total_amount - 订单总金额，购买人员应该付款的金额(包含快递费delivery_fee)
     */
    public BigDecimal getOrderTotalAmount() {
        return orderTotalAmount;
    }

    /**
     * 设置订单总金额，购买人员应该付款的金额(包含快递费delivery_fee)
     *
     * @param orderTotalAmount 订单总金额，购买人员应该付款的金额(包含快递费delivery_fee)
     */
    public void setOrderTotalAmount(BigDecimal orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    /**
     * 获取订单的真实金额，销售人员可以在后台修改支付金额，一般情况下 order_real_amount = order_total_amount
     *
     * @return order_real_amount - 订单的真实金额，销售人员可以在后台修改支付金额，一般情况下 order_real_amount = order_total_amount
     */
    public BigDecimal getOrderRealAmount() {
        return orderRealAmount;
    }

    /**
     * 设置订单的真实金额，销售人员可以在后台修改支付金额，一般情况下 order_real_amount = order_total_amount
     *
     * @param orderRealAmount 订单的真实金额，销售人员可以在后台修改支付金额，一般情况下 order_real_amount = order_total_amount
     */
    public void setOrderRealAmount(BigDecimal orderRealAmount) {
        this.orderRealAmount = orderRealAmount;
    }

    /**
     * 获取优惠券ID
     *
     * @return coupon_user_id - 优惠券ID
     */
    public Integer getCouponUserId() {
        return couponUserId;
    }

    /**
     * 设置优惠券ID
     *
     * @param couponUserId 优惠券ID
     */
    public void setCouponUserId(Integer couponUserId) {
        this.couponUserId = couponUserId;
    }

    /**
     * 获取优惠金额
     *
     * @return coupon_amount - 优惠金额
     */
    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    /**
     * 设置优惠金额
     *
     * @param couponAmount 优惠金额
     */
    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    /**
     * 获取支付状态：1未付款、 2支付宝、3微信支付 、4网银（线上支付)5、余额支付
     *
     * @return pay_status - 支付状态：1未付款、 2支付宝、3微信支付 、4网银（线上支付)5、余额支付
     */
    public Integer getPayStatus() {
        return payStatus;
    }

    /**
     * 设置支付状态：1未付款、 2支付宝、3微信支付 、4网银（线上支付)5、余额支付
     *
     * @param payStatus 支付状态：1未付款、 2支付宝、3微信支付 、4网银（线上支付)5、余额支付
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 获取支付成功的金额
     *
     * @return pay_success_amount - 支付成功的金额
     */
    public BigDecimal getPaySuccessAmount() {
        return paySuccessAmount;
    }

    /**
     * 设置支付成功的金额
     *
     * @param paySuccessAmount 支付成功的金额
     */
    public void setPaySuccessAmount(BigDecimal paySuccessAmount) {
        this.paySuccessAmount = paySuccessAmount;
    }

    /**
     * 获取支付时间
     *
     * @return pay_success_time - 支付时间
     */
    public Date getPaySuccessTime() {
        return paySuccessTime;
    }

    /**
     * 设置支付时间
     *
     * @param paySuccessTime 支付时间
     */
    public void setPaySuccessTime(Date paySuccessTime) {
        this.paySuccessTime = paySuccessTime;
    }

    /**
     * 获取支付证明，手动入账时需要截图
     *
     * @return pay_success_proof - 支付证明，手动入账时需要截图
     */
    public String getPaySuccessProof() {
        return paySuccessProof;
    }

    /**
     * 设置支付证明，手动入账时需要截图
     *
     * @param paySuccessProof 支付证明，手动入账时需要截图
     */
    public void setPaySuccessProof(String paySuccessProof) {
        this.paySuccessProof = paySuccessProof == null ? null : paySuccessProof.trim();
    }

    /**
     * 获取支付备注
     *
     * @return pay_success_remarks - 支付备注
     */
    public String getPaySuccessRemarks() {
        return paySuccessRemarks;
    }

    /**
     * 设置支付备注
     *
     * @param paySuccessRemarks 支付备注
     */
    public void setPaySuccessRemarks(String paySuccessRemarks) {
        this.paySuccessRemarks = paySuccessRemarks == null ? null : paySuccessRemarks.trim();
    }

    /**
     * 获取支付记录
     *
     * @return payment_id - 支付记录
     */
    public Integer getPaymentId() {
        return paymentId;
    }

    /**
     * 设置支付记录
     *
     * @param paymentId 支付记录
     */
    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * 获取第三方订单号
     *
     * @return payment_outer_id - 第三方订单号
     */
    public String getPaymentOuterId() {
        return paymentOuterId;
    }

    /**
     * 设置第三方订单号
     *
     * @param paymentOuterId 第三方订单号
     */
    public void setPaymentOuterId(String paymentOuterId) {
        this.paymentOuterId = paymentOuterId == null ? null : paymentOuterId.trim();
    }

    /**
     * 获取第三方支付用户，一般情况下是用户的openId
     *
     * @return payment_outer_user - 第三方支付用户，一般情况下是用户的openId
     */
    public String getPaymentOuterUser() {
        return paymentOuterUser;
    }

    /**
     * 设置第三方支付用户，一般情况下是用户的openId
     *
     * @param paymentOuterUser 第三方支付用户，一般情况下是用户的openId
     */
    public void setPaymentOuterUser(String paymentOuterUser) {
        this.paymentOuterUser = paymentOuterUser == null ? null : paymentOuterUser.trim();
    }

    /**
     * 获取收货人地址
     *
     * @return delivery_addr_username - 收货人地址
     */
    public String getDeliveryAddrUsername() {
        return deliveryAddrUsername;
    }

    /**
     * 设置收货人地址
     *
     * @param deliveryAddrUsername 收货人地址
     */
    public void setDeliveryAddrUsername(String deliveryAddrUsername) {
        this.deliveryAddrUsername = deliveryAddrUsername == null ? null : deliveryAddrUsername.trim();
    }

    /**
     * 获取收货人手机号（电话）
     *
     * @return delivery_addr_mobile - 收货人手机号（电话）
     */
    public String getDeliveryAddrMobile() {
        return deliveryAddrMobile;
    }

    /**
     * 设置收货人手机号（电话）
     *
     * @param deliveryAddrMobile 收货人手机号（电话）
     */
    public void setDeliveryAddrMobile(String deliveryAddrMobile) {
        this.deliveryAddrMobile = deliveryAddrMobile == null ? null : deliveryAddrMobile.trim();
    }

    /**
     * 获取收件人省
     *
     * @return delivery_addr_province - 收件人省
     */
    public String getDeliveryAddrProvince() {
        return deliveryAddrProvince;
    }

    /**
     * 设置收件人省
     *
     * @param deliveryAddrProvince 收件人省
     */
    public void setDeliveryAddrProvince(String deliveryAddrProvince) {
        this.deliveryAddrProvince = deliveryAddrProvince == null ? null : deliveryAddrProvince.trim();
    }

    /**
     * 获取收件人的城市
     *
     * @return delivery_addr_city - 收件人的城市
     */
    public String getDeliveryAddrCity() {
        return deliveryAddrCity;
    }

    /**
     * 设置收件人的城市
     *
     * @param deliveryAddrCity 收件人的城市
     */
    public void setDeliveryAddrCity(String deliveryAddrCity) {
        this.deliveryAddrCity = deliveryAddrCity == null ? null : deliveryAddrCity.trim();
    }

    /**
     * 获取收件人的区（县）
     *
     * @return delivery_addr_district - 收件人的区（县）
     */
    public String getDeliveryAddrDistrict() {
        return deliveryAddrDistrict;
    }

    /**
     * 设置收件人的区（县）
     *
     * @param deliveryAddrDistrict 收件人的区（县）
     */
    public void setDeliveryAddrDistrict(String deliveryAddrDistrict) {
        this.deliveryAddrDistrict = deliveryAddrDistrict == null ? null : deliveryAddrDistrict.trim();
    }

    /**
     * 获取收件人的详细地址
     *
     * @return delivery_addr_detail - 收件人的详细地址
     */
    public String getDeliveryAddrDetail() {
        return deliveryAddrDetail;
    }

    /**
     * 设置收件人的详细地址
     *
     * @param deliveryAddrDetail 收件人的详细地址
     */
    public void setDeliveryAddrDetail(String deliveryAddrDetail) {
        this.deliveryAddrDetail = deliveryAddrDetail == null ? null : deliveryAddrDetail.trim();
    }

    /**
     * 获取收件人地址邮政编码
     *
     * @return delivery_addr_zipcode - 收件人地址邮政编码
     */
    public String getDeliveryAddrZipcode() {
        return deliveryAddrZipcode;
    }

    /**
     * 设置收件人地址邮政编码
     *
     * @param deliveryAddrZipcode 收件人地址邮政编码
     */
    public void setDeliveryAddrZipcode(String deliveryAddrZipcode) {
        this.deliveryAddrZipcode = deliveryAddrZipcode == null ? null : deliveryAddrZipcode.trim();
    }

    /**
     * 获取发票
     *
     * @return invoice_id - 发票
     */
    public Long getInvoiceId() {
        return invoiceId;
    }

    /**
     * 设置发票
     *
     * @param invoiceId 发票
     */
    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    /**
     * 获取发票开具状态：1 未申请发票、 2 发票申请中、 3 发票开具中、 8 无需开具发票、 9发票已经开具
     *
     * @return invoice_status - 发票开具状态：1 未申请发票、 2 发票申请中、 3 发票开具中、 8 无需开具发票、 9发票已经开具
     */
    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    /**
     * 设置发票开具状态：1 未申请发票、 2 发票申请中、 3 发票开具中、 8 无需开具发票、 9发票已经开具
     *
     * @param invoiceStatus 发票开具状态：1 未申请发票、 2 发票申请中、 3 发票开具中、 8 无需开具发票、 9发票已经开具
     */
    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    /**
     * 获取交易状态：1交易中、 2交易完成（但是可以申请退款） 、3取消交易 、4申请退款、 5拒绝退款、 6退款中、 7退款完成、 9交易结束
     *
     * @return trade_status - 交易状态：1交易中、 2交易完成（但是可以申请退款） 、3取消交易 、4申请退款、 5拒绝退款、 6退款中、 7退款完成、 9交易结束
     */
    public Integer getTradeStatus() {
        return tradeStatus;
    }

    /**
     * 设置交易状态：1交易中、 2交易完成（但是可以申请退款） 、3取消交易 、4申请退款、 5拒绝退款、 6退款中、 7退款完成、 9交易结束
     *
     * @param tradeStatus 交易状态：1交易中、 2交易完成（但是可以申请退款） 、3取消交易 、4申请退款、 5拒绝退款、 6退款中、 7退款完成、 9交易结束
     */
    public void setTradeStatus(Integer tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    /**
     * 获取删除状态：1 正常 ，2 回收站 3 已经删除
     *
     * @return del_status - 删除状态：1 正常 ，2 回收站 3 已经删除
     */
    public Integer getDelStatus() {
        return delStatus;
    }

    /**
     * 设置删除状态：1 正常 ，2 回收站 3 已经删除
     *
     * @param delStatus 删除状态：1 正常 ，2 回收站 3 已经删除
     */
    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }


    public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
     * 获取管理员后台备注
     *
     * @return remarks - 管理员后台备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置管理员后台备注
     *
     * @param remarks 管理员后台备注
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
        sb.append(", ns=").append(ns);
        sb.append(", productType=").append(productType);
        sb.append(", orderTitle=").append(orderTitle);
        sb.append(", orderSummary=").append(orderSummary);
        sb.append(", buyerId=").append(buyerId);
        sb.append(", buyerNickname=").append(buyerNickname);
        sb.append(", buyerMsg=").append(buyerMsg);
        sb.append(", deliveryFee=").append(deliveryFee);
        sb.append(", orderTotalAmount=").append(orderTotalAmount);
        sb.append(", orderRealAmount=").append(orderRealAmount);
        sb.append(", couponUserId=").append(couponUserId);
        sb.append(", couponAmount=").append(couponAmount);
        sb.append(", payStatus=").append(payStatus);
        sb.append(", paySuccessAmount=").append(paySuccessAmount);
        sb.append(", paySuccessTime=").append(paySuccessTime);
        sb.append(", paySuccessProof=").append(paySuccessProof);
        sb.append(", paySuccessRemarks=").append(paySuccessRemarks);
        sb.append(", paymentId=").append(paymentId);
        sb.append(", paymentOuterId=").append(paymentOuterId);
        sb.append(", paymentOuterUser=").append(paymentOuterUser);
        sb.append(", deliveryAddrUsername=").append(deliveryAddrUsername);
        sb.append(", deliveryAddrMobile=").append(deliveryAddrMobile);
        sb.append(", deliveryAddrProvince=").append(deliveryAddrProvince);
        sb.append(", deliveryAddrCity=").append(deliveryAddrCity);
        sb.append(", deliveryAddrDistrict=").append(deliveryAddrDistrict);
        sb.append(", deliveryAddrDetail=").append(deliveryAddrDetail);
        sb.append(", deliveryAddrZipcode=").append(deliveryAddrZipcode);
        sb.append(", invoiceId=").append(invoiceId);
        sb.append(", invoiceStatus=").append(invoiceStatus);
        sb.append(", tradeStatus=").append(tradeStatus);
        sb.append(", delStatus=").append(delStatus);
        sb.append(", modifiedTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", remarks=").append(remarks);
        sb.append(", options=").append(options);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}