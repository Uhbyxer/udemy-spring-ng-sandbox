package com.jhipcon.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Session.
 */
@Entity
@Table(name = "session")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Session implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "decription")
    private byte[] decription;

    @Column(name = "decription_content_type")
    private String decriptionContentType;

    @NotNull
    @Column(name = "start_date_time", nullable = false)
    private ZonedDateTime startDateTime;

    @NotNull
    @Column(name = "end_date_time", nullable = false)
    private ZonedDateTime endDateTime;

    @ManyToMany(mappedBy = "sessions")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Speaker> speakers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Session title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getDecription() {
        return decription;
    }

    public Session decription(byte[] decription) {
        this.decription = decription;
        return this;
    }

    public void setDecription(byte[] decription) {
        this.decription = decription;
    }

    public String getDecriptionContentType() {
        return decriptionContentType;
    }

    public Session decriptionContentType(String decriptionContentType) {
        this.decriptionContentType = decriptionContentType;
        return this;
    }

    public void setDecriptionContentType(String decriptionContentType) {
        this.decriptionContentType = decriptionContentType;
    }

    public ZonedDateTime getStartDateTime() {
        return startDateTime;
    }

    public Session startDateTime(ZonedDateTime startDateTime) {
        this.startDateTime = startDateTime;
        return this;
    }

    public void setStartDateTime(ZonedDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public ZonedDateTime getEndDateTime() {
        return endDateTime;
    }

    public Session endDateTime(ZonedDateTime endDateTime) {
        this.endDateTime = endDateTime;
        return this;
    }

    public void setEndDateTime(ZonedDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Set<Speaker> getSpeakers() {
        return speakers;
    }

    public Session speakers(Set<Speaker> speakers) {
        this.speakers = speakers;
        return this;
    }

    public Session addSpeakers(Speaker speaker) {
        this.speakers.add(speaker);
        speaker.getSessions().add(this);
        return this;
    }

    public Session removeSpeakers(Speaker speaker) {
        this.speakers.remove(speaker);
        speaker.getSessions().remove(this);
        return this;
    }

    public void setSpeakers(Set<Speaker> speakers) {
        this.speakers = speakers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Session session = (Session) o;
        if (session.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), session.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Session{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", decription='" + getDecription() + "'" +
            ", decriptionContentType='" + getDecriptionContentType() + "'" +
            ", startDateTime='" + getStartDateTime() + "'" +
            ", endDateTime='" + getEndDateTime() + "'" +
            "}";
    }
}
