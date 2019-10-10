package com.timanetworks.iov.domain;

import com.timanetworks.iov.core.jpa.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 车系的原始车况信号和标准车况信号对照表（广丰）
 *
 * @author taoningbo
 * @since 4.2.3
 */
@Entity
@Table(name = "veh_status_signal")
public class VehStatusSignal extends BaseEntity {

    private static final long serialVersionUID = 6410849122648827943L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Basic
    @Column(name = "veh_series", nullable = false, length = 100)
    private String vehSeries;

    @Basic
    @Column(name = "status_code", nullable = false, length = 100)
    private String statusCode;

    @Basic
    @Column(name = "origin_code", nullable = false, length = 100)
    private String originCode;

    @Basic
    @Column(name = "origin_name", nullable = false, length = 500)
    private String originName;

    @Basic
    @Column(name = "veh_convert", length = 1000)
    private String vehConvert;

    @Basic
    @Column(name = "signal_type", nullable = false, length = 2)
    private Integer signalType;

    @Basic
    @Column(name = "created_time")
    private Date createdTime;

    @Basic
    @Column(name = "updated_time")
    private Date updatedTime;

    @Basic
    @Column(name = "remark", length = 1000)
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehSeries() {
        return vehSeries;
    }

    public void setVehSeries(String vehSeries) {
        this.vehSeries = vehSeries;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getOriginCode() {
        return originCode;
    }

    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getVehConvert() {
        return vehConvert;
    }

    public void setVehConvert(String vehConvert) {
        this.vehConvert = vehConvert;
    }

    public Integer getSignalType() {
        return signalType;
    }

    public void setSignalType(Integer signalType) {
        this.signalType = signalType;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
