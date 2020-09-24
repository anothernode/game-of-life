package com.anothernode.gameoflife;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
}
