package com.arcticair.mssctoyservice.web.mapper;

import com.arcticair.mssctoyservice.domain.Toy;
import com.arcticair.mssctoyservice.model.ToyDTO;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface ToyMapper {
    Toy toyDtoToToy(ToyDTO toyDTO);
    ToyDTO toyToToyDto(Toy toy);
}
