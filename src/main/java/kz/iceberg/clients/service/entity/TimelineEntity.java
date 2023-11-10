package kz.iceberg.clients.service.entity;

import jakarta.persistence.*;
import kz.iceberg.clients.service.wrapper.enums.TimelineEvent;
import lombok.Data;

@Entity
@Data
@Table(name = "timeline")
public class TimelineEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "date", nullable = false)
    private long date;
    @Basic
    @Column(name = "author", nullable = false)
    private long author;
    @Basic
    @Column(name = "event", nullable = false)
    private TimelineEvent event;
    @Basic
    @Column(name = "object", nullable = false)
    private int object;
    @Basic
    @Column(name = "comment", nullable = true)
    private String comment;
    @Basic
    @Column(name = "state", nullable = true)
    private Short state;
    @Basic
    @Column(name = "client", nullable = false)
    private long client;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimelineEntity that = (TimelineEntity) o;

        if (id != that.id) return false;
        if (date != that.date) return false;
        if (author != that.author) return false;
        if (event != that.event) return false;
        if (object != that.object) return false;
        if (client != that.client) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (date ^ (date >>> 32));
        result = 31 * result + (int) (author ^ (author >>> 32));
        result = 31 * result + (int) event.hashCode();
        result = 31 * result + object;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (int) (client ^ (client >>> 32));
        return result;
    }
}
