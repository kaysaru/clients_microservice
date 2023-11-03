package kz.iceberg.clients.service.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "client", schema = "sc_iceberg", catalog = "crm_clients_service")
public class ClientEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Basic
    @Column(name = "active", nullable = false)
    private boolean active;
    @OneToMany(mappedBy = "clientByClient")
    private Collection<ClientAddressesEntity> clientAddressesById;
    @OneToMany(mappedBy = "clientByClient")
    private Collection<ClientEmailsEntity> clientEmailsById;
    @OneToMany(mappedBy = "clientByClient")
    private Collection<ClientMoreEntity> clientMoresById;
    @OneToMany(mappedBy = "clientByClient")
    private Collection<ClientPhonesEntity> clientPhonesById;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientEntity that = (ClientEntity) o;

        if (id != that.id) return false;
        if (active != that.active) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        return result;
    }

    public Collection<ClientAddressesEntity> getClientAddressesById() {
        return clientAddressesById;
    }

    public void setClientAddressesById(Collection<ClientAddressesEntity> clientAddressesById) {
        this.clientAddressesById = clientAddressesById;
    }

    public Collection<ClientEmailsEntity> getClientEmailsById() {
        return clientEmailsById;
    }

    public void setClientEmailsById(Collection<ClientEmailsEntity> clientEmailsById) {
        this.clientEmailsById = clientEmailsById;
    }

    public Collection<ClientMoreEntity> getClientMoresById() {
        return clientMoresById;
    }

    public void setClientMoresById(Collection<ClientMoreEntity> clientMoresById) {
        this.clientMoresById = clientMoresById;
    }

    public Collection<ClientPhonesEntity> getClientPhonesById() {
        return clientPhonesById;
    }

    public void setClientPhonesById(Collection<ClientPhonesEntity> clientPhonesById) {
        this.clientPhonesById = clientPhonesById;
    }
}
