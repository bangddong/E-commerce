package com.hanghae.ecommerce.interfaces.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getUserBalance() throws Exception {
        // Given

        // When & Then
        mvc.perform(
                get("/users/1/balance")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("1"))
                .andExpect(jsonPath("$.balance").value(10000));
    }

    @Test
    void chargeUserBalance() throws Exception{
        // Given

        // When & Then
        mvc.perform(
                post("/users/1/balance")
                        .content("{\"userId\":\"1\", \"amount\":1000}")
                        .contentType("application/json")
        )
                .andExpect(status().isOk());
    }
}