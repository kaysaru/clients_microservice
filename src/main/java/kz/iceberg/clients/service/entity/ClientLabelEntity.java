package kz.iceberg.clients.service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "client_label", schema = "sc_iceberg", catalog = "crm_clients_service")
public class ClientLabelEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "bad", nullable = false)
    private boolean bad;
    @Basic
    @Column(name = "supplier", nullable = false)
    private boolean supplier;
    @Basic
    @Column(name = "ban", nullable = false)
    private boolean ban;
    @ManyToOne
    @JoinColumn(name = "client_more", referencedColumnName = "id")
    private ClientMoreEntity clientMoreByClientMore;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isBad() {
        return bad;
    }

    public void setBad(boolean bad) {
        this.bad = bad;
    }

    public boolean isSupplier() {
        return supplier;
    }

    public void setSupplier(boolean supplier) {
        this.supplier = supplier;
    }

    public boolean isBan() {
        return ban;
    }

    public void setBan(boolean ban) {
        this.ban = ban;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientLabelEntity that = (ClientLabelEntity) o;

        if (id != that.id) return false;
        if (bad != that.bad) return false;
        if (supplier != that.supplier) return false;
        if (ban != that.ban) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (bad ? 1 : 0);
        result = 31 * result + (supplier ? 1 : 0);
        result = 31 * result + (ban ? 1 : 0);
        return result;
    }

    public ClientMoreEntity getClientMoreByClientMore() {
        return clientMoreByClientMore;
    }

    public void setClientMoreByClientMore(ClientMoreEntity clientMoreByClientMore) {
        this.clientMoreByClientMore = clientMoreByClientMore;
    }
}
