package kz.iceberg.clients.service;

import jakarta.transaction.Transactional;
import kz.iceberg.clients.service.entity.ClientEntity;
import kz.iceberg.clients.service.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public List<ClientEntity> list() {
        return clientRepository.findAll();
    }
}
