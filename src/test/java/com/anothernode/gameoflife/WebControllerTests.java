package com.anothernode.gameoflife;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class WebControllerTests {

  @LocalServerPort
  private int port;

  @Test
  void serverDeliversHtmlDocument() throws Exception {

    Document doc = Jsoup.connect("http://localhost:" + port + "/").get();

    assertThat(doc.documentType().name()).isEqualTo("html");
  }
}
