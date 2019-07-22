package com.jazavac.relaysim.controller;

import java.util.concurrent.atomic.AtomicBoolean;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is used to simulate turning a relay on and off, as well as getting the current state
 */
@RestController
public class RelayApiController {

  private AtomicBoolean relayIsOn = new AtomicBoolean(false);

  @GetMapping("/status")
  public String returnStatus(HttpServletResponse response) {
    response.addHeader("Access-Control-Allow-Origin", "*");
    response.addHeader("Content-Type", "application/json");
    return String.format("{\"relayIsOn\":%b}", relayIsOn.get());
  }

  @PostMapping("/on")
  public String handleOn(HttpServletResponse response) {
    relayIsOn.set(true);
    return returnStatus(response);
  }

  @PostMapping("/off")
  public String handleOff(HttpServletResponse response) {
    relayIsOn.set(false);
    return returnStatus(response);
  }
}
