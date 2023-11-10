package kz.iceberg.clients.service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity
@Data
@Table(name = "client_more")
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
    @OneToOne(mappedBy = "clientMoreByClientMore", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private ClientLabelEntity labels;
    @ManyToOne
    @JoinColumn(name = "company", referencedColumnName = "id")
    private ClientCompanyEntity company;
    @OneToOne
    @JoinColumn(name = "client", referencedColumnName = "id")
    @JsonBackReference
    private ClientEntity client;
    @OneToOne(mappedBy = "clientMoreByClientMore", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private ClientTagEntity tags;

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
}
