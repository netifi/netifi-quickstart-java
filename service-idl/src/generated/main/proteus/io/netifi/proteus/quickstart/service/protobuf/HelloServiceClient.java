package io.netifi.proteus.quickstart.service.protobuf;

@javax.annotation.Generated(
    value = "by Proteus proto compiler (version 0.4.1)",
    comments = "Source: io/netifi/proteus/quickstart/service/protobuf/service.proto")
public final class HelloServiceClient implements HelloService {
  private final io.rsocket.RSocket rSocket;
  private final java.util.function.Function<? super org.reactivestreams.Publisher<io.netifi.proteus.quickstart.service.protobuf.HelloResponse>, ? extends org.reactivestreams.Publisher<io.netifi.proteus.quickstart.service.protobuf.HelloResponse>> sayHello;

  public HelloServiceClient(io.rsocket.RSocket rSocket) {
    this.rSocket = rSocket;
    this.sayHello = java.util.function.Function.identity();
  }

  public HelloServiceClient(io.rsocket.RSocket rSocket, io.micrometer.core.instrument.MeterRegistry registry) {
    this.rSocket = rSocket;
    this.sayHello = io.netifi.proteus.metrics.ProteusMetrics.timed(registry, "proteus.client", "namespace", "io.netifi.proteus.quickstart.service", "service", "HelloService", "method", "sayHello");
  }

  public reactor.core.publisher.Mono<io.netifi.proteus.quickstart.service.protobuf.HelloResponse> sayHello(io.netifi.proteus.quickstart.service.protobuf.HelloRequest message) {
    return sayHello(message, io.netty.buffer.Unpooled.EMPTY_BUFFER);
  }

  @java.lang.Override
  public reactor.core.publisher.Mono<io.netifi.proteus.quickstart.service.protobuf.HelloResponse> sayHello(io.netifi.proteus.quickstart.service.protobuf.HelloRequest message, io.netty.buffer.ByteBuf metadata) {
    return reactor.core.publisher.Mono.defer(new java.util.function.Supplier<reactor.core.publisher.Mono<io.rsocket.Payload>>() {
      @java.lang.Override
      public reactor.core.publisher.Mono<io.rsocket.Payload> get() {
        final int length = io.netifi.proteus.frames.ProteusMetadata.computeLength();
        io.netty.buffer.ByteBuf metadataBuf = io.netty.buffer.ByteBufAllocator.DEFAULT.directBuffer(length);
        io.netifi.proteus.frames.ProteusMetadata.encode(metadataBuf, HelloService.NAMESPACE_ID, HelloService.SERVICE_ID, HelloService.METHOD_SAY_HELLO, metadata);
        io.netty.buffer.ByteBuf data = serialize(message);
        return rSocket.requestResponse(io.rsocket.util.ByteBufPayload.create(data, metadataBuf));
      }
    }).map(deserializer(io.netifi.proteus.quickstart.service.protobuf.HelloResponse.parser())).transform(sayHello);
  }

  private static io.netty.buffer.ByteBuf serialize(final com.google.protobuf.MessageLite message) {
    io.netty.buffer.ByteBuf byteBuf = io.netty.buffer.ByteBufAllocator.DEFAULT.directBuffer(message.getSerializedSize());
    try {
      message.writeTo(com.google.protobuf.CodedOutputStream.newInstance(byteBuf.nioBuffer(0, byteBuf.writableBytes())));
      byteBuf.writerIndex(byteBuf.capacity());
      return byteBuf;
    } catch (Throwable t) {
      byteBuf.release();
      throw new RuntimeException(t);
    }
  }

  private static <T> java.util.function.Function<io.rsocket.Payload, T> deserializer(final com.google.protobuf.Parser<T> parser) {
    return new java.util.function.Function<io.rsocket.Payload, T>() {
      @java.lang.Override
      public T apply(io.rsocket.Payload payload) {
        try {
          com.google.protobuf.CodedInputStream is = com.google.protobuf.CodedInputStream.newInstance(payload.getData());
          return parser.parseFrom(is);
        } catch (Throwable t) {
          throw new RuntimeException(t);
        } finally {
          payload.release();
        }
      }
    };
  }
}
