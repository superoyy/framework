package com.timanetworks.iov.domain;

import com.timanetworks.iov.core.jpa.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: dukla
 * Date: 13-8-6
 * Time: 上午10:06
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "sys_codes")
@Entity
public class SysCodes extends BaseEntity {

    private String id;

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


    private String codeState;

    @Column(name = "code_state", nullable = true, insertable = true, updatable = true, length = 2, precision = 0)
    @Basic
    public String getCodeState() {
        return codeState;
    }

    public void setCodeState(String codeState) {
        this.codeState = codeState;
    }


    private String upId;

    @Column(name = "up_id", nullable = true, insertable = true, updatable = true, length = 36, precision = 0)
    @Basic
    public String getUpId() {
        return upId;
    }

    public void setUpId(String upId) {
        this.upId = upId;
    }

    private String codeType;

    @Column(name = "code_type", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    private String codeName;

    @Column(name = "code_name", nullable = true, insertable = true, updatable = true, length = 200, precision = 0)
    @Basic
    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    private String codeKey;

    @Column(name = "code_key", nullable = true, insertable = true, updatable = true, length = 200, precision = 0)
    @Basic
    public String getCodeKey() {
        return codeKey;
    }

    public void setCodeKey(String codeKey) {
        this.codeKey = codeKey;
    }

    private String codeValue;

    @Column(name = "code_value", nullable = true, insertable = true, updatable = true, length = 1000, precision = 0)
    @Basic
    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    private String codeValueType;

    @Column(name = "code_value_type", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public String getCodeValueType() {
        return codeValueType;
    }

    public void setCodeValueType(String codeValueType) {
        this.codeValueType = codeValueType;
    }

    private String codeDesc;

    @Column(name = "code_desc", nullable = true, insertable = true, updatable = true, length = 1000, precision = 0)
    @Basic
    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }

    private Integer codeOrder;

    @Column(name = "code_order", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Integer getCodeOrder() {
        return codeOrder;
    }

    public void setCodeOrder(Integer codeOrder) {
        this.codeOrder = codeOrder;
    }

    private String remainField;

    @Column(name = "remain_field", nullable = true, insertable = true, updatable = true, length = 1000, precision = 0)
    @Basic
    public String getRemainField() {
        return remainField;
    }

    public void setRemainField(String remainField) {
        this.remainField = remainField;
    }

    private String maintainType;

    @Column(name = "maintain_type", nullable = true, insertable = true, updatable = true, length = 2, precision = 0)
    @Basic
    public String getMaintainType() {
        return maintainType;
    }

    public void setMaintainType(String maintainType) {
        this.maintainType = maintainType;
    }

}
