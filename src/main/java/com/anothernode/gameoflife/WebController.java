package com.anothernode.gameoflife;

import java.util.Set;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

  @GetMapping("/foo")
  public @ResponseBody String home() {
    return "foo";
  }

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @PostMapping("/games")
  public String createGame() {
    return "index";
  }

  @PostMapping(value = "/games", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody Game createGameJson(@RequestBody Game game) {
    return new Game(game.cells());
  }
}
