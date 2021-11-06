package com.jthink.shop.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "jk_member_address")
public class MemberAddress implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "member_id")
    private Integer memberId;

    /**
     * 联系人
     */
    @Column(name = "contact_name")
    private String contactName;

    /**
     * 联系电话
     */
    @Column(name = "contact_mobile")
    private String contactMobile;

    /**
     * 邮编
     */
    @Column(name = "zipcode")
    private String zipcode;

    /**
     * 省
     */
    @Column(name = "province")
    private String province;

    /**
     * 市 
     */
    @Column(name = "city")
    private String city;

    /**
     * 区县
     */
    @Column(name = "districts")
    private String districts;

    /**
     * 详细地址
     */
    @Column(name = "addr_details")
    private String addrDetails;

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
     * 获取用户ID
     *
     * @return member_id - 用户ID
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * 设置用户ID
     *
     * @param memberId 用户ID
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取联系人
     *
     * @return contact_name - 联系人
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * 设置联系人
     *
     * @param contactName 联系人
     */
    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    /**
     * 获取联系电话
     *
     * @return contact_mobile - 联系电话
     */
    public String getContactMobile() {
        return contactMobile;
    }

    /**
     * 设置联系电话
     *
     * @param contactMobile 联系电话
     */
    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile == null ? null : contactMobile.trim();
    }

    /**
     * 获取邮编
     *
     * @return zipcode - 邮编
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * 设置邮编
     *
     * @param zipcode 邮编
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? null : zipcode.trim();
    }

    /**
     * 获取省
     *
     * @return province - 省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省
     *
     * @param province 省
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * 获取市 
     *
     * @return city - 市 
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置市 
     *
     * @param city 市 
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 获取区县
     *
     * @return districts - 区县
     */
    public String getDistricts() {
        return districts;
    }

    /**
     * 设置区县
     *
     * @param districts 区县
     */
    public void setDistricts(String districts) {
        this.districts = districts == null ? null : districts.trim();
    }

    /**
     * 获取详细地址
     *
     * @return addr_details - 详细地址
     */
    public String getAddrDetails() {
        return addrDetails;
    }

    /**
     * 设置详细地址
     *
     * @param addrDetails 详细地址
     */
    public void setAddrDetails(String addrDetails) {
        this.addrDetails = addrDetails == null ? null : addrDetails.trim();
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
        sb.append(", memberId=").append(memberId);
        sb.append(", contactName=").append(contactName);
        sb.append(", contactMobile=").append(contactMobile);
        sb.append(", zipcode=").append(zipcode);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", districts=").append(districts);
        sb.append(", addrDetails=").append(addrDetails);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}