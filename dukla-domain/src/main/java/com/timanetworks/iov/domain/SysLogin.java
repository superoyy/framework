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
@Table(name = "sys_login")
@Entity
public class SysLogin extends BaseEntity {
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

    private String userName;

    @Column(name = "user_name", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String loginPwd;

    @Column(name = "login_pwd", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    private String loginState;

    @Column(name = "login_state", nullable = true, insertable = true, updatable = true, length = 2, precision = 0)
    @Basic
    public String getLoginState() {
        return loginState;
    }

    public void setLoginState(String loginState) {
        this.loginState = loginState;
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


    private Integer loginOrder;

    @Column(name = "login_order", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Integer getLoginOrder() {
        return loginOrder;
    }

    public void setLoginOrder(Integer loginOrder) {
        this.loginOrder = loginOrder;
    }

    private String loginName;

    @Column(name = "login_name", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    private SysDept sysDept;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "dept_id", referencedColumnName = "id")
    public SysDept getSysDept() {
        return sysDept;
    }

    public void setSysDept(SysDept sysDept) {
        this.sysDept = sysDept;
    }

    private String phoneNum;

    @Column(name = "phone_num", nullable = true, insertable = true, updatable = true, length = 30, precision = 0)
    @Basic
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    private String mailAddr;

    @Column(name = "mail_addr", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getMailAddr() {
        return mailAddr;
    }

    public void setMailAddr(String mailAddr) {
        this.mailAddr = mailAddr;
    }
}
