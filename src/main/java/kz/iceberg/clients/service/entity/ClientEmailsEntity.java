package kz.iceberg.clients.service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import kz.iceberg.clients.service.graphql.federation.GraphQLFederationExternal;
import kz.iceberg.clients.service.graphql.federation.GraphQLFederationKey;

@Entity
@Table(name = "client_emails")
public class ClientEmailsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "email", nullable = false, length = -1)
    private String email;
    @Basic
    @Column(name = "main", nullable = false)
    private boolean main;
    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "id")
    @JsonBackReference
    private ClientEntity clientByClient;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientEmailsEntity that = (ClientEmailsEntity) o;

        if (id != that.id) return false;
        if (main != that.main) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (main ? 1 : 0);
        return result;
    }

    public ClientEntity getClientByClient() {
        return clientByClient;
    }

    public void setClientByClient(ClientEntity clientByClient) {
        this.clientByClient = clientByClient;
    }
}
