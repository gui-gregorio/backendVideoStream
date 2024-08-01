package com.example.estudosDro.Controllers;

import org.junit.jupiter.api.Test;
import com.example.estudosDro.Controllers.HelloWorldController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloWorldControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void HelloWorldTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hello World!"));
    }
}
