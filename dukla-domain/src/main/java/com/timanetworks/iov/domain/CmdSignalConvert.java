package com.timanetworks.iov.domain;

import com.timanetworks.iov.core.jpa.entity.BaseEntity;

import javax.persistence.*;


@Entity
@Table(name = "cmd_signal_convert")
public class CmdSignalConvert extends BaseEntity {

    private String id;
    private String vehicleMode;
    private String protocolVersion;
    private String opType;
    private String opSubCode;
    private String op;
    private String didCode;
    private String convert;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name="vehicle_mode", insertable = true, updatable = true, length = 225)
    public String getVehicleMode() {
        return vehicleMode;
    }

    public void setVehicleMode(String vehicleMode) {
        this.vehicleMode = vehicleMode;
    }

    @Basic
    @Column(name="protocol_version", insertable = true, updatable = true, length = 10)
    public String getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    @Basic
    @Column(name="op_type", insertable = true, updatable = true, length = 100)
    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    @Basic
    @Column(name="op_sub_code", insertable = true, updatable = true, length = 10)
    public String getOpSubCode() {
        return opSubCode;
    }

    public void setOpSubCode(String opSubCode) {
        this.opSubCode = opSubCode;
    }

    @Basic
    @Column(name="op", insertable = true, updatable = true, length = 45)
    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    @Basic
    @Column(name="did_code", insertable = true, updatable = true, length = 45)
    public String getDidCode() {
        return didCode;
    }

    public void setDidCode(String didCode) {
        this.didCode = didCode;
    }

    @Basic
    @Column(name="convert", insertable = true, updatable = true, length = 500)
    public String getConvert() {
        return convert;
    }

    public void setConvert(String convert) {
        this.convert = convert;
    }
}
