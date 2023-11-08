package kz.iceberg.clients.service.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import kz.iceberg.clients.service.graphql.federation.GraphQLFederationExtends;
import kz.iceberg.clients.service.graphql.federation.GraphQLFederationExternal;
import kz.iceberg.clients.service.graphql.federation.GraphQLFederationKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Table(name = "client")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@GraphQLFederationKey
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
    @JsonManagedReference
    private Collection<ClientAddressesEntity> addresses;
    @OneToMany(mappedBy = "clientByClient")
    @JsonManagedReference
    private Collection<ClientEmailsEntity> emails;
    @OneToOne(mappedBy = "clientByClient")
    @JsonManagedReference
    private ClientMoreEntity more;
    @OneToMany(mappedBy = "clientByClient")
    @JsonManagedReference
    private Collection<ClientPhonesEntity> phones;

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

    public Collection<ClientAddressesEntity> getAddresses() {
        return addresses;
    }

    public void setAddresses(Collection<ClientAddressesEntity> clientAddressesById) {
        this.addresses = clientAddressesById;
    }

    public Collection<ClientEmailsEntity> getEmails() {
        return emails;
    }

    public void setEmails(Collection<ClientEmailsEntity> clientEmailsById) {
        this.emails = clientEmailsById;
    }

    public ClientMoreEntity getMore() {
        return more;
    }

    public void setMore(ClientMoreEntity clientMoresById) {
        this.more = clientMoresById;
    }

    public Collection<ClientPhonesEntity> getPhones() {
        return phones;
    }

    public void setPhones(Collection<ClientPhonesEntity> clientPhonesById) {
        this.phones = clientPhonesById;
    }
}
