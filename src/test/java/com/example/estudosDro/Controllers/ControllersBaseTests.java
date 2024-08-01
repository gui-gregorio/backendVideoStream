package com.example.estudosDro.Controllers;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class ControllersBaseTests {
    @Autowired
    protected MockMvc mockMvc;
    protected String token;

    @BeforeEach
    public void setUp() throws Exception {
        String validLoginJson = "{\"username\": \"gui\", \"password\": \"gui1998\"}";

        String response = mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validLoginJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        token = new JSONObject(response).getString("token");
    }


}
