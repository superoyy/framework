package com.timanetworks.iov.domain;

import com.timanetworks.iov.core.jpa.entity.BaseEntity;
import javax.persistence.*;

/**
 * Created by dukla on 5/23/17.
 */
@Entity
@Table(name = "remote_command")
public class RemoteCommand  extends BaseEntity {
    private Integer id;
    private String code;
    private String operate;
    private Integer applicationId;
    private Integer messageType;
    private String nameEn;
    private String nameCn;
    private Integer operateTarget;
    private Integer operateContext;
    private Integer exitsContent;
    private String remark;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code", nullable = false, insertable = true, updatable = true, length = 225)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "operate", nullable = false, insertable = true, updatable = true, length = 225)
    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    @Basic
    @Column(name = "application_id", nullable = false, insertable = true, updatable = true)
    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    @Basic
    @Column(name = "message_type", nullable = false, insertable = true, updatable = true)
    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    @Basic
    @Column(name = "name_en", nullable = true, insertable = true, updatable = true, length = 225)
    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    @Basic
    @Column(name = "name_cn", nullable = true, insertable = true, updatable = true, length = 225)
    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    @Basic
    @Column(name = "operate_target", nullable = true, insertable = true, updatable = true)
    public Integer getOperateTarget() {
        return operateTarget;
    }

    public void setOperateTarget(Integer operateTarget) {
        this.operateTarget = operateTarget;
    }

    @Basic
    @Column(name = "operate_context", nullable = true, insertable = true, updatable = true)
    public Integer getOperateContext() {
        return operateContext;
    }

    public void setOperateContext(Integer operateContext) {
        this.operateContext = operateContext;
    }

    @Basic
    @Column(name = "exits_content", nullable = false, insertable = true, updatable = true)
    public Integer getExitsContent() {
        return exitsContent;
    }

    public void setExitsContent(Integer exitsContent) {
        this.exitsContent = exitsContent;
    }

    @Basic
    @Column(name = "remark", nullable = true, insertable = true, updatable = true, length = 45)
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

        RemoteCommand that = (RemoteCommand) o;

        if (!applicationId.equals(that.applicationId)) return false;
        if (!exitsContent.equals(that.exitsContent)) return false;
        if (!messageType.equals(that.messageType)) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nameCn != null ? !nameCn.equals(that.nameCn) : that.nameCn != null) return false;
        if (nameEn != null ? !nameEn.equals(that.nameEn) : that.nameEn != null) return false;
        if (operate != null ? !operate.equals(that.operate) : that.operate != null) return false;
        if (operateContext != null ? !operateContext.equals(that.operateContext) : that.operateContext != null)
            return false;
        if (operateTarget != null ? !operateTarget.equals(that.operateTarget) : that.operateTarget != null)
            return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (operate != null ? operate.hashCode() : 0);
        result = 31 * result + applicationId;
        result = 31 * result + messageType;
        result = 31 * result + (nameEn != null ? nameEn.hashCode() : 0);
        result = 31 * result + (nameCn != null ? nameCn.hashCode() : 0);
        result = 31 * result + (operateTarget != null ? operateTarget.hashCode() : 0);
        result = 31 * result + (operateContext != null ? operateContext.hashCode() : 0);
        result = 31 * result + exitsContent;
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
