package kz.iceberg.clients.service;

import jakarta.transaction.Transactional;
import kz.iceberg.clients.service.entity.ClientEntity;
import kz.iceberg.clients.service.repository.ClientRepository;
import kz.iceberg.clients.service.wrapper.FilterWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public Optional<ClientEntity> list(Long id) {
        return clientRepository.findById(id);
    }

    @Transactional
    public Optional<List<ClientEntity>> list(FilterWrapper filter) {
        ClientEntity entity = ClientEntity.builder()
                .name(filter.getSearch())
                .build();
        //clientRepository.findBy(ClientEntity.builder().name("s").build());
        return Optional.empty();
    }
}
