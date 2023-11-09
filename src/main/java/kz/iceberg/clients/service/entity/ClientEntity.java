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
    @OneToMany(mappedBy = "client")
    @JsonManagedReference
    private Collection<ClientAddressesEntity> addresses;
    @OneToMany(mappedBy = "client")
    @JsonManagedReference
    private Collection<ClientEmailsEntity> emails;
    @OneToOne(mappedBy = "client")
    @JsonManagedReference
    private ClientMoreEntity more;
    @OneToMany(mappedBy = "client")
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
}
