package com.timanetworks.iov.domain;

import com.timanetworks.iov.core.jpa.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by dukla on 5/8/17.
 */
@Entity
@Table(name = "custom_did_signal")
public class CustomDidSignal extends BaseEntity {
    private String id;
    private String vehModeCode;
    private String protocolVersion;
    private Integer didCode;
    private String signalCode;
    private Integer orderNum;
    private Integer length;
    private Integer preci;
    private Integer bitOffset;
    private String decodeMode;
    private String signalConver;
    private String remark;
    private CustomSignal customSignal;
    private CustomDid customDid;

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
    @Column(name = "did_code", nullable = true, insertable = true, updatable = true)
    public Integer getDidCode() {
        return didCode;
    }

    public void setDidCode(Integer didCode) {
        this.didCode = didCode;
    }

    @Basic
    @Column(name = "signal_code", nullable = true, insertable = true, updatable = true, length = 100)
    public String getSignalCode() {
        return signalCode;
    }

    public void setSignalCode(String signalCode) {
        this.signalCode = signalCode;
    }

    @Basic
    @Column(name = "order_num", nullable = true, insertable = true, updatable = true)
    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Basic
    @Column(name = "length", nullable = true, insertable = true, updatable = true)
    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Basic
    @Column(name = "preci", nullable = true, insertable = true, updatable = true)
    public Integer getPreci() {
        return preci;
    }

    public void setPreci(Integer preci) {
        this.preci = preci;
    }

    @Basic
    @Column(name = "bit_offset", nullable = true, insertable = true, updatable = true)
    public Integer getBitOffset() {
        return bitOffset;
    }

    public void setBitOffset(Integer bitOffset) {
        this.bitOffset = bitOffset;
    }

    @Basic
    @Column(name = "decode_mode", nullable = true, insertable = true, updatable = true, length = 36)
    public String getDecodeMode() {
        return decodeMode;
    }

    public void setDecodeMode(String decodeMode) {
        this.decodeMode = decodeMode;
    }

    @Basic
    @Column(name = "signal_conver", nullable = true, insertable = true, updatable = true, length = 800)
    public String getSignalConver() {
        return signalConver;
    }

    public void setSignalConver(String signalConver) {
        this.signalConver = signalConver;
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

        CustomDidSignal that = (CustomDidSignal) o;

        if (bitOffset != null ? !bitOffset.equals(that.bitOffset) : that.bitOffset != null) return false;
        if (decodeMode != null ? !decodeMode.equals(that.decodeMode) : that.decodeMode != null) return false;
        if (didCode != null ? !didCode.equals(that.didCode) : that.didCode != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (length != null ? !length.equals(that.length) : that.length != null) return false;
        if (orderNum != null ? !orderNum.equals(that.orderNum) : that.orderNum != null) return false;
        if (preci != null ? !preci.equals(that.preci) : that.preci != null) return false;
        if (protocolVersion != null ? !protocolVersion.equals(that.protocolVersion) : that.protocolVersion != null)
            return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (signalCode != null ? !signalCode.equals(that.signalCode) : that.signalCode != null) return false;
        if (signalConver != null ? !signalConver.equals(that.signalConver) : that.signalConver != null) return false;
        if (vehModeCode != null ? !vehModeCode.equals(that.vehModeCode) : that.vehModeCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (vehModeCode != null ? vehModeCode.hashCode() : 0);
        result = 31 * result + (protocolVersion != null ? protocolVersion.hashCode() : 0);
        result = 31 * result + (didCode != null ? didCode.hashCode() : 0);
        result = 31 * result + (signalCode != null ? signalCode.hashCode() : 0);
        result = 31 * result + (orderNum != null ? orderNum.hashCode() : 0);
        result = 31 * result + (length != null ? length.hashCode() : 0);
        result = 31 * result + (preci != null ? preci.hashCode() : 0);
        result = 31 * result + (bitOffset != null ? bitOffset.hashCode() : 0);
        result = 31 * result + (decodeMode != null ? decodeMode.hashCode() : 0);
        result = 31 * result + (signalConver != null ? signalConver.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "signal_id", referencedColumnName = "id")
    public CustomSignal getCustomSignal() {
        return customSignal;
    }

    public void setCustomSignal(CustomSignal customSignal) {
        this.customSignal = customSignal;
    }

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "did_id", referencedColumnName = "id")
    public CustomDid getCustomDid() {
        return customDid;
    }

    public void setCustomDid(CustomDid customDid) {
        this.customDid = customDid;
    }
}
