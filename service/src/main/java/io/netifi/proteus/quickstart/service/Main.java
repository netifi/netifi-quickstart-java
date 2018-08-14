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
            .accessKey(9007199254740991L)
            .accessToken("kTBDVtfRBO4tHOnZzSyY5ym2kfY=")
            .host("localhost") // Proteus Broker Host
            .port(8001) // Proteus Broker Port
            .build();

    // Add Service to Respond to Requests
    netifi.addService(new HelloServiceServer(new DefaultHelloService(serviceName), Optional.empty()));

    // Connect to Netifi Proteus Platform
    netifi.group("quickstart.services.helloservices");

    // Keep the Service Running
    Thread.currentThread().join();
  }
}
