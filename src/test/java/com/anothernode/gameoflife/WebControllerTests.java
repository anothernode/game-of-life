package com.anothernode.gameoflife;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class WebControllerTests {

  @LocalServerPort
  private int port;

  private String baseUri;

  @BeforeEach
  void setUp() {
    baseUri = "http://localhost:" + port + "/";
  }

  @Test
  void deliversHtmlDocumentAtBaseUri() throws Exception {
    Document doc = Jsoup.connect(baseUri).get();

    assertThat(doc.documentType().name()).isEqualTo("html");
  }

  @Test
  void deliversBoardAsDivGridWithAtLeastNineItemsAtBaseUri() throws Exception {
    Document doc = Jsoup.connect(baseUri).get();
    Element grid = doc.getElementById("board");

    assertThat(grid.tagName()).as("element with id='gridBoard'").isEqualTo("div");
    assertThat(grid.children()).hasSizeGreaterThanOrEqualTo(9);
  }


@Test
void deliversBoardAsDivGridWithAtLeastThirtySixItemsAtBaseUri() throws Exception {
  Document doc = Jsoup.connect(baseUri).get();
  Element grid = doc.getElementById("board");

  assertThat(grid.tagName()).as("element with id='gridBoard'").isEqualTo("div");
  assertThat(grid.children()).hasSizeGreaterThanOrEqualTo(36);
}
}