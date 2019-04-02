package io.netifi.proteus.quickstart.service;

import io.netifi.proteus.Proteus;
import io.netifi.proteus.common.tags.Tags;
import io.netifi.proteus.quickstart.service.protobuf.HelloServiceServer;
import java.util.Optional;
import java.util.UUID;

/** Starts the Proteus Quickstart Server */
public class Main {

  public static void main(String... args) throws Exception {
    String serviceName = "helloservice-" + UUID.randomUUID().toString();

    // Build Netifi Connection
    Proteus netifi =
        Proteus.tcp()
            .group("quickstart.services.helloservices") // Group name of service
            .destination(serviceName)
            .accessKey(9007199254740991L)
            .accessToken("kTBDVtfRBO4tHOnZzSyY5ym2kfY=")
            .host("localhost") // Proteus Broker Host
            .port(8001) // Proteus Broker Port
            .disableSsl() // Disabled for parity with Javascript Tutorial
            .build();

    // Add Service to Respond to Requests
    netifi.addService(
        new HelloServiceServer(
            new DefaultHelloService(serviceName), Optional.empty(), Optional.empty()));

    // Connect to Netifi Proteus Platform
    netifi.groupServiceSocket("quickstart.services.helloservices", Tags.empty());

    // Keep the Service Running
    Thread.currentThread().join();
  }
}
