package com.jazavac.relaysim.discovery;

import static java.lang.Integer.parseInt;

import java.io.IOException;
import java.net.InetAddress;
import javax.annotation.PreDestroy;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ServiceRegistrator {

  private static final String SERVICE_TYPE = "_relayAPI._tcp.local.";
  private static final String SERVICE_NAME = "relay_API_simulator";
  private static final String SERVICE_TEXT = "GET /status; POST /on; POST /off";

  private JmDNS jmDNS;

  private Environment environment;

  public ServiceRegistrator(Environment env) {
    this.environment = env;
  }

  @EventListener(ApplicationStartedEvent.class)
  public void registerService() throws IOException {
    int runningPort = parseInt(environment.getProperty("local.server.port"));
    jmDNS = JmDNS.create(InetAddress.getLocalHost());
    ServiceInfo serviceInfo =
        ServiceInfo.create(SERVICE_TYPE, SERVICE_NAME, runningPort, SERVICE_TEXT);
    jmDNS.registerService(serviceInfo);
    log.debug("Service registered");
  }

  @PreDestroy
  void unregisterService() throws IOException {
    jmDNS.unregisterAllServices();
    jmDNS.close();
    log.debug("Service unregistered");
  }
}
