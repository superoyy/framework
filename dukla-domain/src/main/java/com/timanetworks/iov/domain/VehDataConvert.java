package com.timanetworks.iov.domain;

import com.timanetworks.iov.core.jpa.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by dukla on 7/5/17.
 */
@Entity
@Table(name = "veh_data_convert")
public class VehDataConvert extends BaseEntity {
    private String id;
    private String vehModeCode;
    private String protocolVersion;
    private String dataConvert;
    private Integer convertType;

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
    @Column(name = "protocol_version", nullable = true, insertable = true, updatable = true, length = 50)
    public String getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    @Basic
    @Column(name = "data_convert", nullable = true, insertable = true, updatable = true, length = 2000)
    public String getDataConvert() {
        return dataConvert;
    }

    public void setDataConvert(String dataConvert) {
        this.dataConvert = dataConvert;
    }

    @Basic
    @Column(name = "convert_type", nullable = true, insertable = true, updatable = true)
    public Integer getConvertType() {
        return convertType;
    }

    public void setConvertType(Integer convertType) {
        this.convertType = convertType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VehDataConvert that = (VehDataConvert) o;

        if (convertType != null ? !convertType.equals(that.convertType) : that.convertType != null) return false;
        if (dataConvert != null ? !dataConvert.equals(that.dataConvert) : that.dataConvert != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (protocolVersion != null ? !protocolVersion.equals(that.protocolVersion) : that.protocolVersion != null)
            return false;
        if (vehModeCode != null ? !vehModeCode.equals(that.vehModeCode) : that.vehModeCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (vehModeCode != null ? vehModeCode.hashCode() : 0);
        result = 31 * result + (protocolVersion != null ? protocolVersion.hashCode() : 0);
        result = 31 * result + (dataConvert != null ? dataConvert.hashCode() : 0);
        result = 31 * result + (convertType != null ? convertType.hashCode() : 0);
        return result;
    }
}
