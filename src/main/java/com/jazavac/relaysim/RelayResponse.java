package com.jazavac.relaysim;

public class RelayResponse {

  private boolean relayIsOn;

  public RelayResponse(boolean relayIsOn) {
    this.relayIsOn = relayIsOn;
  }

  public boolean isRelayIsOn() {
    return relayIsOn;
  }

  public void setRelayIsOn(boolean relayIsOn) {
    this.relayIsOn = relayIsOn;
  }
}
