package com.jthink.shop.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "jk_member_order_item")
public class OrderItem implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 订单号
     */
    @Column(name = "order_ns")
    private String orderNs;

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
     * 用户留言（备注）
     */
    @Column(name = "buyer_msg")
    private String buyerMsg;

    /**
     * 卖家id
     */
    @Column(name = "seller_id")
    private Integer sellerId;

    /**
     * 分销员
     */
    @Column(name = "dist_user_id")
    private Integer distUserId;

    /**
     * 分销金额
     */
    @Column(name = "dist_amount")
    private BigDecimal distAmount;

    /**
     * 产品id
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * 商品的类别，默认是 product ，但是未来可能是 模板、文件、视频等等...
     */
    @Column(name = "product_type")
    private String productType;

    @Column(name = "product_type_text")
    private String productTypeText;

    /**
     * 产品标题
     */
    @Column(name = "product_title")
    private String productTitle;

    @Column(name = "product_summary")
    private String productSummary;

    /**
     * 商品规格表(t_product_sku)id
     */
    @Column(name = "product_sku_id")
    private Integer productSkuId;

    /**
     * 商品规格(不同规格价格不同)
     */
    @Column(name = "product_spec")
    private String productSpec;

    /**
     * 产品缩略图
     */
    @Column(name = "product_thumbnail")
    private String productThumbnail;

    /**
     * 产品链接
     */
    @Column(name = "product_link")
    private String productLink;

    /**
     * 产品价格
     */
    @Column(name = "product_price")
    private BigDecimal productPrice;

    /**
     * 产品数量
     */
    @Column(name = "product_count")
    private Integer productCount;

    /**
     * 是否是虚拟产品，1是，0不是。虚拟产品支付完毕后立即交易完成。是虚拟产品，虚拟产品支付完毕后立即交易完成
     */
    @Column(name = "with_virtual")
    private Boolean withVirtual;

    /**
     * 是否支持退款，1支持，0不支持。
     */
    @Column(name = "with_refund")
    private Boolean withRefund;

    /**
     * 快递费
     */
    @Column(name = "delivery_cost")
    private BigDecimal deliveryCost;

    /**
     * 快递单 id
     */
    @Column(name = "delivery_id")
    private Integer deliveryId;

    /**
     * 其它费用
     */
    @Column(name = "other_cost")
    private BigDecimal otherCost;

    /**
     * 其它费用说明
     */
    @Column(name = "other_cost_remark")
    private String otherCostRemark;

    /**
     * 具体金额 = 产品价格+运费+其他费用- 分销金额
     */
    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    /**
     * 支付金额 = 产品价格+运费+其他价格
     */
    @Column(name = "pay_amount")
    private BigDecimal payAmount;

    /**
     * 查看的网址路径，访问时时，会添加orderid
     */
    @Column(name = "view_path")
    private String viewPath;

    /**
     * 查看的文章内容，比如：查看、下载
     */
    @Column(name = "view_text")
    private String viewText;

    /**
     * 可访问的有效时间，单位秒
     */
    @Column(name = "view_effective_time")
    private Integer viewEffectiveTime;

    /**
     * 评论的路径
     */
    @Column(name = "comment_path")
    private String commentPath;

    /**
     * 发票
     */
    @Column(name = "invoice_id")
    private Integer invoiceId;

    @Column(name = "invoice_status")
    private Byte invoiceStatus;

    /**
     * 状态：1交易中、 2交易完成（但是可以申请退款） 、3取消交易 、4申请退款、 5拒绝退款、 6退款中、 7退款完成、 9交易结束
     */
    @Column(name = "item_status")
    private Byte itemStatus;

    /**
     * 退款订单号
     */
    @Column(name = "refund_no")
    private String refundNo;

    /**
     * 退款金额
     */
    @Column(name = "refund_amount")
    private BigDecimal refundAmount;

    /**
     * 退款描述
     */
    @Column(name = "refund_desc")
    private String refundDesc;

    /**
     * 退款时间
     */
    @Column(name = "refund_time")
    private Date refundTime;

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
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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
     * 获取用户留言（备注）
     *
     * @return buyer_msg - 用户留言（备注）
     */
    public String getBuyerMsg() {
        return buyerMsg;
    }

    /**
     * 设置用户留言（备注）
     *
     * @param buyerMsg 用户留言（备注）
     */
    public void setBuyerMsg(String buyerMsg) {
        this.buyerMsg = buyerMsg == null ? null : buyerMsg.trim();
    }

    /**
     * 获取卖家id
     *
     * @return seller_id - 卖家id
     */
    public Integer getSellerId() {
        return sellerId;
    }

    /**
     * 设置卖家id
     *
     * @param sellerId 卖家id
     */
    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * 获取分销员
     *
     * @return dist_user_id - 分销员
     */
    public Integer getDistUserId() {
        return distUserId;
    }

    /**
     * 设置分销员
     *
     * @param distUserId 分销员
     */
    public void setDistUserId(Integer distUserId) {
        this.distUserId = distUserId;
    }

    /**
     * 获取分销金额
     *
     * @return dist_amount - 分销金额
     */
    public BigDecimal getDistAmount() {
        return distAmount;
    }

    /**
     * 设置分销金额
     *
     * @param distAmount 分销金额
     */
    public void setDistAmount(BigDecimal distAmount) {
        this.distAmount = distAmount;
    }

    /**
     * 获取产品id
     *
     * @return product_id - 产品id
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * 设置产品id
     *
     * @param productId 产品id
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取商品的类别，默认是 product ，但是未来可能是 模板、文件、视频等等...
     *
     * @return product_type - 商品的类别，默认是 product ，但是未来可能是 模板、文件、视频等等...
     */
    public String getProductType() {
        return productType;
    }

    /**
     * 设置商品的类别，默认是 product ，但是未来可能是 模板、文件、视频等等...
     *
     * @param productType 商品的类别，默认是 product ，但是未来可能是 模板、文件、视频等等...
     */
    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    /**
     * @return product_type_text
     */
    public String getProductTypeText() {
        return productTypeText;
    }

    /**
     * @param productTypeText
     */
    public void setProductTypeText(String productTypeText) {
        this.productTypeText = productTypeText == null ? null : productTypeText.trim();
    }

    /**
     * 获取产品标题
     *
     * @return product_title - 产品标题
     */
    public String getProductTitle() {
        return productTitle;
    }

    /**
     * 设置产品标题
     *
     * @param productTitle 产品标题
     */
    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle == null ? null : productTitle.trim();
    }

    /**
     * @return product_summary
     */
    public String getProductSummary() {
        return productSummary;
    }

    /**
     * @param productSummary
     */
    public void setProductSummary(String productSummary) {
        this.productSummary = productSummary == null ? null : productSummary.trim();
    }

    /**
     * 获取商品规格表(t_product_sku)id
     *
     * @return product_sku_id - 商品规格表(t_product_sku)id
     */
    public Integer getProductSkuId() {
        return productSkuId;
    }

    /**
     * 设置商品规格表(t_product_sku)id
     *
     * @param productSkuId 商品规格表(t_product_sku)id
     */
    public void setProductSkuId(Integer productSkuId) {
        this.productSkuId = productSkuId;
    }

    /**
     * 获取商品规格(不同规格价格不同)
     *
     * @return product_spec - 商品规格(不同规格价格不同)
     */
    public String getProductSpec() {
        return productSpec;
    }

    /**
     * 设置商品规格(不同规格价格不同)
     *
     * @param productSpec 商品规格(不同规格价格不同)
     */
    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec == null ? null : productSpec.trim();
    }

    /**
     * 获取产品缩略图
     *
     * @return product_thumbnail - 产品缩略图
     */
    public String getProductThumbnail() {
        return productThumbnail;
    }

    /**
     * 设置产品缩略图
     *
     * @param productThumbnail 产品缩略图
     */
    public void setProductThumbnail(String productThumbnail) {
        this.productThumbnail = productThumbnail == null ? null : productThumbnail.trim();
    }

    /**
     * 获取产品链接
     *
     * @return product_link - 产品链接
     */
    public String getProductLink() {
        return productLink;
    }

    /**
     * 设置产品链接
     *
     * @param productLink 产品链接
     */
    public void setProductLink(String productLink) {
        this.productLink = productLink == null ? null : productLink.trim();
    }

    /**
     * 获取产品价格
     *
     * @return product_price - 产品价格
     */
    public BigDecimal getProductPrice() {
        return productPrice;
    }

    /**
     * 设置产品价格
     *
     * @param productPrice 产品价格
     */
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * 获取产品数量
     *
     * @return product_count - 产品数量
     */
    public Integer getProductCount() {
        return productCount;
    }

    /**
     * 设置产品数量
     *
     * @param productCount 产品数量
     */
    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    /**
     * 获取是否是虚拟产品，1是，0不是。虚拟产品支付完毕后立即交易完成。是虚拟产品，虚拟产品支付完毕后立即交易完成
     *
     * @return with_virtual - 是否是虚拟产品，1是，0不是。虚拟产品支付完毕后立即交易完成。是虚拟产品，虚拟产品支付完毕后立即交易完成
     */
    public Boolean getWithVirtual() {
        return withVirtual;
    }

    /**
     * 设置是否是虚拟产品，1是，0不是。虚拟产品支付完毕后立即交易完成。是虚拟产品，虚拟产品支付完毕后立即交易完成
     *
     * @param withVirtual 是否是虚拟产品，1是，0不是。虚拟产品支付完毕后立即交易完成。是虚拟产品，虚拟产品支付完毕后立即交易完成
     */
    public void setWithVirtual(Boolean withVirtual) {
        this.withVirtual = withVirtual;
    }

    /**
     * 获取是否支持退款，1支持，0不支持。
     *
     * @return with_refund - 是否支持退款，1支持，0不支持。
     */
    public Boolean getWithRefund() {
        return withRefund;
    }

    /**
     * 设置是否支持退款，1支持，0不支持。
     *
     * @param withRefund 是否支持退款，1支持，0不支持。
     */
    public void setWithRefund(Boolean withRefund) {
        this.withRefund = withRefund;
    }

    /**
     * 获取快递费
     *
     * @return delivery_cost - 快递费
     */
    public BigDecimal getDeliveryCost() {
        return deliveryCost;
    }

    /**
     * 设置快递费
     *
     * @param deliveryCost 快递费
     */
    public void setDeliveryCost(BigDecimal deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    /**
     * 获取快递单 id
     *
     * @return delivery_id - 快递单 id
     */
    public Integer getDeliveryId() {
        return deliveryId;
    }

    /**
     * 设置快递单 id
     *
     * @param deliveryId 快递单 id
     */
    public void setDeliveryId(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    /**
     * 获取其它费用
     *
     * @return other_cost - 其它费用
     */
    public BigDecimal getOtherCost() {
        return otherCost;
    }

    /**
     * 设置其它费用
     *
     * @param otherCost 其它费用
     */
    public void setOtherCost(BigDecimal otherCost) {
        this.otherCost = otherCost;
    }

    /**
     * 获取其它费用说明
     *
     * @return other_cost_remark - 其它费用说明
     */
    public String getOtherCostRemark() {
        return otherCostRemark;
    }

    /**
     * 设置其它费用说明
     *
     * @param otherCostRemark 其它费用说明
     */
    public void setOtherCostRemark(String otherCostRemark) {
        this.otherCostRemark = otherCostRemark == null ? null : otherCostRemark.trim();
    }

    /**
     * 获取具体金额 = 产品价格+运费+其他费用- 分销金额
     *
     * @return total_amount - 具体金额 = 产品价格+运费+其他费用- 分销金额
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置具体金额 = 产品价格+运费+其他费用- 分销金额
     *
     * @param totalAmount 具体金额 = 产品价格+运费+其他费用- 分销金额
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 获取支付金额 = 产品价格+运费+其他价格
     *
     * @return pay_amount - 支付金额 = 产品价格+运费+其他价格
     */
    public BigDecimal getPayAmount() {
        return payAmount;
    }

    /**
     * 设置支付金额 = 产品价格+运费+其他价格
     *
     * @param payAmount 支付金额 = 产品价格+运费+其他价格
     */
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * 获取查看的网址路径，访问时时，会添加orderid
     *
     * @return view_path - 查看的网址路径，访问时时，会添加orderid
     */
    public String getViewPath() {
        return viewPath;
    }

    /**
     * 设置查看的网址路径，访问时时，会添加orderid
     *
     * @param viewPath 查看的网址路径，访问时时，会添加orderid
     */
    public void setViewPath(String viewPath) {
        this.viewPath = viewPath == null ? null : viewPath.trim();
    }

    /**
     * 获取查看的文章内容，比如：查看、下载
     *
     * @return view_text - 查看的文章内容，比如：查看、下载
     */
    public String getViewText() {
        return viewText;
    }

    /**
     * 设置查看的文章内容，比如：查看、下载
     *
     * @param viewText 查看的文章内容，比如：查看、下载
     */
    public void setViewText(String viewText) {
        this.viewText = viewText == null ? null : viewText.trim();
    }

    /**
     * 获取可访问的有效时间，单位秒
     *
     * @return view_effective_time - 可访问的有效时间，单位秒
     */
    public Integer getViewEffectiveTime() {
        return viewEffectiveTime;
    }

    /**
     * 设置可访问的有效时间，单位秒
     *
     * @param viewEffectiveTime 可访问的有效时间，单位秒
     */
    public void setViewEffectiveTime(Integer viewEffectiveTime) {
        this.viewEffectiveTime = viewEffectiveTime;
    }

    /**
     * 获取评论的路径
     *
     * @return comment_path - 评论的路径
     */
    public String getCommentPath() {
        return commentPath;
    }

    /**
     * 设置评论的路径
     *
     * @param commentPath 评论的路径
     */
    public void setCommentPath(String commentPath) {
        this.commentPath = commentPath == null ? null : commentPath.trim();
    }

    /**
     * 获取发票
     *
     * @return invoice_id - 发票
     */
    public Integer getInvoiceId() {
        return invoiceId;
    }

    /**
     * 设置发票
     *
     * @param invoiceId 发票
     */
    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    /**
     * @return invoice_status
     */
    public Byte getInvoiceStatus() {
        return invoiceStatus;
    }

    /**
     * @param invoiceStatus
     */
    public void setInvoiceStatus(Byte invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    /**
     * 获取状态：1交易中、 2交易完成（但是可以申请退款） 、3取消交易 、4申请退款、 5拒绝退款、 6退款中、 7退款完成、 9交易结束
     *
     * @return item_status - 状态：1交易中、 2交易完成（但是可以申请退款） 、3取消交易 、4申请退款、 5拒绝退款、 6退款中、 7退款完成、 9交易结束
     */
    public Byte getItemStatus() {
        return itemStatus;
    }

    /**
     * 设置状态：1交易中、 2交易完成（但是可以申请退款） 、3取消交易 、4申请退款、 5拒绝退款、 6退款中、 7退款完成、 9交易结束
     *
     * @param itemStatus 状态：1交易中、 2交易完成（但是可以申请退款） 、3取消交易 、4申请退款、 5拒绝退款、 6退款中、 7退款完成、 9交易结束
     */
    public void setItemStatus(Byte itemStatus) {
        this.itemStatus = itemStatus;
    }

    /**
     * 获取退款订单号
     *
     * @return refund_no - 退款订单号
     */
    public String getRefundNo() {
        return refundNo;
    }

    /**
     * 设置退款订单号
     *
     * @param refundNo 退款订单号
     */
    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo == null ? null : refundNo.trim();
    }

    /**
     * 获取退款金额
     *
     * @return refund_amount - 退款金额
     */
    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    /**
     * 设置退款金额
     *
     * @param refundAmount 退款金额
     */
    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    /**
     * 获取退款描述
     *
     * @return refund_desc - 退款描述
     */
    public String getRefundDesc() {
        return refundDesc;
    }

    /**
     * 设置退款描述
     *
     * @param refundDesc 退款描述
     */
    public void setRefundDesc(String refundDesc) {
        this.refundDesc = refundDesc == null ? null : refundDesc.trim();
    }

    /**
     * 获取退款时间
     *
     * @return refund_time - 退款时间
     */
    public Date getRefundTime() {
        return refundTime;
    }

    /**
     * 设置退款时间
     *
     * @param refundTime 退款时间
     */
    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
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
     * @return options
     */
    public String getOptions() {
        return options;
    }

    /**
     * @param options
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
        sb.append(", orderId=").append(orderId);
        sb.append(", orderNs=").append(orderNs);
        sb.append(", buyerId=").append(buyerId);
        sb.append(", buyerNickname=").append(buyerNickname);
        sb.append(", buyerMsg=").append(buyerMsg);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", distUserId=").append(distUserId);
        sb.append(", distAmount=").append(distAmount);
        sb.append(", productId=").append(productId);
        sb.append(", productType=").append(productType);
        sb.append(", productTypeText=").append(productTypeText);
        sb.append(", productTitle=").append(productTitle);
        sb.append(", productSummary=").append(productSummary);
        sb.append(", productSkuId=").append(productSkuId);
        sb.append(", productSpec=").append(productSpec);
        sb.append(", productThumbnail=").append(productThumbnail);
        sb.append(", productLink=").append(productLink);
        sb.append(", productPrice=").append(productPrice);
        sb.append(", productCount=").append(productCount);
        sb.append(", withVirtual=").append(withVirtual);
        sb.append(", withRefund=").append(withRefund);
        sb.append(", deliveryCost=").append(deliveryCost);
        sb.append(", deliveryId=").append(deliveryId);
        sb.append(", otherCost=").append(otherCost);
        sb.append(", otherCostRemark=").append(otherCostRemark);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", payAmount=").append(payAmount);
        sb.append(", viewPath=").append(viewPath);
        sb.append(", viewText=").append(viewText);
        sb.append(", viewEffectiveTime=").append(viewEffectiveTime);
        sb.append(", commentPath=").append(commentPath);
        sb.append(", invoiceId=").append(invoiceId);
        sb.append(", invoiceStatus=").append(invoiceStatus);
        sb.append(", itemStatus=").append(itemStatus);
        sb.append(", refundNo=").append(refundNo);
        sb.append(", refundAmount=").append(refundAmount);
        sb.append(", refundDesc=").append(refundDesc);
        sb.append(", refundTime=").append(refundTime);
        sb.append(", modifiedTime=").append(modifiedTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", options=").append(options);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}