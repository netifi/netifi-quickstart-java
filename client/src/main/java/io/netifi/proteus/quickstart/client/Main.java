package io.netifi.proteus.quickstart.client;

import io.netifi.proteus.quickstart.service.protobuf.HelloRequest;
import io.netifi.proteus.quickstart.service.protobuf.HelloServiceClient;
import io.netifi.sdk.Netifi;
import io.netifi.sdk.rs.NetifiSocket;

/**
 * Starts the Proteus Quickstart Client
 */
public class Main {

    public static void main(String... args) {

        // Build Netifi Proteus Connection
        Netifi netifi =
                Netifi.builder()
                        .group("quickstart.clients")    // Group name of client
                        .destination("client1")         // Name of this client instance
                        .host("localhost")              // Proteus Router Host
                        .port(8001)                     // Proteus Router Port
                        .build();

        // Connect to Netifi Proteus Platform
        NetifiSocket conn = netifi.connect("quickstart.services.helloservices").block();

        // Create Client to Communicate with the HelloService (included example service)
        HelloServiceClient client = new HelloServiceClient(conn);

        // Create Request to HelloService
        HelloRequest request = HelloRequest.newBuilder()
                .setName("World")
                .build();

        System.out.println("Sending 'World' to HelloService...");

        // Call the HelloService
        System.out.println(client.sayHello(request).block());
    }
}
