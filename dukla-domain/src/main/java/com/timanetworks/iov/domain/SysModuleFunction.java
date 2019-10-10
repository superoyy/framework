package com.timanetworks.iov.domain;

import com.timanetworks.iov.core.jpa.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: dukla
 * Date: 13-8-2
 * Time: 上午11:30
 */
@Table(name = "sys_module_function")
@Entity
public class SysModuleFunction extends BaseEntity {
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

    private String funcName;

    @Column(name = "func_name", nullable = false, insertable = true, updatable = true, length = 200, precision = 0)
    @Basic
    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    private String url;

    @javax.persistence.Column(name = "url", nullable = true, insertable = true, updatable = true, length = 300, precision = 0)
    @Basic
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    private Integer funcOrder;

    @Column(name = "func_order", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Integer getFuncOrder() {
        return funcOrder;
    }

    public void setFuncOrder(Integer funcOrder) {
        this.funcOrder = funcOrder;
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


    private String funcDesc;

    @Column(name = "func_desc", nullable = true, insertable = true, updatable = true, length = 300, precision = 0)
    @Basic
    public String getFuncDesc() {
        return funcDesc;
    }

    public void setFuncDesc(String funcDesc) {
        this.funcDesc = funcDesc;
    }

}
