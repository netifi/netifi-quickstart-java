package io.netifi.proteus.quickstart.service;

import io.netifi.proteus.quickstart.service.protobuf.HelloRequest;
import io.netifi.proteus.quickstart.service.protobuf.HelloResponse;
import io.netifi.proteus.quickstart.service.protobuf.HelloService;
import reactor.core.publisher.Mono;

/**
 * Service that returns a hello message.
 */
public class DefaultHelloService implements HelloService {

    private final String serviceName;

    public DefaultHelloService(final String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public Mono<HelloResponse> sayHello(HelloRequest message) {
        return Mono.fromCallable(() -> HelloResponse.newBuilder()
                .setMessage("Hello, " + message.getName() + "! from " + serviceName)
                .build());
    }

    @Override
    public double availability() {
        return 1.0;
    }

    @Override
    public Mono<Void> close() {
        return Mono.empty();
    }

    @Override
    public Mono<Void> onClose() {
        return Mono.empty();
    }
}
