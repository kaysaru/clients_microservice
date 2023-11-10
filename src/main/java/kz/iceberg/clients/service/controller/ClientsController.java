package kz.iceberg.clients.service.controller;

import kz.iceberg.clients.service.service.ClientService;
import kz.iceberg.clients.service.entity.ClientEntity;
import kz.iceberg.clients.service.entity.dto.ClientEntityDto;
import kz.iceberg.clients.service.wrapper.FilterWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("clients")
public class ClientsController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    ClientService clientService;

    ClientsController(ClientService service) {
        this.clientService = service;
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ClientEntity get(@PathVariable("id") Long id) {
        return clientService.retrieve(id).orElseThrow();
    }

    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<Optional<List<ClientEntity>>> getList(@RequestBody FilterWrapper filter) {
        return new ResponseEntity<>(clientService.list(filter), HttpStatus.OK);
    }

    @PostMapping(path = "/new", produces = "application/json")
    public ResponseEntity<String> create(@RequestBody ClientEntity client) {
        var res = this.clientService.add(client);
        if(res.isEmpty()) {
            return new ResponseEntity<>("An Error Occured.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<String> update(@RequestBody ClientEntityDto entity) {
        if(entity.getId() == null) {
            throw new IllegalStateException("id is null");
        }
        clientService.update(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<String> toggle(@PathVariable Long id) {
        clientService.delete(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
}
