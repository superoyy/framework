package com.timanetworks.iov.domain;

import com.timanetworks.iov.core.jpa.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by dukla on 5/8/17.
 */
@Entity
@Table(name = "custom_ecu")
public class CustomEcu extends BaseEntity {
    private String id;
    private String code;
    private String showNameEn;
    private String showNameCn;
    private String remark;

    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 36, precision = 0)
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid" ,strategy = "org.hibernate.id.UUIDGenerator")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "show_name_en", nullable = true, insertable = true, updatable = true, length = 100)
    public String getShowNameEn() {
        return showNameEn;
    }

    public void setShowNameEn(String showNameEn) {
        this.showNameEn = showNameEn;
    }

    @Basic
    @Column(name = "show_name_cn", nullable = true, insertable = true, updatable = true, length = 100)
    public String getShowNameCn() {
        return showNameCn;
    }

    public void setShowNameCn(String showNameCn) {
        this.showNameCn = showNameCn;
    }

    @Basic
    @Column(name = "remark", nullable = true, insertable = true, updatable = true, length = 500)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomEcu customEcu = (CustomEcu) o;

        if (code != null ? !code.equals(customEcu.code) : customEcu.code != null) return false;
        if (id != null ? !id.equals(customEcu.id) : customEcu.id != null) return false;
        if (remark != null ? !remark.equals(customEcu.remark) : customEcu.remark != null) return false;
        if (showNameCn != null ? !showNameCn.equals(customEcu.showNameCn) : customEcu.showNameCn != null) return false;
        if (showNameEn != null ? !showNameEn.equals(customEcu.showNameEn) : customEcu.showNameEn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (showNameEn != null ? showNameEn.hashCode() : 0);
        result = 31 * result + (showNameCn != null ? showNameCn.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
