package com.timanetworks.iov.domain;

import com.timanetworks.iov.core.jpa.entity.BaseEntity;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by dukla on 8/9/17.
 */
@Entity
@javax.persistence.Table(name = "operation_type_definition")
public class OperationTypeDefinition   extends BaseEntity {
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

    private String comment;

    @Basic
    @javax.persistence.Column(name = "comment", nullable = true, insertable = true, updatable = true, length = 50)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private String operationTypeCode;

    @Basic
    @javax.persistence.Column(name = "operation_type_code", nullable = false, insertable = true, updatable = true, length = 20)
    public String getOperationTypeCode() {
        return operationTypeCode;
    }

    public void setOperationTypeCode(String operationTypeCode) {
        this.operationTypeCode = operationTypeCode;
    }

    private String operationTypeName;

    @Basic
    @javax.persistence.Column(name = "operation_type_name", nullable = false, insertable = true, updatable = true, length = 20)
    public String getOperationTypeName() {
        return operationTypeName;
    }

    public void setOperationTypeName(String operationTypeName) {
        this.operationTypeName = operationTypeName;
    }

    private String adminAuthType;

    @Basic
    @javax.persistence.Column(name = "admin_auth_type", nullable = false, insertable = true, updatable = true, length = 50)
    public String getAdminAuthType() {
        return adminAuthType;
    }

    public void setAdminAuthType(String adminAuthType) {
        this.adminAuthType = adminAuthType;
    }

    private String internalAuthType;

    @Basic
    @javax.persistence.Column(name = "internal_auth_type", nullable = false, insertable = true, updatable = true, length = 50)
    public String getInternalAuthType() {
        return internalAuthType;
    }

    public void setInternalAuthType(String internalAuthType) {
        this.internalAuthType = internalAuthType;
    }

    private String publicAuthType;

    @Basic
    @javax.persistence.Column(name = "public_auth_type", nullable = false, insertable = true, updatable = true, length = 50)
    public String getPublicAuthType() {
        return publicAuthType;
    }

    public void setPublicAuthType(String publicAuthType) {
        this.publicAuthType = publicAuthType;
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

    private String category;

    @Basic
    @Column(name = "category", nullable = true, insertable = true, updatable = true, length = 255)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperationTypeDefinition that = (OperationTypeDefinition) o;

        if (id != that.id) return false;
        if (adminAuthType != null ? !adminAuthType.equals(that.adminAuthType) : that.adminAuthType != null)
            return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (createdClientId != null ? !createdClientId.equals(that.createdClientId) : that.createdClientId != null)
            return false;
        if (createdClientType != null ? !createdClientType.equals(that.createdClientType) : that.createdClientType != null)
            return false;
        if (createdSource != null ? !createdSource.equals(that.createdSource) : that.createdSource != null)
            return false;
        if (createdTime != null ? !createdTime.equals(that.createdTime) : that.createdTime != null) return false;
        if (internalAuthType != null ? !internalAuthType.equals(that.internalAuthType) : that.internalAuthType != null)
            return false;
        if (operationTypeCode != null ? !operationTypeCode.equals(that.operationTypeCode) : that.operationTypeCode != null)
            return false;
        if (operationTypeName != null ? !operationTypeName.equals(that.operationTypeName) : that.operationTypeName != null)
            return false;
        if (publicAuthType != null ? !publicAuthType.equals(that.publicAuthType) : that.publicAuthType != null)
            return false;
        if (updatedBy != null ? !updatedBy.equals(that.updatedBy) : that.updatedBy != null) return false;
        if (updatedClientId != null ? !updatedClientId.equals(that.updatedClientId) : that.updatedClientId != null)
            return false;
        if (updatedClientType != null ? !updatedClientType.equals(that.updatedClientType) : that.updatedClientType != null)
            return false;
        if (updatedSource != null ? !updatedSource.equals(that.updatedSource) : that.updatedSource != null)
            return false;
        if (updatedTime != null ? !updatedTime.equals(that.updatedTime) : that.updatedTime != null)
            return false;
        if (category != null ? !category.equals(that.category) : that.category != null)
            return false;
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
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (operationTypeCode != null ? operationTypeCode.hashCode() : 0);
        result = 31 * result + (operationTypeName != null ? operationTypeName.hashCode() : 0);
        result = 31 * result + (adminAuthType != null ? adminAuthType.hashCode() : 0);
        result = 31 * result + (internalAuthType != null ? internalAuthType.hashCode() : 0);
        result = 31 * result + (publicAuthType != null ? publicAuthType.hashCode() : 0);
        result = 31 * result + (createdSource != null ? createdSource.hashCode() : 0);
        result = 31 * result + (updatedSource != null ? updatedSource.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
