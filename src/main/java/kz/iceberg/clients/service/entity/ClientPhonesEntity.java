package kz.iceberg.clients.service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "client_phones")
public class ClientPhonesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "phone", nullable = false, length = -1)
    private String phone;
    @Basic
    @Column(name = "main", nullable = false)
    private boolean main;
    @Basic
    @Column(name = "whatsapp", nullable = false)
    private boolean whatsapp;
    @Basic
    @Column(name = "viber", nullable = false)
    private boolean viber;
    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "id")
    @JsonBackReference
    private ClientEntity client;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
    }

    public boolean isWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(boolean whatsapp) {
        this.whatsapp = whatsapp;
    }

    public boolean isViber() {
        return viber;
    }

    public void setViber(boolean viber) {
        this.viber = viber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientPhonesEntity that = (ClientPhonesEntity) o;

        if (id != that.id) return false;
        if (main != that.main) return false;
        if (whatsapp != that.whatsapp) return false;
        if (viber != that.viber) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (main ? 1 : 0);
        result = 31 * result + (whatsapp ? 1 : 0);
        result = 31 * result + (viber ? 1 : 0);
        return result;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity clientByClient) {
        this.client = clientByClient;
    }
}
