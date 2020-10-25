package com.anothernode.gameoflife;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import java.util.Set;
import com.anothernode.gameoflife.domain.Cell;
import com.anothernode.gameoflife.domain.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

/**
 * End to end tests for the RESTful API with loading of the full Spring context
 */
@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
class RestApiControllerTests {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Value("${baseUri}")
  private String baseUri;

  private String baseWithPortUri;
  private String gamesUri;

  @BeforeEach
  void setUp() {
    baseWithPortUri = String.format("%s:%s", baseUri, port);
    gamesUri = String.format("%s/games", baseWithPortUri);
  }

  @Test
  void postedGameCanBeRetrievedWithGetById() throws Exception {
    var gameReturnedFromPost = restTemplate.postForObject(gamesUri, Set.of(), Game.class);
    var uri = String.format("%s/%s", gamesUri, gameReturnedFromPost.getId());
    var gameReturnedFromGet = restTemplate.getForObject(uri, Game.class);

    assertThat(gameReturnedFromPost.getId()).isEqualTo(gameReturnedFromGet.getId());
  }

  @Test
  void twoDistinctGamesHaveDifferentIds() throws Exception {
    var game1 = restTemplate.postForObject(gamesUri, new Game(), Game.class);
    var game2 = restTemplate.postForObject(gamesUri, new Game(), Game.class);

    assertThat(game1.getId()).isNotEqualTo(game2.getId());
  }

  @Test
  void postingGameWithoutCellsCreatesGameWithoutCells() throws Exception {
    var responseEntity = restTemplate.postForEntity(gamesUri, Set.of(), Game.class);

    // Even if the server returns an HTTP error, the RestTemplate will still create a Game
    // instance with zero cells, so the HTTP return code is verified explicitly here.
    assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
    assertThat(responseEntity.getBody().cells()).isEmpty();
  }

  @Test
  void postingGameWithCellsCreatesGameWithThoseCells() throws Exception {
    Set<Cell> cells = Set.of(new Cell(0, 0), new Cell(2, 2));
    var game = restTemplate.postForObject(gamesUri, cells, Game.class);

    assertThat(game.cells()).isEqualTo(cells);
  }

  @Test
  void postingRoundToGameWithZeroCellsYieldsNextRoundWithZeroCellsOnBoard() throws Exception {
    var game = restTemplate.postForObject(gamesUri, Set.of(), Game.class);
    var roundsUri = String.format("%s/%s/rounds", gamesUri, game.getId());
    var resultEntity = restTemplate.postForEntity(roundsUri, null, Game.class);

    assertThat(resultEntity.getStatusCode().is2xxSuccessful()).as("HTTP status code is 2xx")
        .isTrue();
  }
}
