package io.netifi.proteus.quickstart.service;

import io.netifi.proteus.quickstart.service.protobuf.HelloServiceServer;
import io.netifi.sdk.Netifi;

/**
 * Starts the Proteus Quickstart Server
 */
public class Main {

    public static void main(String... args) throws Exception {
        // Build Netifi Connection
        Netifi netifi =
                Netifi.builder()
                        .group("quickstart.services.helloservices")     // Group name of service
                        .destination("helloservice1")                   // Name of this service instance
                        .host("localhost")                              // Proteus Router Host
                        .port(8001)                                     // Proteus Router Port
                        .build();

        // Add Service to Respond to Requests
        netifi.addService(new HelloServiceServer(new DefaultHelloService()));

        // Connect to Netifi Proteus Platform
        netifi.connect("quickstart.services.helloservices").block();

        // Keep the Service Running
        Thread.currentThread().join();
    }
}
