package kz.iceberg.clients.service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "timeline", schema = "sc_iceberg", catalog = "crm_clients_service")
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
    private short event;
    @Basic
    @Column(name = "object", nullable = false)
    private int object;
    @Basic
    @Column(name = "comment", nullable = false, length = -1)
    private String comment;
    @Basic
    @Column(name = "state", nullable = true)
    private Short state;
    @Basic
    @Column(name = "client", nullable = false)
    private long client;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getAuthor() {
        return author;
    }

    public void setAuthor(long author) {
        this.author = author;
    }

    public short getEvent() {
        return event;
    }

    public void setEvent(short event) {
        this.event = event;
    }

    public int getObject() {
        return object;
    }

    public void setObject(int object) {
        this.object = object;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public long getClient() {
        return client;
    }

    public void setClient(long client) {
        this.client = client;
    }

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
        result = 31 * result + (int) event;
        result = 31 * result + object;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (int) (client ^ (client >>> 32));
        return result;
    }
}
