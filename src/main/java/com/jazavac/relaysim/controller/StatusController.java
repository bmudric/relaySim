package com.jazavac.relaysim.controller;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/status")
public class StatusController {

  @GetMapping
  public String returnStatus(HttpServletResponse response){
    response.addHeader("Access-Control-Allow-Origin", "*");
    return String.format("{\"relayIsOn\":\"%s\"}", generateStatus());
  }

  private boolean generateStatus() {
    return (System.currentTimeMillis() / 1000) % 2 == 0;
  }
}
