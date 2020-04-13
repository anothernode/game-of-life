package com.anothernode.gameoflife;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleWebController {

  @GetMapping("/")
  public @ResponseBody String home() {
    return "foo";
  }
}
