package kz.iceberg.clients.service.repository;

import kz.iceberg.clients.service.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
