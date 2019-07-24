package com.jazavac.relaysim.controller;

import java.util.concurrent.atomic.AtomicBoolean;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is used to simulate turning a relay on and off, as well as getting the current state
 */
@RestController
@Slf4j
public class RelayApiController {

  private AtomicBoolean relayIsOn = new AtomicBoolean(false);

  @GetMapping("/status")
  public String returnStatus(HttpServletResponse response) {
    log.debug("Client requesting GET /status");
    response.addHeader("Access-Control-Allow-Origin", "*");
    response.addHeader("Content-Type", "application/json");
    return String.format("{\"relayIsOn\":%b}", relayIsOn.get());
  }

  @PostMapping("/on")
  public String handleOn(HttpServletResponse response) {
    log.debug("Client requesting POST /on");
    relayIsOn.set(true);
    return returnStatus(response);
  }

  @PostMapping("/off")
  public String handleOff(HttpServletResponse response) {
    log.debug("Client requesting POST /off");
    relayIsOn.set(false);
    return returnStatus(response);
  }
}
