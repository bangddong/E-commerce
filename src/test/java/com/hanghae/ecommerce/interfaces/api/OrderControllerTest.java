package com.hanghae.ecommerce.interfaces.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void createOrder() throws Exception {
        // Given

        // When & Then
        mvc.perform(
                post("/order")
                        .content("{\"userId\":\"1\", \"productId\":1, \"quantity\":2}")
                        .contentType("application/json")
        )
                .andExpect(status().isOk());
    }
}