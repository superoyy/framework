package com.timanetworks.iov.domain;

import com.timanetworks.iov.core.jpa.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by dukla on 6/6/17.
 */
@Entity
@Table(name = "mpd_topic")
public class MpdTopic extends BaseEntity {
    private String id;
    private String topicName;
    private Integer status;

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
    @Column(name = "topic_name", nullable = false, insertable = true, updatable = true, length = 255)
    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    @Basic
    @Column(name = "status", nullable = true, insertable = true, updatable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MpdTopic mpdTopic = (MpdTopic) o;

        if (id != null ? !id.equals(mpdTopic.id) : mpdTopic.id != null) return false;
        if (status != null ? !status.equals(mpdTopic.status) : mpdTopic.status != null) return false;
        if (topicName != null ? !topicName.equals(mpdTopic.topicName) : mpdTopic.topicName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (topicName != null ? topicName.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
