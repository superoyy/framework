package com.timanetworks.iov.domain;

import com.timanetworks.iov.core.jpa.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dukla on 9/19/17.
 */
@Entity
@Table(name = "vehicle_gateway")
public class VehicleGateway  extends BaseEntity {
    private String id;
    private String ip;
    private int port;
    private String vehicleModeCode;
    private Integer status;
    private Date startTime;
    private Date heartbeatTime;
    private Date stopTime;
    private Integer stopReason;
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
    @Column(name = "ip", nullable = false, insertable = true, updatable = true, length = 50)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "port", nullable = false, insertable = true, updatable = true)
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Basic
    @Column(name = "vehicle_mode_code", nullable = true, insertable = true, updatable = true, length = 100)
    public String getVehicleModeCode() {
        return vehicleModeCode;
    }

    public void setVehicleModeCode(String vehicleModeCode) {
        this.vehicleModeCode = vehicleModeCode;
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
    @Column(name = "start_time", nullable = true, insertable = true, updatable = true)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "heartbeat_time", nullable = true, insertable = true, updatable = true)
    public Date getHeartbeatTime() {
        return heartbeatTime;
    }

    public void setHeartbeatTime(Date heartbeatTime) {
        this.heartbeatTime = heartbeatTime;
    }

    @Basic
    @Column(name = "stop_time", nullable = true, insertable = true, updatable = true)
    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    @Basic
    @Column(name = "stop_reason", nullable = true, insertable = true, updatable = true)
    public Integer getStopReason() {
        return stopReason;
    }

    public void setStopReason(Integer stopReason) {
        this.stopReason = stopReason;
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

        VehicleGateway that = (VehicleGateway) o;

        if (port != that.port) return false;
        if (heartbeatTime != null ? !heartbeatTime.equals(that.heartbeatTime) : that.heartbeatTime != null)
            return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (stopReason != null ? !stopReason.equals(that.stopReason) : that.stopReason != null) return false;
        if (stopTime != null ? !stopTime.equals(that.stopTime) : that.stopTime != null) return false;
        if (vehicleModeCode != null ? !vehicleModeCode.equals(that.vehicleModeCode) : that.vehicleModeCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + port;
        result = 31 * result + (vehicleModeCode != null ? vehicleModeCode.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (heartbeatTime != null ? heartbeatTime.hashCode() : 0);
        result = 31 * result + (stopTime != null ? stopTime.hashCode() : 0);
        result = 31 * result + (stopReason != null ? stopReason.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
