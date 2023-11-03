package kz.iceberg.clients.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.iceberg.clients.service.entity.ClientEntity;
import kz.iceberg.clients.service.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientsController {
    @Autowired
    ClientRepository clientRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @GetMapping(path = "/client")
    public String get() {
        List<ClientEntity> clients  = clientRepository.findAllById(List.of(36L, 37L));
        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString(clients.get(1));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }
}
