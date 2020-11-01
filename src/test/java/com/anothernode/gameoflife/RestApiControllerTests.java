package com.anothernode.gameoflife;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Set;
import com.anothernode.gameoflife.domain.Cell;
import com.anothernode.gameoflife.domain.Game;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SuppressWarnings("squid:S2699")
@WebMvcTest
class RestApiControllerTests {

  @MockBean
  private GameRepository gameRepository;

  private MockMvc mockMvc;

  private static final ObjectMapper objectMapper = new ObjectMapper();
  private static String emptySetJson;

  @BeforeAll
  static void setUp() throws Exception {
    emptySetJson = objectMapper.writeValueAsString(Set.of());
  }

  @BeforeEach
  void setUp(WebApplicationContext wac) throws Exception {
    mockMvc = MockMvcBuilders.webAppContextSetup(wac)
        .defaultRequest(get("/")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8"))
        .alwaysExpect(status().isOk())
        // .alwaysExpect(content().contentType("application/json"))
        .build();

    emptySetJson = objectMapper.writeValueAsString(Set.of());
  }

  @Test
  void postingGameWithoutCellsYieldsGameWithoutCells() throws Exception {
    mockMvc.perform(post("/games").content(emptySetJson))
        .andDo(print())
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.board.cells").isEmpty());
  }

  @Test
  void postingGameWitCellsCreatesGameWithThoseCells() throws Exception {
    var cells = Set.of(new Cell(0, 0), new Cell(2, 2));
    var cellsJson = objectMapper.writeValueAsString(cells);
    var responseJson = mockMvc.perform(post("/games").content(cellsJson))
        .andReturn().getResponse().getContentAsString();
    var game = objectMapper.readValue(responseJson, Game.class);

    assertThat(game.getBoard().getCells()).containsAll(cells);
  }

  @Test
  void gameCanBeRetrievedById() throws Exception {
    var id = "xyz";
    when(gameRepository.findById(id)).thenReturn(new Game());
    mockMvc.perform(get("/games/{id}", id))
        .andExpect(jsonPath("$.id").exists());
  }

  @Test
  void twoDistinctGamesHaveDifferntIds() throws Exception {
    var game1 = objectMapper.readValue(
        mockMvc.perform(post("/games").content(emptySetJson))
            .andReturn().getResponse().getContentAsString(),
        Game.class);

    var game2 = objectMapper.readValue(
        mockMvc.perform(post("/games").content(emptySetJson))
            .andReturn().getResponse().getContentAsString(),
        Game.class);

    assertThat(game1.getId()).isNotEqualTo(game2.getId());
  }
}
