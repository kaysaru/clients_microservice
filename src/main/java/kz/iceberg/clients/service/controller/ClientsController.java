package kz.iceberg.clients.service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.iceberg.clients.service.ClientService;
import kz.iceberg.clients.service.entity.ClientEntity;
import kz.iceberg.clients.service.wrapper.FilterWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientsController {
    @Autowired
    ClientService clientService;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
//    @GetMapping(path = "/{id}", produces = "application/json")
//    public String get() {
//        List<ClientEntity> clients  = clientService.findAllById(List.of(36L, 37L));
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json;
//        try {
//            json = objectMapper.writeValueAsString(clients.get(1));
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        return json;
//    }

    @PostMapping(path = "/new", produces = "application/json")
    public ResponseEntity<List<ClientEntity>> getList(@RequestBody FilterWrapper filter) {
        return new ResponseEntity<>(clientService.list(filter).orElse(List.of()), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<String> update(@PathVariable String id) {
        return null;
    }

    @PatchMapping(path = "/{id}", produces = "application/json")
    public String toggle(@PathVariable String id) {
        return null;
    }
}
