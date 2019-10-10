package com.timanetworks.iov.domain;

import com.timanetworks.iov.core.jpa.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dukla on 8/4/17.
 */
@Entity
@Table(name = "dialer_history")
public class DialerHistory  extends BaseEntity {
    private String id;
    private Date buildTime;
    private String dialerType;
    private Date finishTime;
    private String operationId;
    private String source;
    private String vin;
    private String errorCode;
    private String errorMessage;
    private String repeatRing;
    private String status;
    private String cmdId;
    private String phoneNum;

    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 32, precision = 0)
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid" ,strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "build_time", nullable = false, insertable = true, updatable = true)
    public Date getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    @Basic
    @Column(name = "dialer_type", nullable = true, insertable = true, updatable = true, length = 255)
    public String getDialerType() {
        return dialerType;
    }

    public void setDialerType(String dialerType) {
        this.dialerType = dialerType;
    }

    @Basic
    @Column(name = "finish_time", nullable = false, insertable = true, updatable = true)
    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    @Basic
    @Column(name = "operation_id", nullable = true, insertable = true, updatable = true, length = 255)
    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    @Basic
    @Column(name = "source", nullable = true, insertable = true, updatable = true, length = 255)
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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
    @Column(name = "error_code", nullable = true, insertable = true, updatable = true, length = 255)
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Basic
    @Column(name = "error_message", nullable = true, insertable = true, updatable = true, length = 255)
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Basic
    @Column(name = "repeat_ring", nullable = true, insertable = true, updatable = true, length = 255)
    public String getRepeatRing() {
        return repeatRing;
    }

    public void setRepeatRing(String repeatRing) {
        this.repeatRing = repeatRing;
    }

    @Basic
    @Column(name = "status", nullable = false, insertable = true, updatable = true, length = 255)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "cmd_id", nullable = true, insertable = true, updatable = true, length = 255)
    public String getCmdId() {
        return cmdId;
    }

    public void setCmdId(String cmdId) {
        this.cmdId = cmdId;
    }

    @Basic
    @Column(name = "phone_num", nullable = true, insertable = true, updatable = true, length = 255)
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DialerHistory that = (DialerHistory) o;

        if (buildTime != null ? !buildTime.equals(that.buildTime) : that.buildTime != null) return false;
        if (cmdId != null ? !cmdId.equals(that.cmdId) : that.cmdId != null) return false;
        if (dialerType != null ? !dialerType.equals(that.dialerType) : that.dialerType != null) return false;
        if (errorCode != null ? !errorCode.equals(that.errorCode) : that.errorCode != null) return false;
        if (errorMessage != null ? !errorMessage.equals(that.errorMessage) : that.errorMessage != null) return false;
        if (finishTime != null ? !finishTime.equals(that.finishTime) : that.finishTime != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (operationId != null ? !operationId.equals(that.operationId) : that.operationId != null) return false;
        if (phoneNum != null ? !phoneNum.equals(that.phoneNum) : that.phoneNum != null) return false;
        if (repeatRing != null ? !repeatRing.equals(that.repeatRing) : that.repeatRing != null) return false;
        if (source != null ? !source.equals(that.source) : that.source != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (vin != null ? !vin.equals(that.vin) : that.vin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (buildTime != null ? buildTime.hashCode() : 0);
        result = 31 * result + (dialerType != null ? dialerType.hashCode() : 0);
        result = 31 * result + (finishTime != null ? finishTime.hashCode() : 0);
        result = 31 * result + (operationId != null ? operationId.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (vin != null ? vin.hashCode() : 0);
        result = 31 * result + (errorCode != null ? errorCode.hashCode() : 0);
        result = 31 * result + (errorMessage != null ? errorMessage.hashCode() : 0);
        result = 31 * result + (repeatRing != null ? repeatRing.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (cmdId != null ? cmdId.hashCode() : 0);
        result = 31 * result + (phoneNum != null ? phoneNum.hashCode() : 0);
        return result;
    }
}
