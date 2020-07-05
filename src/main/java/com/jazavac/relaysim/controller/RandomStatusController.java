package com.jazavac.relaysim.controller;

import com.jazavac.relaysim.RelayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** This class is used to simulate getting a status of a relay that turns on and off every second */
@RestController
@CrossOrigin
@Slf4j
public class RandomStatusController {

  @GetMapping("/randomstatus")
  public RelayResponse returnStatus() {
    log.debug("Client requesting GET /randomstatus");
    return new RelayResponse(generateStatus());
  }

  private boolean generateStatus() {
    return (System.currentTimeMillis() / 1000) % 2 == 0;
  }
}
