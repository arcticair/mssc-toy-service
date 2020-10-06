package com.arcticair.mssctoyservice.services;

import com.arcticair.mssctoyservice.domain.Toy;
import com.arcticair.mssctoyservice.model.ToyDTO;
import com.arcticair.mssctoyservice.repositories.ToyRepository;
import com.arcticair.mssctoyservice.web.mapper.ToyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ToyServiceImpl implements ToyService {
    private final ToyRepository repository;
    private final ToyMapper mapper;

    @Override
    public ToyDTO getToyById(UUID id) {
        return mapper.toyToToyDto(repository.findById(id).orElseThrow());
    }

    @Override
    public ToyDTO createToy(ToyDTO toy) {
        return mapper.toyToToyDto(repository.save(mapper.toyDtoToToy(toy)));

    }

    @Override
    public ToyDTO updateToy(UUID id, ToyDTO toy) {
        Toy origin = repository.findById(id).orElseThrow();
        origin.setName(toy.getName());
        origin.setPrice(toy.getPrice());
        return mapper.toyToToyDto(repository.save(origin));
    }
}
