package kz.iceberg.clients.service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "client_tag")
public class ClientTagEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "rich", nullable = false)
    private boolean rich;
    @Basic
    @Column(name = "good", nullable = false)
    private boolean good;
    @Basic
    @Column(name = "jurface", nullable = false)
    private boolean jurface;
    @Basic
    @Column(name = "charity", nullable = false)
    private boolean charity;
    @Basic
    @Column(name = "insolvent", nullable = false)
    private boolean insolvent;
    @OneToOne
    @JoinColumn(name = "client_more", referencedColumnName = "id")
    @JsonBackReference
    private ClientMoreEntity clientMoreByClientMore;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isRich() {
        return rich;
    }

    public void setRich(boolean rich) {
        this.rich = rich;
    }

    public boolean isGood() {
        return good;
    }

    public void setGood(boolean good) {
        this.good = good;
    }

    public boolean isJurface() {
        return jurface;
    }

    public void setJurface(boolean jurface) {
        this.jurface = jurface;
    }

    public boolean isCharity() {
        return charity;
    }

    public void setCharity(boolean charity) {
        this.charity = charity;
    }

    public boolean isInsolvent() {
        return insolvent;
    }

    public void setInsolvent(boolean insolvent) {
        this.insolvent = insolvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientTagEntity that = (ClientTagEntity) o;

        if (id != that.id) return false;
        if (rich != that.rich) return false;
        if (good != that.good) return false;
        if (jurface != that.jurface) return false;
        if (charity != that.charity) return false;
        if (insolvent != that.insolvent) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (rich ? 1 : 0);
        result = 31 * result + (good ? 1 : 0);
        result = 31 * result + (jurface ? 1 : 0);
        result = 31 * result + (charity ? 1 : 0);
        result = 31 * result + (insolvent ? 1 : 0);
        return result;
    }

    public ClientMoreEntity getClientMoreByClientMore() {
        return clientMoreByClientMore;
    }

    public void setClientMoreByClientMore(ClientMoreEntity clientMoreByClientMore) {
        this.clientMoreByClientMore = clientMoreByClientMore;
    }
}
