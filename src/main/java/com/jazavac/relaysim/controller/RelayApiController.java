package com.jazavac.relaysim.controller;

import com.jazavac.relaysim.RelayResponse;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is used to simulate turning a relay on and off, as well as getting the current state
 */
@RestController
@CrossOrigin
@Slf4j
public class RelayApiController {

  private AtomicBoolean relayIsOn = new AtomicBoolean(false);

  @GetMapping("/status")
  public RelayResponse returnStatus() {
    log.debug("Client requesting GET /status");
    return new RelayResponse(relayIsOn.get());
  }

  @PostMapping("/on")
  public RelayResponse handleOn() {
    log.debug("Client requesting POST /on");
    relayIsOn.set(true);
    return returnStatus();
  }

  @PostMapping("/off")
  public RelayResponse handleOff() {
    log.debug("Client requesting POST /off");
    relayIsOn.set(false);
    return returnStatus();
  }
}
