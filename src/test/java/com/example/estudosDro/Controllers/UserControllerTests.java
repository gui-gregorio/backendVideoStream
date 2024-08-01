package com.example.estudosDro.Controllers;

import com.example.estudosDro.Entities.UserEntity;
import com.example.estudosDro.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

public class UserControllerTests extends ControllersBaseTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;

    @Test
    public void getUsersTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void registerUserTests() throws Exception{
        String uniqueUsername = "newUserTest" + UUID.randomUUID().toString();
        String uniqueEmail = "emailTest" + UUID.randomUUID().toString() + "@gmail.com";
        String newUserJson = String.format("{\"email\": \"%s\", \"username\": \"%s\", " +
                        "\"password\": \"1234578\", \"name\": \"tester\", \"role\": \"paidUser\", \"hasPaid\": true}",
                uniqueEmail, uniqueUsername);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(newUserJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("User registered with success"));
        userRepository.findByUsername(uniqueUsername).ifPresent(user -> userRepository.delete(user));
    }
    @Test
    public void registerSameUsernameTests() throws Exception{
        String existingUsernameJson = "{\"email\": \"gui@gmail.com\", \"username\": \"gui\", " +
                "\"password\": \"1234578\", \"name\": \"tester\", \"role\": \"paidUser\", \"hasPaid\": true}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(existingUsernameJson))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }


    @Test
    public void registerSameEmailTests() throws Exception{
        String existingEmailJson = "{\"email\": \"gui@gmail.com\", \"username\": \"testeEmailAqui\", " +
                "\"password\": \"1234578\", \"name\": \"tester\", \"role\": \"paidUser\", \"hasPaid\": true}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(existingEmailJson))
                .andExpect(MockMvcResultMatchers.status().isConflict());

    }
}
