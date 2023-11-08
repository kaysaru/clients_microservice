package kz.iceberg.clients.service;

import jakarta.transaction.Transactional;
import kz.iceberg.clients.service.entity.ClientEntity;
import kz.iceberg.clients.service.repository.ClientRepository;
import kz.iceberg.clients.service.wrapper.FilterWrapper;
import kz.iceberg.clients.service.wrapper.enums.Ascension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        ClientEntity entity = ClientEntity.builder()
                .name(filter.getSearch())
                .build();
        String search = filter.getSearch();

        Sort sort = Sort.by(filter.tableNameAdapter(filter.getSort().getKey()));
        if (filter.getSort().getValue().equals(Ascension.ASC))
            sort = sort.ascending();
        else if (filter.getSort().getValue().equals(Ascension.DESC))
            sort = sort.descending();

        Pageable pageable = PageRequest.of(
                filter.getPagination().getKey(),
                filter.getPagination().getValue(),
                sort
        );



        var list = clientRepository.findByNameOrEmails_emailOrAddresses_AddressOrPhones_Phone(search, search, search, search,
                pageable);
        return Optional.of(list);
    }

    @Transactional
    public void update(ClientEntity client) {
        this.clientRepository.save(client);
    }
}
