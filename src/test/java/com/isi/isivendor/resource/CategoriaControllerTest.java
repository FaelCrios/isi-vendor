package com.isi.isivendor.resource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.xml.crypto.dsig.XMLValidateContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class CategoriaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Deveria devolver código HTTP - 200 quando informações estão válidas ")
    void criar_categoria1() throws Exception {
        var response = mvc
                .perform(post("/categoria"))
                .andReturn().getResponse();



    }
    @Test
    void getAllCategoria() {
    }

    @Test
    void getCategoriaById() {
    }



    @Test
    void deleteCategoria() {
    }

    @Test
    void updateCategoria() {
    }
}