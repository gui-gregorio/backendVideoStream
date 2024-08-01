package com.example.estudosDro.Controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class UserControllerTests extends ControllersBaseTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getUsersTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
