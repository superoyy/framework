package com.timanetworks.iov.domain;

import com.timanetworks.iov.core.jpa.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: dukla
 * Date: 13-8-5
 * Time: 上午10:44
 */
@Table(name = "sys_module")
@Entity
public class SysModule extends BaseEntity {
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

    private String upId;

    @Column(name = "up_id", nullable = true, insertable = true, updatable = true, length = 36, precision = 0)
    @Basic
    public String getUpId() {
        return upId;
    }

    public void setUpId(String upId) {
        this.upId = upId;
    }

    private String moduleNo;

    @Column(name = "module_no", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getModuleNo() {
        return moduleNo;
    }

    public void setModuleNo(String moduleNo) {
        this.moduleNo = moduleNo;
    }

    private String moduleName;

    @Column(name = "module_name", nullable = true, insertable = true, updatable = true, length = 200, precision = 0)
    @Basic
    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    private String moduleDesc;

    @Column(name = "module_desc", nullable = true, insertable = true, updatable = true, length = 200, precision = 0)
    @Basic
    public String getModuleDesc() {
        return moduleDesc;
    }

    public void setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc;
    }

    private Integer nodeLevel;

    @Column(name = "node_level", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Integer getNodeLevel() {
        return nodeLevel;
    }

    public void setNodeLevel(Integer nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    private String url;

    @Column(name = "url", nullable = true, insertable = true, updatable = true, length = 300, precision = 0)
    @Basic
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String icon;

    @Column(name = "icon", nullable = true, insertable = true, updatable = true, length = 50, precision = 0)
    @Basic
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    private Integer moduleOrder;

    @Column(name = "module_order", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Integer getModuleOrder() {
        return moduleOrder;
    }

    public void setModuleOrder(Integer moduleOrder) {
        this.moduleOrder = moduleOrder;
    }

    private String moduleState;

    @Column(name = "module_state", nullable = true, insertable = true, updatable = true, length = 2, precision = 0)
    @Basic
    public String getModuleState() {
        return moduleState;
    }

    public void setModuleState(String moduleState) {
        this.moduleState = moduleState;
    }

}
