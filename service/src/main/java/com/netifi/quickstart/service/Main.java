package com.netifi.quickstart.service;

import com.netifi.broker.BrokerClient;
import com.netifi.common.tags.Tags;
import com.netifi.quickstart.service.protobuf.HelloServiceServer;
import java.util.Optional;
import java.util.UUID;

/** Starts the Netifi Quickstart Server */
public class Main {

  public static void main(String... args) throws Exception {
    String serviceName = "helloservice-" + UUID.randomUUID().toString();

    // Build Netifi Broker Connection
    BrokerClient netifi =
        BrokerClient.tcp()
            .group("quickstart.services.helloservices") // Group name of service
            .destination(serviceName)
            .accessKey(9007199254740991L)
            .accessToken("kTBDVtfRBO4tHOnZzSyY5ym2kfY=")
            .host("localhost") // Netifi Broker Host
            .port(8001) // Netifi Broker Port
            .disableSsl() // Disabled for parity with Javascript Tutorial
            .build();

    // Add Service to Respond to Requests
    netifi.addService(
        new HelloServiceServer(
            new DefaultHelloService(serviceName), Optional.empty(), Optional.empty()));

    // Connect to Netifi Platform
    netifi.groupServiceSocket("quickstart.services.helloservices", Tags.empty());

    // Keep the Service Running
    Thread.currentThread().join();
  }
}
