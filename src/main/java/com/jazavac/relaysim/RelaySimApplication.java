package com.jazavac.relaysim;

import java.io.IOException;
import java.net.InetAddress;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RelaySimApplication {

  public static void main(String[] args) throws IOException {
    JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
    ServiceInfo serviceInfo =
        ServiceInfo.create(
            "_relayAPI._tcp.local.",
            "Relay API simulator",
            8080,
            "GET /status; POST /on; POST /off");
    jmdns.registerService(serviceInfo);

    SpringApplication.run(RelaySimApplication.class, args);
  }
}
