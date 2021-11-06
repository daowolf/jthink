package com.jthink.shop.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "jk_product_sku")
public class ProductSku implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商品表的商品ID
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * 规格ID组合
     */
    @Column(name = "spuval_ids")
    private String spuvalIds;

    /**
     * 商品规格值列表，采用JSON数组格式
     */
    @Column(name = "spuval_str")
    private String spuvalStr;

    @Column(name = "origin_price")
    private BigDecimal originPrice;

    /**
     * 商品货品价格
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * 商品货品数量
     */
    @Column(name = "stock")
    private Integer stock;

    /**
     * 商品货品图片
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
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
     * 获取商品表的商品ID
     *
     * @return product_id - 商品表的商品ID
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * 设置商品表的商品ID
     *
     * @param productId 商品表的商品ID
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

  

    public String getSpuvalIds() {
		return spuvalIds;
	}

	public void setSpuvalIds(String spuvalIds) {
		this.spuvalIds = spuvalIds;
	}

	public String getSpuvalStr() {
		return spuvalStr;
	}

	public void setSpuvalStr(String spuvalStr) {
		this.spuvalStr = spuvalStr;
	}

	/**
     * @return origin_price
     */
    public BigDecimal getOriginPrice() {
        return originPrice;
    }

    /**
     * @param originPrice
     */
    public void setOriginPrice(BigDecimal originPrice) {
        this.originPrice = originPrice;
    }

    /**
     * 获取商品货品价格
     *
     * @return price - 商品货品价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置商品货品价格
     *
     * @param price 商品货品价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取商品货品数量
     *
     * @return stock - 商品货品数量
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * 设置商品货品数量
     *
     * @param stock 商品货品数量
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * 获取商品货品图片
     *
     * @return img_url - 商品货品图片
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置商品货品图片
     *
     * @param imgUrl 商品货品图片
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
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
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
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
        sb.append(", productId=").append(productId);
        sb.append(", spuvalIds=").append(spuvalIds);
        sb.append(", spuvalStr=").append(spuvalStr);
        sb.append(", originPrice=").append(originPrice);
        sb.append(", price=").append(price);
        sb.append(", stock=").append(stock);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}