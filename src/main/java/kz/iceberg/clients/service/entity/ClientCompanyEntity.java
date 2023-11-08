package kz.iceberg.clients.service.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "client_company")
public class ClientCompanyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Basic
    @Column(name = "bin", nullable = false)
    private long bin;
    @OneToMany(mappedBy = "company")
    private Collection<ClientMoreEntity> clientMoresById;

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

    public long getBin() {
        return bin;
    }

    public void setBin(long bin) {
        this.bin = bin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientCompanyEntity that = (ClientCompanyEntity) o;

        if (id != that.id) return false;
        if (bin != that.bin) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (bin ^ (bin >>> 32));
        return result;
    }

    public Collection<ClientMoreEntity> getClientMoresById() {
        return clientMoresById;
    }

    public void setClientMoresById(Collection<ClientMoreEntity> clientMoresById) {
        this.clientMoresById = clientMoresById;
    }
}
