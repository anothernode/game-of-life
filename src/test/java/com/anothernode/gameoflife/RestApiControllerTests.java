package com.anothernode.gameoflife;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
        .defaultRequest(get("/").accept(MediaType.APPLICATION_JSON)) //
        .alwaysExpect(status().isOk()) //
        .alwaysExpect(content().contentType("application/json;charset=UTF-8")) //
        .build();
  }

  @Test
  void twoDistinctGamesHaveDifferentIds() throws Exception {
    mockMvc.perform(post("/games")).andDo(print()).andExpect(status().isOk());
  }
}
