package com.arcticair.mssctoyservice.web.controller;

import com.arcticair.mssctoyservice.model.ToyDTO;
import com.arcticair.mssctoyservice.model.ToyStyleEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ToyController.class)
class ToyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getToyDTO() throws Exception {
        mockMvc.perform(get("/api/v1/toys/"+ UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void saveToy() throws Exception {
        ToyDTO toyDTO = getValidToyDTO();
        String jsonRepr = objectMapper.writeValueAsString(toyDTO);

        mockMvc.perform(post("/api/v1/toys/").contentType(MediaType.APPLICATION_JSON).content(jsonRepr)).andExpect(status().isCreated());
    }

    @Test
    void updateToy()  throws Exception {
        ToyDTO toyDTO = getValidToyDTO();
        String jsonRepr = objectMapper.writeValueAsString(toyDTO);

        mockMvc.perform(put("/api/v1/toys/"+ UUID.randomUUID().toString()).contentType(MediaType.APPLICATION_JSON).content(jsonRepr)).andExpect(status().isNoContent());

    }

    ToyDTO getValidToyDTO() {
        return ToyDTO.builder()
                .name("Arara")
                .style(ToyStyleEnum.BABY)
                .price(new BigDecimal("11.11"))
                .upc(222L)
                .build();
    }
}