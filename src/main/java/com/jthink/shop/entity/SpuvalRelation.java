package com.jthink.shop.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "jk_spuval_relation")
public class SpuvalRelation implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * jk_spu 主键
     */
    @Column(name = "spu_id")
    private Integer spuId;

    /**
     * jk_spu_value 主键
     */
    @Column(name = "spuval_id")
    private Integer spuvalId;

    /**
     * jk_posts 商品表主键
     */
    @Column(name = "product_id")
    private Integer productId;

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
     * 获取jk_spu 主键
     *
     * @return spu_id - jk_spu 主键
     */
    public Integer getSpuId() {
        return spuId;
    }

    /**
     * 设置jk_spu 主键
     *
     * @param spuId jk_spu 主键
     */
    public void setSpuId(Integer spuId) {
        this.spuId = spuId;
    }

    /**
     * 获取jk_spu_value 主键
     *
     * @return spuval_id - jk_spu_value 主键
     */
    public Integer getSpuvalId() {
        return spuvalId;
    }

    /**
     * 设置jk_spu_value 主键
     *
     * @param spuvalId jk_spu_value 主键
     */
    public void setSpuvalId(Integer spuvalId) {
        this.spuvalId = spuvalId;
    }

    /**
     * 获取jk_posts 商品表主键
     *
     * @return product_id - jk_posts 商品表主键
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * 设置jk_posts 商品表主键
     *
     * @param productId jk_posts 商品表主键
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", spuId=").append(spuId);
        sb.append(", spuvalId=").append(spuvalId);
        sb.append(", productId=").append(productId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}