package com.anothernode.gameoflife;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class WebControllerHtmlTests {

  @LocalServerPort
  private int port;

  @Value("${baseUri}")
  private String baseUri;

  private String indexUri;
  private String gamesUri;

  @BeforeEach
  void setUp() {
    indexUri = String.format("%s:%s", baseUri, port);
    gamesUri = String.format("%s:%s/games", baseUri, port);
  }

  @Test
  void deliversHtmlDocumentAtBaseUri() throws Exception {
    Document doc = Jsoup.connect(indexUri).get();

    assertThat(doc.documentType().name()).isEqualTo("html");
  }

  @Test
  void deliversBoardAsDivGridWithAtLeastNineItemsAtBaseUri() throws Exception {
    Document doc = Jsoup.connect(indexUri).get();
    Element grid = doc.getElementById("board");

    assertThat(grid.tagName()).as("element with id='gridBoard'").isEqualTo("div");
    assertThat(grid.children()).hasSizeGreaterThanOrEqualTo(9);
  }

  @Test
  void gameIsCreatedWhenPostingToGamesCollection() throws Exception {
    Document doc = Jsoup.connect(gamesUri).post();
    Element grid = doc.getElementById("board");

    assertThat(grid.tagName()).as("element with id='gridBoard'").isEqualTo("div");
    assertThat(grid.children()).hasSizeGreaterThanOrEqualTo(9);
  }

}
