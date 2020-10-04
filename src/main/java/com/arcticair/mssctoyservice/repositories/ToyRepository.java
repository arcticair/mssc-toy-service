package com.arcticair.mssctoyservice.repositories;

import com.arcticair.mssctoyservice.domain.Toy;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ToyRepository extends PagingAndSortingRepository<Toy, UUID> {
}
