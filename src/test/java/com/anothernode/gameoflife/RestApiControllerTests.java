package com.anothernode.gameoflife;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class RestApiControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GameRepository gameRepository;

  @SuppressWarnings("squid:S2699")
  @Test
  public void foo() throws Exception {

    mockMvc.perform(get("/")).andExpect(status().isOk());
  }
}
