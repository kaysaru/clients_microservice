package kz.iceberg.clients.service.repository;

import kz.iceberg.clients.service.entity.ClientEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    List<ClientEntity> findByNameOrEmails_emailOrAddresses_addressOrPhones_phone(
            String name,
            String emails_email,
            String addresses_address,
            String phones_phone,
            Pageable pageable
            );
}
