package com.jthink.shop.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "jk_coupon")
public class Coupon implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 例如：无门槛50元优惠券 | 单品最高减2000元'
     */
    @Column(name = "title")
    private String title;

    @Column(name = "icon")
    private String icon;

    /**
     * 1满减券  2叠加满减券  3无门槛券  
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 满多少金额
     */
    @Column(name = "with_amount")
    private BigDecimal withAmount;

    /**
     * 是否是推广奖励券
     */
    @Column(name = "with_award")
    private Boolean withAward;

    /**
     * 是不是只有领取人可用，如果不是，领取人可以随便给其他人用
     */
    @Column(name = "with_owner")
    private Boolean withOwner;

    /**
     * 优惠券金额
     */
    @Column(name = "amount")
    private BigDecimal amount;

    /**
     * 配额：发券数量
     */
    @Column(name = "quota")
    private Integer quota;

    /**
     * 已领取的优惠券数量
     */
    @Column(name = "take_count")
    private Integer takeCount;

    /**
     * 已使用的优惠券数量
     */
    @Column(name = "used_count")
    private Integer usedCount;

    /**
     * 发放开始时间
     */
    @Column(name = "start_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startTime;

    /**
     * 发放结束时间
     */
    @Column(name = "end_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endTime;

    /**
     * 时效:1绝对时效（XXX-XXX时间段有效）  2相对时效（领取后N天有效）
     */
    @Column(name = "valid_type")
    private Integer validType;

    /**
     * 使用开始时间
     */
    @Column(name = "valid_start_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date validStartTime;

    /**
     * 使用结束时间
     */
    @Column(name = "valid_end_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date validEndTime;

    /**
     * 自领取之日起有效天数
     */
    @Column(name = "valid_days")
    private Integer validDays;

    /**
     * 创建用户
     */
    @Column(name = "create_user_id")
    private Integer createUserId;

    /**
     * 状态 0锁定 1有效
     */
    @Column(name = "cou_status")
    private String couStatus;

    /**
     * 兑换码(可选，如果有值说明支持兑换码领取此类型优惠券)
     */
    @Column(name = "code")
    private String code;

    /**
     * 商品限制类型，如果0则全商品，如果是1则是类目限制，如果是2则是商品限制。
     */
    @Column(name = "products_type")
    private Short productsType;

    /**
     * 商品限制值，products_type如果是0则空集合，如果是1则是类目集合，如果是2则是商品集合。
     */
    @Column(name = "products_value")
    private String productsValue;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取例如：无门槛50元优惠券 | 单品最高减2000元'
     *
     * @return title - 例如：无门槛50元优惠券 | 单品最高减2000元'
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置例如：无门槛50元优惠券 | 单品最高减2000元'
     *
     * @param title 例如：无门槛50元优惠券 | 单品最高减2000元'
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * @return icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * 获取1满减券  2叠加满减券  3无门槛券  
     *
     * @return type - 1满减券  2叠加满减券  3无门槛券  
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1满减券  2叠加满减券  3无门槛券  
     *
     * @param type 1满减券  2叠加满减券  3无门槛券  
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取满多少金额
     *
     * @return with_amount - 满多少金额
     */
    public BigDecimal getWithAmount() {
        return withAmount;
    }

    /**
     * 设置满多少金额
     *
     * @param withAmount 满多少金额
     */
    public void setWithAmount(BigDecimal withAmount) {
        this.withAmount = withAmount;
    }

    /**
     * 获取是否是推广奖励券
     *
     * @return with_award - 是否是推广奖励券
     */
    public Boolean getWithAward() {
        return withAward;
    }

    /**
     * 设置是否是推广奖励券
     *
     * @param withAward 是否是推广奖励券
     */
    public void setWithAward(Boolean withAward) {
        this.withAward = withAward;
    }

    /**
     * 获取是不是只有领取人可用，如果不是，领取人可以随便给其他人用
     *
     * @return with_owner - 是不是只有领取人可用，如果不是，领取人可以随便给其他人用
     */
    public Boolean getWithOwner() {
        return withOwner;
    }

    /**
     * 设置是不是只有领取人可用，如果不是，领取人可以随便给其他人用
     *
     * @param withOwner 是不是只有领取人可用，如果不是，领取人可以随便给其他人用
     */
    public void setWithOwner(Boolean withOwner) {
        this.withOwner = withOwner;
    }

    /**
     * 获取优惠券金额
     *
     * @return amount - 优惠券金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置优惠券金额
     *
     * @param amount 优惠券金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取配额：发券数量
     *
     * @return quota - 配额：发券数量
     */
    public Integer getQuota() {
        return quota;
    }

    /**
     * 设置配额：发券数量
     *
     * @param quota 配额：发券数量
     */
    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    /**
     * 获取已领取的优惠券数量
     *
     * @return take_count - 已领取的优惠券数量
     */
    public Integer getTakeCount() {
        return takeCount;
    }

    /**
     * 设置已领取的优惠券数量
     *
     * @param takeCount 已领取的优惠券数量
     */
    public void setTakeCount(Integer takeCount) {
        this.takeCount = takeCount;
    }

    /**
     * 获取已使用的优惠券数量
     *
     * @return used_count - 已使用的优惠券数量
     */
    public Integer getUsedCount() {
        return usedCount;
    }

    /**
     * 设置已使用的优惠券数量
     *
     * @param usedCount 已使用的优惠券数量
     */
    public void setUsedCount(Integer usedCount) {
        this.usedCount = usedCount;
    }

    /**
     * 获取发放开始时间
     *
     * @return start_time - 发放开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置发放开始时间
     *
     * @param startTime 发放开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取发放结束时间
     *
     * @return end_time - 发放结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置发放结束时间
     *
     * @param endTime 发放结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取时效:1绝对时效（XXX-XXX时间段有效）  2相对时效（领取后N天有效）
     *
     * @return valid_type - 时效:1绝对时效（XXX-XXX时间段有效）  2相对时效（领取后N天有效）
     */
    public Integer getValidType() {
        return validType;
    }

    /**
     * 设置时效:1绝对时效（XXX-XXX时间段有效）  2相对时效（领取后N天有效）
     *
     * @param validType 时效:1绝对时效（XXX-XXX时间段有效）  2相对时效（领取后N天有效）
     */
    public void setValidType(Integer validType) {
        this.validType = validType;
    }

    /**
     * 获取使用开始时间
     *
     * @return valid_start_time - 使用开始时间
     */
    public Date getValidStartTime() {
        return validStartTime;
    }

    /**
     * 设置使用开始时间
     *
     * @param validStartTime 使用开始时间
     */
    public void setValidStartTime(Date validStartTime) {
        this.validStartTime = validStartTime;
    }

    /**
     * 获取使用结束时间
     *
     * @return valid_end_time - 使用结束时间
     */
    public Date getValidEndTime() {
        return validEndTime;
    }

    /**
     * 设置使用结束时间
     *
     * @param validEndTime 使用结束时间
     */
    public void setValidEndTime(Date validEndTime) {
        this.validEndTime = validEndTime;
    }

    /**
     * 获取自领取之日起有效天数
     *
     * @return valid_days - 自领取之日起有效天数
     */
    public Integer getValidDays() {
        return validDays;
    }

    /**
     * 设置自领取之日起有效天数
     *
     * @param validDays 自领取之日起有效天数
     */
    public void setValidDays(Integer validDays) {
        this.validDays = validDays;
    }

    /**
     * 获取创建用户
     *
     * @return create_user_id - 创建用户
     */
    public Integer getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置创建用户
     *
     * @param createUserId 创建用户
     */
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取状态 0锁定 1有效
     *
     * @return cou_status - 状态 0锁定 1有效
     */
    public String getCouStatus() {
        return couStatus;
    }

    /**
     * 设置状态 0锁定 1有效
     *
     * @param couStatus 状态 0锁定 1有效
     */
    public void setCouStatus(String couStatus) {
        this.couStatus = couStatus == null ? null : couStatus.trim();
    }

    /**
     * 获取兑换码(可选，如果有值说明支持兑换码领取此类型优惠券)
     *
     * @return code - 兑换码(可选，如果有值说明支持兑换码领取此类型优惠券)
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置兑换码(可选，如果有值说明支持兑换码领取此类型优惠券)
     *
     * @param code 兑换码(可选，如果有值说明支持兑换码领取此类型优惠券)
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取商品限制类型，如果0则全商品，如果是1则是类目限制，如果是2则是商品限制。
     *
     * @return products_type - 商品限制类型，如果0则全商品，如果是1则是类目限制，如果是2则是商品限制。
     */
    public Short getProductsType() {
        return productsType;
    }

    /**
     * 设置商品限制类型，如果0则全商品，如果是1则是类目限制，如果是2则是商品限制。
     *
     * @param productsType 商品限制类型，如果0则全商品，如果是1则是类目限制，如果是2则是商品限制。
     */
    public void setProductsType(Short productsType) {
        this.productsType = productsType;
    }

    /**
     * 获取商品限制值，products_type如果是0则空集合，如果是1则是类目集合，如果是2则是商品集合。
     *
     * @return products_value - 商品限制值，products_type如果是0则空集合，如果是1则是类目集合，如果是2则是商品集合。
     */
    public String getProductsValue() {
        return productsValue;
    }

    /**
     * 设置商品限制值，products_type如果是0则空集合，如果是1则是类目集合，如果是2则是商品集合。
     *
     * @param productsValue 商品限制值，products_type如果是0则空集合，如果是1则是类目集合，如果是2则是商品集合。
     */
    public void setProductsValue(String productsValue) {
        this.productsValue = productsValue == null ? null : productsValue.trim();
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
        sb.append(", title=").append(title);
        sb.append(", icon=").append(icon);
        sb.append(", type=").append(type);
        sb.append(", withAmount=").append(withAmount);
        sb.append(", withAward=").append(withAward);
        sb.append(", withOwner=").append(withOwner);
        sb.append(", amount=").append(amount);
        sb.append(", quota=").append(quota);
        sb.append(", takeCount=").append(takeCount);
        sb.append(", usedCount=").append(usedCount);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", validType=").append(validType);
        sb.append(", validStartTime=").append(validStartTime);
        sb.append(", validEndTime=").append(validEndTime);
        sb.append(", validDays=").append(validDays);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", couStatus=").append(couStatus);
        sb.append(", code=").append(code);
        sb.append(", productsType=").append(productsType);
        sb.append(", productsValue=").append(productsValue);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", options=").append(options);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}