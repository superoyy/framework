package com.timanetworks.iov.domain;

import com.timanetworks.iov.core.jpa.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by dukla on 5/8/17.
 */
@Entity
@Table(name = "veh_status_struc")
public class VehStatusStruc extends BaseEntity{
    private String id;
    private String vehModeCode;
    private String protocolVersion;
    private GlobalStatus globalStatus;
    private String statusCode;
    private Integer nodeType;
    private GlobalStatus upGlobalStatus;
    private String upStatusCode;
    private Integer didCode;
    private Integer signalIndex;
    private String valConver;
    private Integer nodeOrder;
    private String nodeVal;

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
    @Column(name = "protocol_version", nullable = true, insertable = true, updatable = true, length = 20)
    public String getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    @Basic
    @Column(name = "status_code", nullable = true, insertable = true, updatable = true, length = 50)
    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @Basic
    @Column(name = "node_type", nullable = true, insertable = true, updatable = true)
    public Integer getNodeType() {
        return nodeType;
    }

    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }

    @Basic
    @Column(name = "up_status_code", nullable = true, insertable = true, updatable = true, length = 50)
    public String getUpStatusCode() {
        return upStatusCode;
    }

    public void setUpStatusCode(String upStatusCode) {
        this.upStatusCode = upStatusCode;
    }

    @Basic
    @Column(name = "did_code", nullable = true, insertable = true, updatable = true)
    public Integer getDidCode() {
        return didCode;
    }

    public void setDidCode(Integer didCode) {
        this.didCode = didCode;
    }

    @Basic
    @Column(name = "signal_index", nullable = true, insertable = true, updatable = true)
    public Integer getSignalIndex() {
        return signalIndex;
    }

    public void setSignalIndex(Integer signalIndex) {
        this.signalIndex = signalIndex;
    }

    @Basic
    @Column(name = "val_conver", nullable = true, insertable = true, updatable = true, length = 800)
    public String getValConver() {
        return valConver;
    }

    public void setValConver(String valConver) {
        this.valConver = valConver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VehStatusStruc that = (VehStatusStruc) o;

        if (didCode != null ? !didCode.equals(that.didCode) : that.didCode != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nodeType != null ? !nodeType.equals(that.nodeType) : that.nodeType != null) return false;
        if (protocolVersion != null ? !protocolVersion.equals(that.protocolVersion) : that.protocolVersion != null)
            return false;
        if (signalIndex != null ? !signalIndex.equals(that.signalIndex) : that.signalIndex != null) return false;
        if (statusCode != null ? !statusCode.equals(that.statusCode) : that.statusCode != null) return false;
        if (upStatusCode != null ? !upStatusCode.equals(that.upStatusCode) : that.upStatusCode != null) return false;
        if (valConver != null ? !valConver.equals(that.valConver) : that.valConver != null) return false;
        if (vehModeCode != null ? !vehModeCode.equals(that.vehModeCode) : that.vehModeCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (vehModeCode != null ? vehModeCode.hashCode() : 0);
        result = 31 * result + (protocolVersion != null ? protocolVersion.hashCode() : 0);
        result = 31 * result + (statusCode != null ? statusCode.hashCode() : 0);
        result = 31 * result + (nodeType != null ? nodeType.hashCode() : 0);
        result = 31 * result + (upStatusCode != null ? upStatusCode.hashCode() : 0);
        result = 31 * result + (didCode != null ? didCode.hashCode() : 0);
        result = 31 * result + (signalIndex != null ? signalIndex.hashCode() : 0);
        result = 31 * result + (valConver != null ? valConver.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "node_order", nullable = true, insertable = true, updatable = true)
    public Integer getNodeOrder() {
        return nodeOrder;
    }

    public void setNodeOrder(Integer nodeOrder) {
        this.nodeOrder = nodeOrder;
    }

    @Basic
    @Column(name = "node_val", nullable = true, insertable = true, updatable = true, length = 100)
    public String getNodeVal() {
        return nodeVal;
    }

    public void setNodeVal(String nodeVal) {
        this.nodeVal = nodeVal;
    }

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    public GlobalStatus getGlobalStatus() {
        return globalStatus;
    }

    public void setGlobalStatus(GlobalStatus globalStatus) {
        this.globalStatus = globalStatus;
    }

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "up_status_id", referencedColumnName = "id")
    public GlobalStatus getUpGlobalStatus() {
        return upGlobalStatus;
    }

    public void setUpGlobalStatus(GlobalStatus upGlobalStatus) {
        this.upGlobalStatus = upGlobalStatus;
    }


}
