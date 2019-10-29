package com.staxter.registration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private final String jsonBody = "{" +
            "\"firstName\": \"Kenneth\"," +
            "\"lastName\": \"Reyes\"," +
            "\"userName\": \"kenbem\"," +
            "\"plainTextPassword\": \"123456789\"" +
            "}";

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void whenAddNewUser_thenCreatedResponse() throws Exception{

        mockMvc
                .perform(
                        post("/userservice/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonBody)
                            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Kenneth"))
                .andExpect(jsonPath("$.lastName").value("Reyes"))
                .andExpect(jsonPath("$.userName").value("kenbem"));

    }

    @Test
    public void whenAddNewUser_ButUserExist_thenConflictResponse() throws Exception{

        mockMvc
                .perform(
                        post("/userservice/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonBody)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc
                .perform(
                        post("/userservice/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonBody)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());


    }

}
