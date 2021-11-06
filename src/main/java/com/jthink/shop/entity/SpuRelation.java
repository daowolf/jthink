package com.jthink.shop.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "jk_spu_relation")
public class SpuRelation implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "spu_id")
    private Integer spuId;

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
     * @return spu_id
     */
    public Integer getSpuId() {
        return spuId;
    }

    /**
     * @param spuId
     */
    public void setSpuId(Integer spuId) {
        this.spuId = spuId;
    }

    /**
     * @return product_id
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * @param productId
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
        sb.append(", productId=").append(productId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}