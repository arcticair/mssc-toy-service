package com.arcticair.mssctoyservice.web.controller;

import com.arcticair.mssctoyservice.domain.Toy;
import com.arcticair.mssctoyservice.model.ToyDTO;
import com.arcticair.mssctoyservice.model.ToyStyleEnum;
import com.arcticair.mssctoyservice.repositories.ToyRepository;
import com.arcticair.mssctoyservice.services.ToyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "tututu", uriPort = 80)
@SpringBootTest
@AutoConfigureMockMvc
class ToyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Mock
    ToyRepository repository;

    @MockBean
    ToyService service;

    @BeforeEach
    void setUp() {
        //given(repository.findById(any(UUID.class))).willReturn(getValidToy());
           given(service.getToyById(any(UUID.class))).willReturn(getValidToyDTO());
           given(service.updateToy(any(), any())).willReturn(getValidToyDTO());

    }

    @Test
    void getToyDTO() throws Exception {
       // given(service.getToyById(any(UUID.class))).willReturn(getValidToyDTO());

        mockMvc.perform(get("/api/v1/toys/{toyId}", UUID.randomUUID().toString())
                .param("iis", "yes")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("v1/toys-get", pathParameters(
                        parameterWithName("toyId").description("UUID of toy record")
                        ),
                        requestParameters(
                                parameterWithName("iis").description("Fake parameter it is ignored")
                        ),
                        responseFields(
                                fieldWithPath("id").description("id of toy"),
                                fieldWithPath("version").description("version of toy"),
                                fieldWithPath("createdDate").description("createdDate of toy"),
                                fieldWithPath("lastModifiedDate").description("lastModifiedDate of toy"),
                                fieldWithPath("name").description("name of toy"),
                                fieldWithPath("style").description("style of toy"),
                                fieldWithPath("upc").description("upc of toy"),
                                fieldWithPath("price").description("price of toy"),
                                fieldWithPath("quantity").description("quantity of toy")
                        )
                ));
    }

    @Test
    void saveToy() throws Exception {
        ToyDTO toyDTO = getValidToyDTO();
        String jsonRepr = objectMapper.writeValueAsString(toyDTO);

        ConstraintDescriptions fields = new ConstraintDescriptions(ToyDTO.class);

        mockMvc.perform(post("/api/v1/toys/")
                .contentType(MediaType.APPLICATION_JSON).content(jsonRepr))
                .andExpect(status().isCreated())
                .andDo(document("v1/toys-post",
                        requestFields(
                                fieldWithPath("id").ignored(),
                                fieldWithPath("name").description("name"),
                                fieldWithPath("version").ignored(),
                                fieldWithPath("createdDate").ignored(),
                                fieldWithPath("lastModifiedDate").ignored(),
                                fieldWithPath("style").description("style of toy"),
                                fieldWithPath("upc").description("upc of toy"),
                                fieldWithPath("price").description("price of toy"),
                                fieldWithPath("quantity").description("quantity of toy")

                        ))
                )
        ;
    }

    @Test
    void updateToy() throws Exception {
       // given(service.updateToy(any(), any())).willReturn(getValidToyDTO());

        ToyDTO toyDTO = getValidToyDTO();
        String jsonRepr = objectMapper.writeValueAsString(toyDTO);

        mockMvc.perform(put("/api/v1/toys/" + UUID.randomUUID().toString()).contentType(MediaType.APPLICATION_JSON).content(jsonRepr)).andExpect(status().isNoContent());

    }

    ToyDTO getValidToyDTO() {
        return ToyDTO.builder()
                .name("Arara")
                .style(ToyStyleEnum.BABY)
                .price(new BigDecimal("11.11"))
                .upc("0000077777")
                .build();
    }

    Toy getValidToy() {
       return Toy.builder().name("ddd").style("rvrv").upc("ooo").build();
    }


}