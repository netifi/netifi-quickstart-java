package io.netifi.proteus.quickstart.service;

import io.netifi.proteus.Proteus;
import io.netifi.proteus.quickstart.service.protobuf.HelloServiceServer;

import java.util.Optional;
import java.util.UUID;

/** Starts the Proteus Quickstart Server */
public class Main {

  public static void main(String... args) throws Exception {
    String serviceName = "helloservice-" + UUID.randomUUID().toString();

    // Build Netifi Connection
    Proteus netifi =
        Proteus.builder()
            .group("quickstart.services.helloservices") // Group name of service
            .destination(serviceName)
            .accessKey(7685465987873703191L)
            .accessToken("PYYgV9XHSJ/3KqgK5wYjz+73MeA=")
            .host("localhost") // Proteus Router Host
            .port(8001) // Proteus Router Port
            .build();

    // Add Service to Respond to Requests
    netifi.addService(new HelloServiceServer(new DefaultHelloService(serviceName), Optional.empty()));

    // Connect to Netifi Proteus Platform
    netifi.group("quickstart.services.helloservices");

    // Keep the Service Running
    Thread.currentThread().join();
  }
}
