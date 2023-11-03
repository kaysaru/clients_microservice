package kz.iceberg.clients.service.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "client_more", schema = "sc_iceberg", catalog = "crm_clients_service")
public class ClientMoreEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "notes", nullable = false, length = -1)
    private String notes;
    @Basic
    @Column(name = "rate", nullable = false, precision = 0)
    private double rate;
    @Basic
    @Column(name = "identifier", nullable = false, length = 60)
    private String identifier;
    @OneToMany(mappedBy = "clientMoreByClientMore")
    private Collection<ClientLabelEntity> clientLabelsById;
    @ManyToOne
    @JoinColumn(name = "company", referencedColumnName = "id")
    private ClientCompanyEntity clientCompanyByCompany;
    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "id")
    private ClientEntity clientByClient;
    @OneToMany(mappedBy = "clientMoreByClientMore")
    private Collection<ClientTagEntity> clientTagsById;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientMoreEntity that = (ClientMoreEntity) o;

        if (id != that.id) return false;
        if (Double.compare(rate, that.rate) != 0) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
        if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        temp = Double.doubleToLongBits(rate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (identifier != null ? identifier.hashCode() : 0);
        return result;
    }

    public Collection<ClientLabelEntity> getClientLabelsById() {
        return clientLabelsById;
    }

    public void setClientLabelsById(Collection<ClientLabelEntity> clientLabelsById) {
        this.clientLabelsById = clientLabelsById;
    }

    public ClientCompanyEntity getClientCompanyByCompany() {
        return clientCompanyByCompany;
    }

    public void setClientCompanyByCompany(ClientCompanyEntity clientCompanyByCompany) {
        this.clientCompanyByCompany = clientCompanyByCompany;
    }

    public ClientEntity getClientByClient() {
        return clientByClient;
    }

    public void setClientByClient(ClientEntity clientByClient) {
        this.clientByClient = clientByClient;
    }

    public Collection<ClientTagEntity> getClientTagsById() {
        return clientTagsById;
    }

    public void setClientTagsById(Collection<ClientTagEntity> clientTagsById) {
        this.clientTagsById = clientTagsById;
    }
}
