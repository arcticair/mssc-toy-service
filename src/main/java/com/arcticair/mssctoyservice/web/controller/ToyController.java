package com.arcticair.mssctoyservice.web.controller;

import com.arcticair.mssctoyservice.model.ToyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/toys")
public class ToyController {

    @GetMapping("/{id}")
    public ResponseEntity<ToyDTO> getToyDTO(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(ToyDTO.builder().build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveToy(@RequestBody ToyDTO toy) {
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateToy(@PathVariable("id") UUID id, @RequestBody ToyDTO toy) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
