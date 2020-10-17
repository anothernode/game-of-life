package com.anothernode.gameoflife;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

  @Autowired
  private GameRepository gameRepository;

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
    gameRepository.save(game);
    return game;
  }

  @GetMapping(value = "/games/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody Game getGameJson(@PathVariable String id) {
    return gameRepository.findById(id);
  }
}
