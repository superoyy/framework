package com.timanetworks.iov.domain;

import com.timanetworks.iov.core.jpa.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by dukla on 5/8/17.
 */
@Entity
@Table(name = "veh_status_struc_templ")
public class VehStatusStrucTempl extends BaseEntity{
    private String id;
    private String upStrucId;
    private String statusId;
    private Integer nodeType;
    private Integer nodeCount;
    private String didId;
    private VehStatusTempl vehStatusTempl;
    private String nodeVal;

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

    @Basic
    @Column(name = "up_struc_id", nullable = true, insertable = true, updatable = true, length = 36)
    public String getUpStrucId() {
        return upStrucId;
    }

    public void setUpStrucId(String upStrucId) {
        this.upStrucId = upStrucId;
    }

    @Basic
    @Column(name = "status_id", nullable = true, insertable = true, updatable = true, length = 36)
    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    @Basic
    @Column(name = "node_type", nullable = true, insertable = true, updatable = true)
    public Integer getNodeType() {
        return nodeType;
    }

    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }

    @Basic
    @Column(name = "node_count", nullable = true, insertable = true, updatable = true)
    public Integer getNodeCount() {
        return nodeCount;
    }

    public void setNodeCount(Integer nodeCount) {
        this.nodeCount = nodeCount;
    }

    @Basic
    @Column(name = "did_id", nullable = true, insertable = true, updatable = true, length = 36)
    public String getDidId() {
        return didId;
    }

    public void setDidId(String didId) {
        this.didId = didId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VehStatusStrucTempl that = (VehStatusStrucTempl) o;

        if (didId != null ? !didId.equals(that.didId) : that.didId != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nodeCount != null ? !nodeCount.equals(that.nodeCount) : that.nodeCount != null) return false;
        if (nodeType != null ? !nodeType.equals(that.nodeType) : that.nodeType != null) return false;
        if (statusId != null ? !statusId.equals(that.statusId) : that.statusId != null) return false;
        if (upStrucId != null ? !upStrucId.equals(that.upStrucId) : that.upStrucId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (upStrucId != null ? upStrucId.hashCode() : 0);
        result = 31 * result + (statusId != null ? statusId.hashCode() : 0);
        result = 31 * result + (nodeType != null ? nodeType.hashCode() : 0);
        result = 31 * result + (nodeCount != null ? nodeCount.hashCode() : 0);
        result = 31 * result + (didId != null ? didId.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "templ_id", referencedColumnName = "id")
    public VehStatusTempl getVehStatusTempl() {
        return vehStatusTempl;
    }

    public void setVehStatusTempl(VehStatusTempl vehStatusTempl) {
        this.vehStatusTempl = vehStatusTempl;
    }

    @Basic
    @Column(name = "node_val", nullable = true, insertable = true, updatable = true, length = 100)
    public String getNodeVal() {
        return nodeVal;
    }

    public void setNodeVal(String nodeVal) {
        this.nodeVal = nodeVal;
    }
}
