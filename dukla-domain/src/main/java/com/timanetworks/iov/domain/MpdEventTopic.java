package com.timanetworks.iov.domain;

import com.timanetworks.iov.core.jpa.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by dukla on 6/6/17.
 */
@Entity
@Table(name = "mpd_event_topic")
public class MpdEventTopic extends BaseEntity {
    private String id;
    private String eventCode;
    private String signalFilter;
    private String alias;
    private MpdTopic mpdTopic;

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
    @Column(name = "event_code", nullable = true, insertable = true, updatable = true, length = 255)
    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    @Basic
    @Column(name = "signal_filter", nullable = true, insertable = true, updatable = true, length = 1000)
    public String getSignalFilter() {
        return signalFilter;
    }

    public void setSignalFilter(String signalFilter) {
        this.signalFilter = signalFilter;
    }

    @Basic
    @Column(name = "alias", nullable = true, insertable = true, updatable = true, length = 1000)
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MpdEventTopic that = (MpdEventTopic) o;

        if (alias != null ? !alias.equals(that.alias) : that.alias != null) return false;
        if (eventCode != null ? !eventCode.equals(that.eventCode) : that.eventCode != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (signalFilter != null ? !signalFilter.equals(that.signalFilter) : that.signalFilter != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (eventCode != null ? eventCode.hashCode() : 0);
        result = 31 * result + (signalFilter != null ? signalFilter.hashCode() : 0);
        result = 31 * result + (alias != null ? alias.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mpd_topic_id", referencedColumnName = "id", nullable = true)
    public MpdTopic getMpdTopic() {
        return mpdTopic;
    }

    public void setMpdTopic(MpdTopic mpdTopic) {
        this.mpdTopic = mpdTopic;
    }
}
