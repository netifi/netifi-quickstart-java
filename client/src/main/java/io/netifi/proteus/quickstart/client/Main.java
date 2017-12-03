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

        // Build Netifi Connection
        Netifi netifi =
                Netifi.builder()
                        .group("quickstart.client")
                        .destination("client")
                        .host("localhost")
                        .port(8001)
                        .build();

        // Connect to Netifi
        NetifiSocket conn = netifi.connect("quickstart.server").block();

        // Create Client to Communicate with the HelloService
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
