package com.timanetworks.iov.domain;

import com.timanetworks.iov.core.jpa.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by dukla on 5/8/17.
 */
@Entity
@Table(name = "custom_dtc")
public class CustomDtc extends BaseEntity {
    private String id;
    private String vehModeCode;
    private String code;
    private String ecuCode;
    private Integer level;
    private String failureType;
    private String describCh;
    private String describEn;
    private Integer dispFlag;
    private Integer overruledFlag;
    private String remark;
    private CustomEcu customEcu;

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
    @Column(name = "veh_mode_code", nullable = true, insertable = true, updatable = true, length = 100)
    public String getVehModeCode() {
        return vehModeCode;
    }

    public void setVehModeCode(String vehModeCode) {
        this.vehModeCode = vehModeCode;
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
    @Column(name = "ecu_code", nullable = true, insertable = true, updatable = true, length = 50)
    public String getEcuCode() {
        return ecuCode;
    }

    public void setEcuCode(String ecuCode) {
        this.ecuCode = ecuCode;
    }

    @Basic
    @Column(name = "level", nullable = true, insertable = true, updatable = true)
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "failure_type", nullable = true, insertable = true, updatable = true, length = 100)
    public String getFailureType() {
        return failureType;
    }

    public void setFailureType(String failureType) {
        this.failureType = failureType;
    }

    @Basic
    @Column(name = "describ_ch", nullable = true, insertable = true, updatable = true, length = 200)
    public String getDescribCh() {
        return describCh;
    }

    public void setDescribCh(String describCh) {
        this.describCh = describCh;
    }

    @Basic
    @Column(name = "describ_en", nullable = true, insertable = true, updatable = true, length = 200)
    public String getDescribEn() {
        return describEn;
    }

    public void setDescribEn(String describEn) {
        this.describEn = describEn;
    }

    @Basic
    @Column(name = "disp_flag", nullable = true, insertable = true, updatable = true)
    public Integer getDispFlag() {
        return dispFlag;
    }

    public void setDispFlag(Integer dispFlag) {
        this.dispFlag = dispFlag;
    }

    @Basic
    @Column(name = "overruled_flag", nullable = true, insertable = true, updatable = true)
    public Integer getOverruledFlag() {
        return overruledFlag;
    }

    public void setOverruledFlag(Integer overruledFlag) {
        this.overruledFlag = overruledFlag;
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

        CustomDtc customDtc = (CustomDtc) o;

        if (code != null ? !code.equals(customDtc.code) : customDtc.code != null) return false;
        if (describCh != null ? !describCh.equals(customDtc.describCh) : customDtc.describCh != null) return false;
        if (describEn != null ? !describEn.equals(customDtc.describEn) : customDtc.describEn != null) return false;
        if (dispFlag != null ? !dispFlag.equals(customDtc.dispFlag) : customDtc.dispFlag != null) return false;
        if (ecuCode != null ? !ecuCode.equals(customDtc.ecuCode) : customDtc.ecuCode != null) return false;
        if (failureType != null ? !failureType.equals(customDtc.failureType) : customDtc.failureType != null)
            return false;
        if (id != null ? !id.equals(customDtc.id) : customDtc.id != null) return false;
        if (level != null ? !level.equals(customDtc.level) : customDtc.level != null) return false;
        if (overruledFlag != null ? !overruledFlag.equals(customDtc.overruledFlag) : customDtc.overruledFlag != null)
            return false;
        if (remark != null ? !remark.equals(customDtc.remark) : customDtc.remark != null) return false;
        if (vehModeCode != null ? !vehModeCode.equals(customDtc.vehModeCode) : customDtc.vehModeCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (vehModeCode != null ? vehModeCode.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (ecuCode != null ? ecuCode.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (failureType != null ? failureType.hashCode() : 0);
        result = 31 * result + (describCh != null ? describCh.hashCode() : 0);
        result = 31 * result + (describEn != null ? describEn.hashCode() : 0);
        result = 31 * result + (dispFlag != null ? dispFlag.hashCode() : 0);
        result = 31 * result + (overruledFlag != null ? overruledFlag.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "ecu_id", referencedColumnName = "id")
    public CustomEcu getCustomEcu() {
        return customEcu;
    }

    public void setCustomEcu(CustomEcu customEcu) {
        this.customEcu = customEcu;
    }
}
