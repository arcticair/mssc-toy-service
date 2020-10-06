package com.arcticair.mssctoyservice.web.controller;

import com.arcticair.mssctoyservice.model.ToyDTO;
import com.arcticair.mssctoyservice.services.ToyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/toys/")
public class ToyController {

    private final ToyService toyService;

    @GetMapping("{id}")
    public ResponseEntity<ToyDTO> getToyDTO(@PathVariable("id") UUID id) {
        System.out.println("+++++++++++++++++++++ GET TOY.....");
        return new ResponseEntity<>(toyService.getToyById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveToy(@RequestBody @Validated ToyDTO toy) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/toys/" + UUID.randomUUID());
        return new ResponseEntity(toyService.createToy(toy), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity updateToy(@PathVariable("id") UUID id, @RequestBody @Validated ToyDTO toy) {
        return new ResponseEntity(toyService.updateToy(id, toy), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{toyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteToy(@PathVariable UUID toyId) {
        return;
    }
}
