package com.jazavac.relaysim.discovery;

import java.io.IOException;
import java.net.InetAddress;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import org.springframework.stereotype.Component;

@Component
public class ServiceRegistrator {

  private static final String SERVICE_TYPE = "_relayAPI._tcp.local.";
  private static final String SERVICE_NAME = "relay_API_simulator";
  private static final String SERVICE_TEXT = "GET /status; POST /on; POST /off";
  private static final int SERVICE_PORT = 8080; // TODO get port at runtime

  private JmDNS jmDNS;

  @PostConstruct
  void registerService() throws IOException {
    jmDNS = JmDNS.create(InetAddress.getLocalHost());
    ServiceInfo serviceInfo =
        ServiceInfo
            .create(SERVICE_TYPE, SERVICE_NAME, SERVICE_PORT, SERVICE_TEXT);
    jmDNS.registerService(serviceInfo);
    System.out.println("Service registered");
  }

  @PreDestroy
  void unregisterService() throws IOException {
    jmDNS.unregisterAllServices();
    jmDNS.close();
    System.out.println("Service unregistered");
  }
}
