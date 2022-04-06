package com.nashtech.ecommerce.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashtech.ecommerce.domain.Account;
import com.nashtech.ecommerce.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(classes = {AccountController.class})
@AutoConfigureMockMvc
public class AccountControllerTest {
    @Autowired
    private AuthService authService ;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MockMvc accountMockMvc;

    private String convertObjectToJSONString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    };

    @Test
    @Transactional
    void addAccountTest() throws Exception {
        Account newAccount = new Account();
        newAccount.setUsername("");
        newAccount.setPassword("Aa1234567890");
        accountMockMvc.perform(post("/api/account/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJSONString(newAccount)))
            .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }
}
