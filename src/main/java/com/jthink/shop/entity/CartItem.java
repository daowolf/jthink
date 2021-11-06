package com.jthink.shop.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8810873108937641870L;
	private Integer productId;
	private String productName;
	// 规格ID组合
	private String spuvalIds;
	// 规格json字符
	private String spuvalStr;

	private BigDecimal price;
	private String imgUrl;
	private Integer quantity;
	private BigDecimal allPrice;

	public BigDecimal getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(BigDecimal allPrice) {
		this.allPrice = allPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

}
