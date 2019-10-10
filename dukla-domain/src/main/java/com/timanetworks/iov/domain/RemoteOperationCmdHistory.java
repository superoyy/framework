package com.timanetworks.iov.domain;

import com.timanetworks.iov.core.jpa.entity.BaseEntity;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by dukla on 8/4/17.
 */
@Entity
@javax.persistence.Table(name = "remote_operation_cmd_history")
public class RemoteOperationCmdHistory extends BaseEntity {

    private long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String createdBy;

    @Basic
    @javax.persistence.Column(name = "created_by", nullable = true, insertable = true, updatable = true, length = 255)
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    private String createdClientId;

    @Basic
    @javax.persistence.Column(name = "created_client_id", nullable = true, insertable = true, updatable = true, length = 255)
    public String getCreatedClientId() {
        return createdClientId;
    }

    public void setCreatedClientId(String createdClientId) {
        this.createdClientId = createdClientId;
    }

    private String createdClientType;

    @Basic
    @javax.persistence.Column(name = "created_client_type", nullable = true, insertable = true, updatable = true, length = 255)
    public String getCreatedClientType() {
        return createdClientType;
    }

    public void setCreatedClientType(String createdClientType) {
        this.createdClientType = createdClientType;
    }

    private Date createdTime;

    @Basic
    @javax.persistence.Column(name = "created_time", nullable = true, insertable = true, updatable = true)
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    private String updatedBy;

    @Basic
    @javax.persistence.Column(name = "updated_by", nullable = true, insertable = true, updatable = true, length = 255)
    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    private String updatedClientId;

    @Basic
    @javax.persistence.Column(name = "updated_client_id", nullable = true, insertable = true, updatable = true, length = 255)
    public String getUpdatedClientId() {
        return updatedClientId;
    }

    public void setUpdatedClientId(String updatedClientId) {
        this.updatedClientId = updatedClientId;
    }

    private Integer updatedClientType;

    @Basic
    @javax.persistence.Column(name = "updated_client_type", nullable = true, insertable = true, updatable = true)
    public Integer getUpdatedClientType() {
        return updatedClientType;
    }

    public void setUpdatedClientType(Integer updatedClientType) {
        this.updatedClientType = updatedClientType;
    }

    private Date updatedTime;

    @Basic
    @javax.persistence.Column(name = "updated_time", nullable = true, insertable = true, updatable = true)
    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    private Date buildTime;

    @Basic
    @javax.persistence.Column(name = "build_time", nullable = false, insertable = true, updatable = true)
    public Date getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    private int cmd;

    @Basic
    @javax.persistence.Column(name = "cmd", nullable = false, insertable = true, updatable = true)
    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    private String cmdId;

    @Basic
    @javax.persistence.Column(name = "cmd_id", nullable = false, insertable = true, updatable = true, length = 50)
    public String getCmdId() {
        return cmdId;
    }

    public void setCmdId(String cmdId) {
        this.cmdId = cmdId;
    }

    private String errCode;

    @Basic
    @javax.persistence.Column(name = "err_code", nullable = true, insertable = true, updatable = true, length = 20)
    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    private String errMessage;

    @Basic
    @javax.persistence.Column(name = "err_message", nullable = true, insertable = true, updatable = true, length = 255)
    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    private boolean isMust;

    @Basic
    @javax.persistence.Column(name = "is_must", nullable = false, insertable = true, updatable = true)
    public boolean isMust() {
        return isMust;
    }

    public void setMust(boolean isMust) {
        this.isMust = isMust;
    }

    private String operationId;

    @Basic
    @javax.persistence.Column(name = "operation_id", nullable = false, insertable = true, updatable = true, length = 50)
    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    private String paramObj;

    @Basic
    @javax.persistence.Column(name = "param_obj", nullable = true, insertable = true, updatable = true, length = 2048)
    public String getParamObj() {
        return paramObj;
    }

    public void setParamObj(String paramObj) {
        this.paramObj = paramObj;
    }

    private Byte seq;

    @Basic
    @javax.persistence.Column(name = "seq", nullable = true, insertable = true, updatable = true)
    public Byte getSeq() {
        return seq;
    }

    public void setSeq(Byte seq) {
        this.seq = seq;
    }

