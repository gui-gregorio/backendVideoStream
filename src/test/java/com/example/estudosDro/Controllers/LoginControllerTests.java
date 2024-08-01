package com.example.estudosDro.Controllers;


import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTests extends ControllersBaseTests {

    @Test
    public void authenticationUserTest() throws Exception{
        String invalidLoginJson = "{\"username\": \"abasdaaf\", \"password\": \"23214\"}";
        //testa apenas o invalido porque o valido já está sendo testado para pegar o token
          mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidLoginJson))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().string("Invalid Login or Password"));

    }
}
