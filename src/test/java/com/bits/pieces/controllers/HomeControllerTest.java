package com.bits.pieces.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.valueOf;
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
//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@SpringBootTest
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class HomeControllerTest {

    @LocalServerPort private int port;

    @Autowired private MockMvc mockMvc;
    @Autowired private TestRestTemplate testRestTemplate;

    private static String baseUrl;

    @Before
    public void setup() {
        baseUrl = "http://localhost:" + port;
    }

    @Test
    public void routeToHome() {
    }

    /**
     * Starts server with random port & sends HTTP request & asserts response using rest template
     */
    @Test
    public void healthEndpoint_RestTemplate() {
        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/actuator/health", String.class)).contains("UP");
    }

    /**
     * Full Spring app context is started (without server) using Mock MVC
     * @throws Exception
     */
    @Test
    public void healthEndpoint_MVC() throws Exception {
        this.mockMvc.perform(get("/actuator/health")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("UP")));
    }

    @Test
    public void testAddEmployeeSuccess() throws URISyntaxException {
        final String baseUrl = "http://localhost:"+port+"/actuator/health";
        URI uri = new URI(baseUrl);

        String result = this.testRestTemplate.getForObject(valueOf(uri), String.class);

        System.out.println(result);
    }

    @Test
    public void testActuatorEndpoint() throws URISyntaxException {
//        final String baseUrl = "http://localhost:"+port+"/actuator";
        URI uri = new URI(baseUrl + "/actuator");

        ResponseEntity<String> result = this.testRestTemplate.getForEntity(uri, String.class);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(StringUtils.countOccurrencesOf(result.getBody(), "localhost")).isEqualTo(5);
    }
}

