package io.netifi.proteus.quickstart.service.protobuf;

@javax.annotation.Generated(
    value = "by Proteus proto compiler (version 0.2.1)",
    comments = "Source: io/netifi/proteus/quickstart/service/protobuf/service.proto")
public final class HelloServiceServer implements io.netifi.proteus.ProteusService {
  private final HelloService service;

  public HelloServiceServer(HelloService service) {
    this.service = service;
  }

  @java.lang.Override
  public int getNamespaceId() {
    return HelloService.NAMESPACE_ID;
  }

  @java.lang.Override
  public int getServiceId() {
    return HelloService.SERVICE_ID;
  }

  @java.lang.Override
  public reactor.core.publisher.Mono<Void> fireAndForget(io.rsocket.Payload payload) {
    return reactor.core.publisher.Mono.error(new UnsupportedOperationException("Fire and forget not implemented."));
  }

  @java.lang.Override
  public reactor.core.publisher.Mono<io.rsocket.Payload> requestResponse(io.rsocket.Payload payload) {
    try {
      io.netty.buffer.ByteBuf metadata = io.netty.buffer.Unpooled.wrappedBuffer(payload.getMetadata());
      switch(io.netifi.proteus.frames.ProteusMetadata.methodId(metadata)) {
        case HelloService.METHOD_SAY_HELLO: {
          com.google.protobuf.ByteString data = com.google.protobuf.UnsafeByteOperations.unsafeWrap(payload.getData());
          return service.sayHello(io.netifi.proteus.quickstart.service.protobuf.HelloRequest.parseFrom(data)).map(serializer);
        }
        default: {
          return reactor.core.publisher.Mono.error(new UnsupportedOperationException());
        }
      }
    } catch (Throwable t) {
      return reactor.core.publisher.Mono.error(t);
    }
  }

  @java.lang.Override
  public reactor.core.publisher.Flux<io.rsocket.Payload> requestStream(io.rsocket.Payload payload) {
    return reactor.core.publisher.Flux.error(new UnsupportedOperationException("Request-Stream not implemented."));
  }

  @java.lang.Override
  public reactor.core.publisher.Flux<io.rsocket.Payload> requestChannel(io.rsocket.Payload payload, reactor.core.publisher.Flux<io.rsocket.Payload> publisher) {
    return reactor.core.publisher.Flux.error(new UnsupportedOperationException("Request-Channel not implemented."));
  }

  @java.lang.Override
  public reactor.core.publisher.Flux<io.rsocket.Payload> requestChannel(org.reactivestreams.Publisher<io.rsocket.Payload> payloads) {
    return reactor.core.publisher.Flux.error(new UnsupportedOperationException("Request-Channel not implemented."));
  }

  @java.lang.Override
  public reactor.core.publisher.Mono<Void> metadataPush(io.rsocket.Payload payload) {
    return reactor.core.publisher.Mono.error(new UnsupportedOperationException("Metadata-Push not implemented."));
  }

  @java.lang.Override
  public double availability() {
    return service.availability();
  }

  @java.lang.Override
  public reactor.core.publisher.Mono<Void> close() {
    return service.close();
  }

  @java.lang.Override
  public reactor.core.publisher.Mono<Void> onClose() {
    return service.onClose();
  }

  private static final java.util.function.Function<com.google.protobuf.MessageLite, io.rsocket.Payload> serializer =
    new java.util.function.Function<com.google.protobuf.MessageLite, io.rsocket.Payload>() {
      @java.lang.Override
      public io.rsocket.Payload apply(com.google.protobuf.MessageLite message) {
        return new io.rsocket.util.PayloadImpl(message.toByteString().asReadOnlyByteBuffer());
      }
    };

  private static <T> java.util.function.Function<io.rsocket.Payload, T> deserializer(final com.google.protobuf.Parser<T> parser) {
    return new java.util.function.Function<io.rsocket.Payload, T>() {
      @java.lang.Override
      public T apply(io.rsocket.Payload payload) {
        try {
          com.google.protobuf.ByteString data = com.google.protobuf.UnsafeByteOperations.unsafeWrap(payload.getData());
          return parser.parseFrom(data);
        } catch (Throwable t) {
          throw new RuntimeException(t);
        }
      }
    };
  }
}