    private String status;

    @Basic
    @javax.persistence.Column(name = "status", nullable = true, insertable = true, updatable = true, length = 255)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private Date statusUpdateTime;

    @Basic
    @javax.persistence.Column(name = "status_update_time", nullable = false, insertable = true, updatable = true)
    public Date getStatusUpdateTime() {
        return statusUpdateTime;
    }

    public void setStatusUpdateTime(Date statusUpdateTime) {
        this.statusUpdateTime = statusUpdateTime;
    }

    private String vin;

    @Basic
    @javax.persistence.Column(name = "vin", nullable = false, insertable = true, updatable = true, length = 17)
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    private OperationTypeDefinition operationTypeDefinition;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "operation_type_id", referencedColumnName = "id")
    public OperationTypeDefinition getOperationTypeDefinition() {
        return operationTypeDefinition;
    }

    public void setOperationTypeDefinition(OperationTypeDefinition operationTypeDefinition) {
        this.operationTypeDefinition = operationTypeDefinition;
    }

    private Date aid;

    @Basic
    @javax.persistence.Column(name = "aid", nullable = true, insertable = true, updatable = true, length = 255)
    public Date getAid() {
        return aid;
    }

    public void setAid(Date aid) {
        this.aid = aid;
    }

    private byte permission;

    @Basic
    @javax.persistence.Column(name = "permission", nullable = false, insertable = true, updatable = true)
    public byte getPermission() {
        return permission;
    }

    public void setPermission(byte permission) {
        this.permission = permission;
    }

    private String role;

    @Basic
    @javax.persistence.Column(name = "role", nullable = false, insertable = true, updatable = true, length = 255)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String uid;

    @Basic
    @javax.persistence.Column(name = "uid", nullable = true, insertable = true, updatable = true, length = 255)
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    private String userOrg;

    @Basic
    @javax.persistence.Column(name = "user_org", nullable = true, insertable = true, updatable = true, length = 255)
    public String getUserOrg() {
        return userOrg;
    }

    public void setUserOrg(String userOrg) {
        this.userOrg = userOrg;
    }

    private String createdSource;

    @Basic
    @javax.persistence.Column(name = "created_source", nullable = true, insertable = true, updatable = true, length = 50)
    public String getCreatedSource() {
        return createdSource;
    }

    public void setCreatedSource(String createdSource) {
        this.createdSource = createdSource;
    }

    private String updatedSource;

    @Basic
    @javax.persistence.Column(name = "updated_source", nullable = true, insertable = true, updatable = true, length = 50)
    public String getUpdatedSource() {
        return updatedSource;
    }

    public void setUpdatedSource(String updatedSource) {
        this.updatedSource = updatedSource;
    }

    private String source;

    @Basic
    @javax.persistence.Column(name = "source", nullable = true, insertable = true, updatable = true, length = 100)
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    private Integer removed;

    @Basic
    @javax.persistence.Column(name = "removed", nullable = true, insertable = true, updatable = true)
    public Integer getRemoved() {
        return removed;
    }

    public void setRemoved(Integer removed) {
        this.removed = removed;
    }

    private String operationContent;

    @Basic
    @javax.persistence.Column(name = "operation_content", nullable = true, insertable = true, updatable = true, length = 100)
    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
    }

    private Integer ring;

    @Basic
    @javax.persistence.Column(name = "ring", nullable = true, insertable = true, updatable = true)
    public Integer getRing() {
        return ring;
    }

    public void setRing(Integer ring) {
        this.ring = ring;
    }

    private String responseContent;

    @Basic
    @javax.persistence.Column(name = "response_content", nullable = true, insertable = true, updatable = true, length = 1000)
    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RemoteOperationCmdHistory that = (RemoteOperationCmdHistory) o;

        if (cmd != that.cmd) return false;
        if (id != that.id) return false;
        if (isMust != that.isMust) return false;
        if (permission != that.permission) return false;
        if (aid != null ? !aid.equals(that.aid) : that.aid != null) return false;
        if (buildTime != null ? !buildTime.equals(that.buildTime) : that.buildTime != null) return false;
        if (cmdId != null ? !cmdId.equals(that.cmdId) : that.cmdId != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (createdClientId != null ? !createdClientId.equals(that.createdClientId) : that.createdClientId != null)
            return false;
        if (createdClientType != null ? !createdClientType.equals(that.createdClientType) : that.createdClientType != null)
            return false;
        if (createdSource != null ? !createdSource.equals(that.createdSource) : that.createdSource != null)
            return false;
        if (createdTime != null ? !createdTime.equals(that.createdTime) : that.createdTime != null) return false;
        if (errCode != null ? !errCode.equals(that.errCode) : that.errCode != null) return false;
        if (errMessage != null ? !errMessage.equals(that.errMessage) : that.errMessage != null) return false;
        if (operationContent != null ? !operationContent.equals(that.operationContent) : that.operationContent != null)
            return false;
        if (operationId != null ? !operationId.equals(that.operationId) : that.operationId != null) return false;
        if (paramObj != null ? !paramObj.equals(that.paramObj) : that.paramObj != null) return false;
        if (removed != null ? !removed.equals(that.removed) : that.removed != null) return false;
        if (responseContent != null ? !responseContent.equals(that.responseContent) : that.responseContent != null)
            return false;
        if (ring != null ? !ring.equals(that.ring) : that.ring != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (seq != null ? !seq.equals(that.seq) : that.seq != null) return false;
        if (source != null ? !source.equals(that.source) : that.source != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (statusUpdateTime != null ? !statusUpdateTime.equals(that.statusUpdateTime) : that.statusUpdateTime != null)
            return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (updatedBy != null ? !updatedBy.equals(that.updatedBy) : that.updatedBy != null) return false;
        if (updatedClientId != null ? !updatedClientId.equals(that.updatedClientId) : that.updatedClientId != null)
            return false;
        if (updatedClientType != null ? !updatedClientType.equals(that.updatedClientType) : that.updatedClientType != null)
            return false;
        if (updatedSource != null ? !updatedSource.equals(that.updatedSource) : that.updatedSource != null)
            return false;
        if (updatedTime != null ? !updatedTime.equals(that.updatedTime) : that.updatedTime != null) return false;
        if (userOrg != null ? !userOrg.equals(that.userOrg) : that.userOrg != null) return false;
        if (vin != null ? !vin.equals(that.vin) : that.vin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdClientId != null ? createdClientId.hashCode() : 0);
        result = 31 * result + (createdClientType != null ? createdClientType.hashCode() : 0);
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        result = 31 * result + (updatedBy != null ? updatedBy.hashCode() : 0);
        result = 31 * result + (updatedClientId != null ? updatedClientId.hashCode() : 0);
        result = 31 * result + (updatedClientType != null ? updatedClientType.hashCode() : 0);
        result = 31 * result + (updatedTime != null ? updatedTime.hashCode() : 0);
        result = 31 * result + (buildTime != null ? buildTime.hashCode() : 0);
        result = 31 * result + cmd;
        result = 31 * result + (cmdId != null ? cmdId.hashCode() : 0);
        result = 31 * result + (errCode != null ? errCode.hashCode() : 0);
        result = 31 * result + (errMessage != null ? errMessage.hashCode() : 0);
        result = 31 * result + (isMust ? 1 : 0);
        result = 31 * result + (operationId != null ? operationId.hashCode() : 0);
        result = 31 * result + (paramObj != null ? paramObj.hashCode() : 0);
        result = 31 * result + (seq != null ? seq.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (statusUpdateTime != null ? statusUpdateTime.hashCode() : 0);
        result = 31 * result + (vin != null ? vin.hashCode() : 0);
        result = 31 * result + (aid != null ? aid.hashCode() : 0);
        result = 31 * result + (int) permission;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (userOrg != null ? userOrg.hashCode() : 0);
        result = 31 * result + (createdSource != null ? createdSource.hashCode() : 0);
        result = 31 * result + (updatedSource != null ? updatedSource.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (removed != null ? removed.hashCode() : 0);
        result = 31 * result + (operationContent != null ? operationContent.hashCode() : 0);
        result = 31 * result + (ring != null ? ring.hashCode() : 0);
        result = 31 * result + (responseContent != null ? responseContent.hashCode() : 0);
        return result;
    }
}
