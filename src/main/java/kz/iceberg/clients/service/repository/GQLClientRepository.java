package kz.iceberg.clients.service.repository;

import kz.iceberg.clients.service.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface GQLClientRepository extends JpaRepository<ClientEntity, Long> {
}
