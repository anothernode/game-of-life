package com.anothernode.gameoflife;

import java.util.Set;
import com.anothernode.gameoflife.domain.Cell;
import com.anothernode.gameoflife.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RestApiController {

  @Autowired
  private GameRepository gameRepository;

  @PostMapping(value = "/games")
  public Game createGameJson(@RequestBody Set<Cell> cells) {
    var game = new Game(cells);
    gameRepository.save(game);
    return game;
  }

  @GetMapping(value = "/games/{id}")
  public Game getGameJson(@PathVariable String id) {
    return gameRepository.findById(id);
  }

  @PostMapping(value = "/games/{id}/rounds")
  public Game nextRound(@PathVariable String id) {
    return null;
  }
}
