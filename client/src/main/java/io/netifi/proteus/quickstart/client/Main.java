package io.netifi.proteus.quickstart.client;

import io.netifi.proteus.Proteus;
import io.netifi.proteus.quickstart.service.protobuf.HelloRequest;
import io.netifi.proteus.quickstart.service.protobuf.HelloServiceClient;
import io.netifi.proteus.rsocket.ProteusSocket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * Starts the Proteus Quickstart Client
 */
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String... args) {

        // Build Netifi Proteus Connection
        Proteus netifi =
                Proteus.builder()
                        .group("quickstart.clients")                    // Group name of client
                        .destination("client1")                         // Name of this client instance
                        .accessKey(9007199254740991L)
                        .accessToken("kTBDVtfRBO4tHOnZzSyY5ym2kfY=")
                        .host("localhost")                              // Proteus Broker Host
                        .port(8001)                                     // Proteus Broker Port
                        .build();

        // Connect to Netifi Proteus Platform
        ProteusSocket conn = netifi.group("quickstart.services.helloservices");
        
        // Create Client to Communicate with the HelloService (included example service)
        HelloServiceClient client = new HelloServiceClient(conn);
        
        Flux.interval(Duration.ofSeconds(1)) // emit tick ever second
            .onBackpressureBuffer() // buffer ticks on back-pressure
            .flatMap(
                l -> {
                    String name = "World-" + l;
                    // Create Request to HelloService
                    HelloRequest request = HelloRequest.newBuilder().setName(name).build();
                
                    logger.info("Sending '{}' to HelloService...", name);
                    return client.sayHello(request).doOnNext(response -> {
                        logger.info("Got response '{}'", response.getMessage());
                    });
                }, 1) // allow one at a lime
            .doOnError(t -> t.printStackTrace())
            .blockLast();
    }
}
