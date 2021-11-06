package com.jthink.shop.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "jk_spu_value")
public class ProductSpuValue implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 规格ID(t_product_specification表主键)
     */
    @Column(name = "spu_id")
    private Integer spuId;

    @Column(name = "spu_value")
    private String spuValue;

    @Column(name = "seq_num")
    private Integer seqNum;

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

   

    public Integer getSpuId() {
		return spuId;
	}

	public void setSpuId(Integer spuId) {
		this.spuId = spuId;
	}

	public String getSpuValue() {
		return spuValue;
	}

	public void setSpuValue(String spuValue) {
		this.spuValue = spuValue;
	}

	/**
     * @return seq_num
     */
    public Integer getSeqNum() {
        return seqNum;
    }

    /**
     * @param seqNum
     */
    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
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
        sb.append(", spuId=").append(spuId);
        sb.append(", spuValue=").append(spuValue);
        sb.append(", seqNum=").append(seqNum);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}