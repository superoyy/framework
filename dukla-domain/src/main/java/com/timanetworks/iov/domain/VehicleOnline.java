package com.timanetworks.iov.domain;

import com.timanetworks.iov.core.jpa.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dukla on 9/19/17.
 */
@Entity
@Table(name = "vehicle_online")
public class VehicleOnline  extends BaseEntity {
    private String id;
    private String vin;
    private String vgIp;
    private Integer vgPort;
    private Integer status;
    private Date onlineTime;
    private Date offlineTime;
    private Integer offlineReason;
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
    @Column(name = "vin", nullable = false, insertable = true, updatable = true, length = 100)
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Basic
    @Column(name = "vg_ip", nullable = true, insertable = true, updatable = true, length = 50)
    public String getVgIp() {
        return vgIp;
    }

    public void setVgIp(String vgIp) {
        this.vgIp = vgIp;
    }

    @Basic
    @Column(name = "vg_port", nullable = true, insertable = true, updatable = true)
    public Integer getVgPort() {
        return vgPort;
    }

    public void setVgPort(Integer vgPort) {
        this.vgPort = vgPort;
    }

    @Basic
    @Column(name = "status", nullable = true, insertable = true, updatable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "online_time", nullable = true, insertable = true, updatable = true)
    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    @Basic
    @Column(name = "offline_time", nullable = true, insertable = true, updatable = true)
    public Date getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Date offlineTime) {
        this.offlineTime = offlineTime;
    }


    @Basic
    @Column(name = "offline_reason", nullable = true, insertable = true, updatable = true)
    public Integer getOfflineReason() {
        return offlineReason;
    }

    public void setOfflineReason(Integer offlineReason) {
        this.offlineReason = offlineReason;
    }


    @Basic
    @Column(name = "remark", nullable = true, insertable = true, updatable = true, length = 1000)
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

        VehicleOnline that = (VehicleOnline) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (offlineTime != null ? !offlineTime.equals(that.offlineTime) : that.offlineTime != null) return false;
        if (onlineTime != null ? !onlineTime.equals(that.onlineTime) : that.onlineTime != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (offlineReason != null ? !offlineReason.equals(that.offlineReason) : that.offlineReason != null) return false;
        if (vgIp != null ? !vgIp.equals(that.vgIp) : that.vgIp != null) return false;
        if (vgPort != null ? !vgPort.equals(that.vgPort) : that.vgPort != null) return false;
        if (vin != null ? !vin.equals(that.vin) : that.vin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (vin != null ? vin.hashCode() : 0);
        result = 31 * result + (vgIp != null ? vgIp.hashCode() : 0);
        result = 31 * result + (vgPort != null ? vgPort.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (onlineTime != null ? onlineTime.hashCode() : 0);
        result = 31 * result + (offlineTime != null ? offlineTime.hashCode() : 0);
        result = 31 * result + (offlineReason != null ? offlineReason.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
