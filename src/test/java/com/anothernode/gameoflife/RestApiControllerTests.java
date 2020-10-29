package com.anothernode.gameoflife;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.Set;
import com.fasterxml.jackson.databind.ObjectMapper;
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

  private MockMvc mockMvc;

  @MockBean
  private GameRepository gameRepository;

  @BeforeEach
  void setUp(WebApplicationContext wac) {
    mockMvc = MockMvcBuilders.webAppContextSetup(wac)
        .defaultRequest(get("/")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
        .alwaysExpect(status().isOk()).alwaysExpect(content().contentType("application/json"))
        .build();
  }

  @Test
  void twoDistinctGamesHaveDifferentIds() throws Exception {
    var emptySetJson = new ObjectMapper().writeValueAsString(Set.of());
    mockMvc.perform(post("/games").content(emptySetJson))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.board.cells").isEmpty());
  }
}
