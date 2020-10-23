package com.anothernode.gameoflife;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebHtmlController {

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @PostMapping("/games")
  public String createGame() {
    return "index";
  }

}
