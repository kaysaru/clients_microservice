package kz.iceberg.clients.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import kz.iceberg.clients.service.entity.ClientEntity;
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

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    ClientService(final ClientRepository clientRepository) {
        this.clientRepository = clientRepository;

    }

    public Optional<ClientEntity> add(ClientEntity client) {
        return Optional.of(this.clientRepository.save(client));
    }

    @Transactional
    public Optional<ClientEntity> retrieve(Long id) {
        return Optional.of(clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found")));
    }

    @Transactional
    public Optional<List<ClientEntity>> list(FilterWrapper filter) {
        String search = filter.getSearch();

        LoggerFactory.getLogger(ClientService.class).info(filter.tableNameAdapter(search.trim().toLowerCase()));
        Sort sort = Sort.by(filter.tableNameAdapter(filter.getSort().getColumnName()));

        if (filter.getSort().getOrder().equals(Ascension.ASC))
            sort = sort.ascending();
        else if (filter.getSort().getOrder().equals(Ascension.DESC))
            sort = sort.descending();

        Pageable pageable = PageRequest.of(
                filter.getPagination().getOffset(),
                filter.getPagination().getLimit(),
                sort
        );

        return Optional.ofNullable(clientRepository.findByNameContainingIgnoreCaseOrEmailsEmailContainingIgnoreCaseOrAddressesAddressContainingIgnoreCaseOrPhonesPhoneContainingIgnoreCase(search, search, search, search, pageable));
    }

    @Transactional
    public ClientEntity update(ClientEntity client) {
        try {
            throw new NoSuchMethodException();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        //TODO implement update
        // return this.clientRepository.save(client);
    }

    @Transactional
    public boolean delete(Long id) {
        this.clientRepository.deleteById(id);
        return true;
    }
}
