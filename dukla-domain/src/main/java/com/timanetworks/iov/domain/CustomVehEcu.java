package com.timanetworks.iov.domain;

import com.timanetworks.iov.core.jpa.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by dukla on 5/8/17.
 */
@Entity
@Table(name = "custom_veh_ecu")
public class CustomVehEcu extends BaseEntity {
    private String id;
    private String vehModeCode;
    private String ecuCode;
    private Integer scoreFlag;
    private Integer scoreTotal;
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
    @Column(name = "veh_mode_code", nullable = true, insertable = true, updatable = true, length = 50)
    public String getVehModeCode() {
        return vehModeCode;
    }

    public void setVehModeCode(String vehModeCode) {
        this.vehModeCode = vehModeCode;
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
    @Column(name = "score_flag", nullable = true, insertable = true, updatable = true)
    public Integer getScoreFlag() {
        return scoreFlag;
    }

    public void setScoreFlag(Integer scoreFlag) {
        this.scoreFlag = scoreFlag;
    }

    @Basic
    @Column(name = "score_total", nullable = true, insertable = true, updatable = true)
    public Integer getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(Integer scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    @Basic
    @Column(name = "remark", nullable = true, insertable = true, updatable = true, length = 200)
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

        CustomVehEcu that = (CustomVehEcu) o;

        if (ecuCode != null ? !ecuCode.equals(that.ecuCode) : that.ecuCode != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (scoreFlag != null ? !scoreFlag.equals(that.scoreFlag) : that.scoreFlag != null) return false;
        if (scoreTotal != null ? !scoreTotal.equals(that.scoreTotal) : that.scoreTotal != null) return false;
        if (vehModeCode != null ? !vehModeCode.equals(that.vehModeCode) : that.vehModeCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (vehModeCode != null ? vehModeCode.hashCode() : 0);
        result = 31 * result + (ecuCode != null ? ecuCode.hashCode() : 0);
        result = 31 * result + (scoreFlag != null ? scoreFlag.hashCode() : 0);
        result = 31 * result + (scoreTotal != null ? scoreTotal.hashCode() : 0);
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
