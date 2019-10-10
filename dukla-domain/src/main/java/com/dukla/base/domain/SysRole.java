package com.dukla.base.domain;

import com.dukla.base.jpa.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: dukla
 * Date: 13-8-2
 * Time: 上午11:30
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "sys_role")
@Entity
public class SysRole extends BaseEntity {
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

    private String roleName;

    @Column(name = "role_name", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    private String roleState;

    @Column(name = "role_state", nullable = true, insertable = true, updatable = true, length = 2, precision = 0)
    @Basic
    public String getRoleState() {
        return roleState;
    }

    public void setRoleState(String roleState) {
        this.roleState = roleState;
    }

    private String roleDesc;

    @Column(name = "role_desc", nullable = true, insertable = true, updatable = true, length = 1000, precision = 0)
    @Basic
    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    private Integer roleOrder;

    @Column(name = "role_order", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Integer getRoleOrder() {
        return roleOrder;
    }

    public void setRoleOrder(Integer roleOrder) {
        this.roleOrder = roleOrder;
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
