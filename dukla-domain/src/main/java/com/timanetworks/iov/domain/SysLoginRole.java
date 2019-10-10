package com.timanetworks.iov.domain;

import com.timanetworks.iov.core.jpa.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: dukla
 * Date: 13-8-2
 * Time: 上午11:30
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "sys_login_role")
@Entity
public class SysLoginRole extends BaseEntity {
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

    private SysRole sysRole;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    private SysLogin sysLogin;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "login_id", referencedColumnName = "id", nullable = false)
    public SysLogin getSysLogin() {
        return sysLogin;
    }

    public void setSysLogin(SysLogin sysLogin) {
        this.sysLogin = sysLogin;
    }
}
