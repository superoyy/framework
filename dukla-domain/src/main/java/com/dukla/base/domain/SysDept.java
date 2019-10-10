package com.dukla.base.domain;

import com.dukla.base.jpa.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: dukla
 * Date: 13-8-6
 * Time: 上午9:46
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "sys_dept")
@Entity
public class SysDept extends BaseEntity {
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

    private String deptName;

    @Column(name = "dept_name", nullable = true, insertable = true, updatable = true, length = 200, precision = 0)
    @Basic
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    private String deptNo;

    @Column(name = "dept_no", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    private Integer deptOrder;

    @Column(name = "dept_order", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Integer getDeptOrder() {
        return deptOrder;
    }

    public void setDeptOrder(Integer deptOrder) {
        this.deptOrder = deptOrder;
    }

    private String deptState;

    @Column(name = "dept_state", nullable = true, insertable = true, updatable = true, length = 2, precision = 0)
    @Basic
    public String getDeptState() {
        return deptState;
    }

    public void setDeptState(String deptState) {
        this.deptState = deptState;
    }

    private String deptDesc;

    @Column(name = "dept_desc", nullable = true, insertable = true, updatable = true, length = 300, precision = 0)
    @Basic
    public String getDeptDesc() {
        return deptDesc;
    }

    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }

}
