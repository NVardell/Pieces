package com.bits.pieces.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit Tests | Home Controller
 *
 * @author Nate Vardell
 * @since 4/22/2019
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@SpringBootTest
public class HomeControllerTest {

    @LocalServerPort
    private int port;

    @Autowired private MockMvc mockMvc;
    @Autowired private TestRestTemplate restTemplate;

    @Test
    public void routeToHome() {
    }

    /**
     * Starts server with random port & sends HTTP request & asserts response using rest template
     */
    @Test
    public void health() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/health", String.class)).contains("UP");
    }

    /**
     * Full Spring app context is started (without server) using Mock MVC
     * @throws Exception
     */
    @Test
    public void health2() throws Exception {
        this.mockMvc.perform(get("/health")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("UP")));
    }
}