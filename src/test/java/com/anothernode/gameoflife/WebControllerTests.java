package com.anothernode.gameoflife;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class WebControllerTests {

  @Autowired
  private TestRestTemplate restTemplate;

  @LocalServerPort
  private int port;

  @Test
  void serverDeliversResponse() {

    String response = restTemplate.getForObject("http://localhost:" + port + "/foo", String.class);

    assertThat(response).isEqualTo("foo");
  }

  @Test
  void serverDeliversHtmlForIndex() {

    String response = restTemplate.getForObject("http://localhost:" + port + "/", String.class);

    assertThat(response).contains("hello");
  }
}
