package com.jthink.cms.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "jk_term_relationships")
public class TermRelationship implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 对应文章ID/链接ID
     */
    @Column(name = "object_id")
    private Integer objectId;

    /**
     * 对应分类方法ID
     */
    @Column(name = "term_id")
    private Integer termId;

    /**
     * 排序
     */
    @Column(name = "term_order")
    private Integer termOrder;

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
     * 获取对应文章ID/链接ID
     *
     * @return object_id - 对应文章ID/链接ID
     */
    public Integer getObjectId() {
        return objectId;
    }

    /**
     * 设置对应文章ID/链接ID
     *
     * @param objectId 对应文章ID/链接ID
     */
    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    /**
     * 获取对应分类方法ID
     *
     * @return term_taxonomy_id - 对应分类方法ID
     */
    public Integer getTermId() {
        return termId;
    }

    /**
     * 设置对应分类方法ID
     *
     * @param termTaxonomyId 对应分类方法ID
     */
    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    /**
     * 获取排序
     *
     * @return term_order - 排序
     */
    public Integer getTermOrder() {
        return termOrder;
    }

    /**
     * 设置排序
     *
     * @param termOrder 排序
     */
    public void setTermOrder(Integer termOrder) {
        this.termOrder = termOrder;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", objectId=").append(objectId);
        sb.append(", termId=").append(termId);
        sb.append(", termOrder=").append(termOrder);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}