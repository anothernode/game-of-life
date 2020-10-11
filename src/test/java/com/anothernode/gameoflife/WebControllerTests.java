package com.anothernode.gameoflife;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class WebControllerTests {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  private String baseUri;

  @BeforeEach
  void setUp() {
    baseUri = "http://localhost:" + port;
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
  void gameIsCreatedWhenPostingToGamesCollection() throws Exception {
    Document doc = Jsoup.connect(baseUri + "/games").post();
    Element grid = doc.getElementById("board");

    assertThat(grid.tagName()).as("element with id='gridBoard'").isEqualTo("div");
    assertThat(grid.children()).hasSizeGreaterThanOrEqualTo(9);
  }

  @Test
  void postingGameWithoutCellsCreatesGameWithoutCells() throws Exception {
    Game game = restTemplate.postForObject(baseUri + "/games", new Game(Set.of()), Game.class);

    assertThat(game.cells()).isEmpty();
  }

  @Test
  void postingGameWithCellsCreatesGameWithThoseCells() throws Exception {
    Set<Cell> cells = Set.of(new Cell(0, 0), new Cell(2, 2));
    Game game = restTemplate.postForObject(baseUri + "/games", new Game(cells), Game.class);

    assertThat(game.cells()).containsAll(cells);
  }
}
