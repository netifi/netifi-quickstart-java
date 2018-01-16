package io.netifi.proteus.quickstart.client;

import io.netifi.proteus.Netifi;
import io.netifi.proteus.quickstart.service.protobuf.HelloRequest;
import io.netifi.proteus.quickstart.service.protobuf.HelloServiceClient;
import io.netifi.proteus.rs.NetifiSocket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Starts the Proteus Quickstart Client
 */
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String... args) {

        // Build Netifi Proteus Connection
        Netifi netifi =
                Netifi.builder()
                        .group("quickstart.clients")                    // Group name of client
                        .destination("client1")                         // Name of this client instance
                        .accountId(100)
                        .accessKey(7685465987873703191L)
                        .minHostsAtStartup(1)
                        .accessToken("PYYgV9XHSJ/3KqgK5wYjz+73MeA=")
                        .host("localhost")                              // Proteus Router Host
                        .port(8001)                                     // Proteus Router Port
                        .build();

        // Connect to Netifi Proteus Platform
        NetifiSocket conn = netifi.connect("quickstart.services.helloservices").block();

        // Create Client to Communicate with the HelloService (included example service)
        HelloServiceClient client = new HelloServiceClient(conn);

        // Create Request to HelloService
        HelloRequest request = HelloRequest.newBuilder()
                .setName("World")
                .build();

        logger.info("Sending 'World' to HelloService...");

        // Call the HelloService
        logger.info(client.sayHello(request).block());
    }
}
