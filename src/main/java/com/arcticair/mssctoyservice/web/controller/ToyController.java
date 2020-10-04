package com.arcticair.mssctoyservice.web.controller;

import com.arcticair.mssctoyservice.model.ToyDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/toys/")
public class ToyController {

    @GetMapping("{id}")
    public ResponseEntity<ToyDTO> getToyDTO(@PathVariable("id") UUID id) {
        System.out.println("+++++++++++++++++++++ GET TOY.....");
        return new ResponseEntity<>(ToyDTO.builder().name("Piko").id(UUID.randomUUID()).build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveToy(@RequestBody ToyDTO toy) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/toys/" + UUID.randomUUID());

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity updateToy(@PathVariable("id") UUID id, @RequestBody ToyDTO toy) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{toyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteToy(@PathVariable UUID toyId) {
        return;
    }
}
