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
@Table(name = "sys_permit")
@Entity
public class SysPermit extends BaseEntity {
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

    private SysModule sysModule;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "module_id", referencedColumnName = "id", nullable = false)
    public SysModule getSysModule() {
        return sysModule;
    }

    public void setSysModule(SysModule sysModule) {
        this.sysModule = sysModule;
    }

    private SysRole sysRole;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }
}
