package org.tourGo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TestController {

  @GetMapping("/views/plan/ex")
  public String index() {
    return "views/plan/ex2";
  }
  
  @GetMapping("/ex")
  public String index1() {
    return "ex1";
  }
}