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
@Table(name = "sys_permit_function")
@Entity
public class SysPermitFunction extends BaseEntity {
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

    private SysPermit sysPermit;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "permit_id", referencedColumnName = "id", nullable = false)
    public SysPermit getSysPermit() {
        return sysPermit;
    }

    public void setSysPermit(SysPermit sysPermit) {
        this.sysPermit = sysPermit;
    }

    private SysModuleFunction sysModuleFunction;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "func_id", referencedColumnName = "id", nullable = false)
    public SysModuleFunction getSysModuleFunction() {
        return sysModuleFunction;
    }

    public void setSysModuleFunction(SysModuleFunction sysModuleFunction) {
        this.sysModuleFunction = sysModuleFunction;
    }
}
