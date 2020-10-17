package com.anothernode.gameoflife;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepository {

  private Map<String, Game> store = new HashMap<>();

  public void save(Game game) {
    store.put(game.getId(), game);
  }

  public Game findById(String id) {
    return store.get(id);
  }
}
