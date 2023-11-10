package kz.iceberg.clients.service.controller;

import kz.iceberg.clients.service.wrapper.MergeRequestWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import kz.iceberg.clients.service.service.MergeService;

import java.util.List;

@RestController("/merge")
public class MergeController {
    private final MergeService service;

    MergeController(final MergeService service) {
        this.service = service;
    }
    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<String> merge(@RequestBody MergeRequestWrapper wrapper) {
        if(this.service.fetchAndMerge(wrapper.getPrimary(), wrapper.getSecondaries())) {
            return new ResponseEntity<>("merged ids " + wrapper.getSecondaries().toString() + " with id " + wrapper.getPrimary(), HttpStatus.OK);
        }
        return new ResponseEntity<>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
