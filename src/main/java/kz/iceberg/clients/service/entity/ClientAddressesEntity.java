package kz.iceberg.clients.service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "client_addresses", schema = "sc_iceberg", catalog = "crm_clients_service")
public class ClientAddressesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "address", nullable = false, length = -1)
    private String address;
    @Basic
    @Column(name = "main", nullable = false)
    private boolean main;
    @Basic
    @Column(name = "delivery", nullable = false)
    private boolean delivery;
    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "id")
    private ClientEntity clientByClient;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientAddressesEntity that = (ClientAddressesEntity) o;

        if (id != that.id) return false;
        if (main != that.main) return false;
        if (delivery != that.delivery) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (main ? 1 : 0);
        result = 31 * result + (delivery ? 1 : 0);
        return result;
    }

    public ClientEntity getClientByClient() {
        return clientByClient;
    }

    public void setClientByClient(ClientEntity clientByClient) {
        this.clientByClient = clientByClient;
    }
}
