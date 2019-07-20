package com.bits.pieces.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.net.URISyntaxException;

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
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class HomeControllerTest {

    @LocalServerPort private int port;

    @Autowired private MockMvc mockMvc;
    @Autowired private TestRestTemplate testRestTemplate;

    final private static String host = "http://localhost:";
    final private static String actuatorEndpoint = "/actuator";
    final private static String actuatorHealthEndpoint = "/actuator/health";

    private static URI uri;
    private static String baseUrl;
    private static String baseActuatorUrl;
    private static String baseActuatorHealthUrl;

    @Before
    public void setup() {
        baseUrl = host + port;
        baseActuatorUrl = host + port + actuatorEndpoint;
        baseActuatorHealthUrl = host + port + actuatorHealthEndpoint;
    }

    @Test
    public void routeToHome() {
        assertThat(this.testRestTemplate.getForObject(baseUrl, String.class)).contains("HOME!!!");
    }

    /**
     * Starts server with random port & sends HTTP request & asserts response using rest template
     */
    @Test
    public void healthEndpoint_RestTemplate() {
        assertThat(this.testRestTemplate.getForObject(baseActuatorHealthUrl, String.class)).contains("UP");
    }

    /**
     * Full Spring app context is started (without server) using Mock MVC
     * @throws Exception
     */
    @Test
    public void healthEndpoint_MVC() throws Exception {
        this.mockMvc.perform(get(actuatorHealthEndpoint)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("UP")));
    }


    @Test
    public void testActuatorEndpoint_StringResponseEntity() throws URISyntaxException {
        uri = new URI(baseActuatorUrl);
        ResponseEntity<String> result = this.testRestTemplate.getForEntity(uri, String.class);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(StringUtils.countOccurrencesOf(result.getBody(), host)).isEqualTo(5);
    }


    @Test
    public void testActuatorHealthEndpoint_StringResponse() throws URISyntaxException {
        uri = new URI(baseActuatorHealthUrl);
        assertThat(testRestTemplate.getForObject(valueOf(uri), String.class)).containsIgnoringCase("UP");
    }
}

