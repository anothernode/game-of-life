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
class WebControllerTests {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  private String baseUri;
  private String gamesUri;

  @BeforeEach
  void setUp() {
    baseUri = "http://localhost:" + port;
    gamesUri = baseUri + "/games";
  }

  // HTML based tests

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
    Document doc = Jsoup.connect(gamesUri).post();
    Element grid = doc.getElementById("board");

    assertThat(grid.tagName()).as("element with id='gridBoard'").isEqualTo("div");
    assertThat(grid.children()).hasSizeGreaterThanOrEqualTo(9);
  }

  // JSON based tests

  @Test
  void postingGameWithoutCellsCreatesGameWithoutCells() throws Exception {
    var responseEntity = restTemplate.postForEntity(gamesUri, Set.of(), Game.class);

    assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
    assertThat(responseEntity.getBody().cells()).isEmpty();
  }

  @Test
  void postingGameWithCellsCreatesGameWithThoseCells() throws Exception {
    Set<Cell> cells = Set.of(new Cell(0, 0), new Cell(2, 2));
    Game game = restTemplate.postForObject(gamesUri, cells, Game.class);

    assertThat(game.cells()).isEqualTo(cells);
  }

  @Test
  void postedGameCanBeRetrievedWithGetById() throws Exception {
    var gameReturnedFromPost = restTemplate.postForObject(gamesUri, Set.of(), Game.class);
    var gameReturnedFromGet =
        restTemplate.getForObject(gamesUri + "/" + gameReturnedFromPost.getId(), Game.class);

    assertThat(gameReturnedFromPost.getId()).isEqualTo(gameReturnedFromGet.getId());
  }

  @Test
  void twoDistinctGamesHaveDifferentIds() throws Exception {
    var game1 = restTemplate.postForObject(gamesUri, new Game(), Game.class);
    var game2 = restTemplate.postForObject(gamesUri, new Game(), Game.class);

    assertThat(game1.getId()).isNotEqualTo(game2.getId());
  }
}
