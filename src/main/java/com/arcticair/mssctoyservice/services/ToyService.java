package com.arcticair.mssctoyservice.services;

import com.arcticair.mssctoyservice.model.ToyDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface ToyService {
    ToyDTO getToyById(UUID id);

    ToyDTO createToy(ToyDTO toy);

    ToyDTO updateToy(UUID id, ToyDTO toy);
}
