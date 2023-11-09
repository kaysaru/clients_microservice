package kz.iceberg.clients.service;

import jakarta.transaction.Transactional;
import kz.iceberg.clients.service.entity.ClientEntity;
import kz.iceberg.clients.service.graphql.GraphQLController;
import kz.iceberg.clients.service.repository.ClientRepository;
import kz.iceberg.clients.service.wrapper.FilterWrapper;
import kz.iceberg.clients.service.wrapper.enums.Ascension;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    ClientService(final ClientRepository clientRepository) {
        this.clientRepository = clientRepository;

    }

    public boolean add(ClientEntity client) {
        this.clientRepository.save(client);
        return true;
    }

    @Transactional
    public Optional<ClientEntity> retrieve(Long id) {
        return Optional.of(clientRepository.findById(id).orElseThrow());
    }

    @Transactional
    public Optional<List<ClientEntity>> list(FilterWrapper filter) {
        String search = filter.getSearch();

        LoggerFactory.getLogger(ClientService.class).info(filter.tableNameAdapter(search));
        Sort sort = Sort.by( filter.tableNameAdapter(filter.getSort().getKey()) );

        if (filter.getSort().getValue().equals(Ascension.ASC))
            sort = sort.ascending();
        else if (filter.getSort().getValue().equals(Ascension.DESC))
            sort = sort.descending();

        Pageable pageable = PageRequest.of(
                filter.getPagination().getKey(),
                filter.getPagination().getValue(),
                sort
        );

        var list = clientRepository.findByNameOrEmails_emailOrAddresses_addressOrPhones_phone(search, search, search, search,
                pageable);
        return Optional.of(list);
    }

    @Transactional
    public void update(ClientEntity client) {
        this.clientRepository.save(client);
    }
}
