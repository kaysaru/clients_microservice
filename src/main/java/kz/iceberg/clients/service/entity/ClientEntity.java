package kz.iceberg.clients.service.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import kz.iceberg.clients.service.graphql.federation.GraphQLFederationKey;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "client")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Basic
    @Column(name = "active", nullable = false)
    private boolean active;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Collection<ClientAddressesEntity> addresses;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Collection<ClientEmailsEntity> emails;
    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private ClientMoreEntity more;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Collection<ClientPhonesEntity> phones;

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

    public <T> void concatLists(Collection<T> target, Collection<T> source) {
        for(T el : source) {
            target.add(el);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Collection<ClientAddressesEntity> getAddresses() {
        return addresses;
    }

    public void setAddresses(Collection<ClientAddressesEntity> addresses) {
        this.addresses = addresses;
    }

    public Collection<ClientEmailsEntity> getEmails() {
        return emails;
    }

    public void setEmails(Collection<ClientEmailsEntity> emails) {
        this.emails = emails;
    }

    public ClientMoreEntity getMore() {
        return more;
    }

    public void setMore(ClientMoreEntity more) {
        this.more = more;
    }

    public Collection<ClientPhonesEntity> getPhones() {
        return phones;
    }

    public void setPhones(Collection<ClientPhonesEntity> phones) {
        this.phones = phones;
    }
}
