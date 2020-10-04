package com.arcticair.mssctoyservice.web.controller;

import com.arcticair.mssctoyservice.model.CustomerDTO;
import com.arcticair.mssctoyservice.model.ToyDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/customers/")
@RestController
public class CustomerController {

    @GetMapping("{userId}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("userId") UUID userId) {
        return new ResponseEntity<>(CustomerDTO.builder().id(UUID.randomUUID()).name("Naomi").build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveToy(@RequestBody CustomerDTO customerDTO) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/customers/" + UUID.randomUUID());
        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity updateToy(@PathVariable("id") UUID id, @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteToy(@PathVariable("id") UUID toyId) {
        return;
    }
}
