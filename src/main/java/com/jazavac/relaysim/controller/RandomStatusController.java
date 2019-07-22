package com.jazavac.relaysim.controller;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** This class is used to simulate getting a status of a relay that turns on and off every second */
@RestController
public class RandomStatusController {

  @GetMapping("/randomstatus")
  public String returnStatus(HttpServletResponse response) {
    response.addHeader("Access-Control-Allow-Origin", "*");
    response.addHeader("Content-Type", "application/json");
    return String.format("{\"relayIsOn\":%b}", generateStatus());
  }

  private boolean generateStatus() {
    return (System.currentTimeMillis() / 1000) % 2 == 0;
  }
}
