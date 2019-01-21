package io.netifi.proteus.quickstart.service.protobuf;

@javax.annotation.Generated(
    value = "by RSocket RPC proto compiler",
    comments = "Source: io/netifi/proteus/quickstart/service/protobuf/service.proto")
@io.rsocket.rpc.annotations.internal.Generated(
    type = io.rsocket.rpc.annotations.internal.ResourceType.CLIENT,
    idlClass = HelloService.class)
public final class HelloServiceClient implements HelloService {
  private final io.rsocket.RSocket rSocket;
  private final java.util.function.Function<? super org.reactivestreams.Publisher<io.netifi.proteus.quickstart.service.protobuf.HelloResponse>, ? extends org.reactivestreams.Publisher<io.netifi.proteus.quickstart.service.protobuf.HelloResponse>> sayHello;
  private final java.util.function.Function<java.util.Map<String, String>, java.util.function.Function<? super org.reactivestreams.Publisher<io.netifi.proteus.quickstart.service.protobuf.HelloResponse>, ? extends org.reactivestreams.Publisher<io.netifi.proteus.quickstart.service.protobuf.HelloResponse>>> sayHelloTrace;

  public HelloServiceClient(io.rsocket.RSocket rSocket) {
    this.rSocket = rSocket;
    this.sayHello = java.util.function.Function.identity();
    this.sayHelloTrace = io.rsocket.rpc.tracing.Tracing.trace();
  }

  public HelloServiceClient(io.rsocket.RSocket rSocket, io.micrometer.core.instrument.MeterRegistry registry) {
    this.rSocket = rSocket;
    this.sayHello = io.rsocket.rpc.metrics.Metrics.timed(registry, "rsocket.client", "service", HelloService.SERVICE, "method", HelloService.METHOD_SAY_HELLO);
    this.sayHelloTrace = io.rsocket.rpc.tracing.Tracing.trace();
  }


  public HelloServiceClient(io.rsocket.RSocket rSocket, io.opentracing.Tracer tracer) {
    this.rSocket = rSocket;
    this.sayHello = java.util.function.Function.identity();
    this.sayHelloTrace = io.rsocket.rpc.tracing.Tracing.trace(tracer, HelloService.METHOD_SAY_HELLO, io.rsocket.rpc.tracing.Tag.of("rsocket.service", HelloService.SERVICE), io.rsocket.rpc.tracing.Tag.of("rsocket.rpc.role", "client"), io.rsocket.rpc.tracing.Tag.of("rsocket.rpc.version", ""));
  }


  public HelloServiceClient(io.rsocket.RSocket rSocket, io.micrometer.core.instrument.MeterRegistry registry, io.opentracing.Tracer tracer) {
    this.rSocket = rSocket;
    this.sayHello = io.rsocket.rpc.metrics.Metrics.timed(registry, "rsocket.client", "service", HelloService.SERVICE, "method", HelloService.METHOD_SAY_HELLO);
    this.sayHelloTrace = io.rsocket.rpc.tracing.Tracing.trace(tracer, HelloService.METHOD_SAY_HELLO, io.rsocket.rpc.tracing.Tag.of("rsocket.service", HelloService.SERVICE), io.rsocket.rpc.tracing.Tag.of("rsocket.rpc.role", "client"), io.rsocket.rpc.tracing.Tag.of("rsocket.rpc.version", ""));
  }

  @io.rsocket.rpc.annotations.internal.GeneratedMethod(returnTypeClass = io.netifi.proteus.quickstart.service.protobuf.HelloResponse.class)
  public reactor.core.publisher.Mono<io.netifi.proteus.quickstart.service.protobuf.HelloResponse> sayHello(io.netifi.proteus.quickstart.service.protobuf.HelloRequest message) {
    return sayHello(message, io.netty.buffer.Unpooled.EMPTY_BUFFER);
  }

  @java.lang.Override
  @io.rsocket.rpc.annotations.internal.GeneratedMethod(returnTypeClass = io.netifi.proteus.quickstart.service.protobuf.HelloResponse.class)
  public reactor.core.publisher.Mono<io.netifi.proteus.quickstart.service.protobuf.HelloResponse> sayHello(io.netifi.proteus.quickstart.service.protobuf.HelloRequest message, io.netty.buffer.ByteBuf metadata) {
  java.util.Map<String, String> map = new java.util.HashMap<>();
    return reactor.core.publisher.Mono.defer(new java.util.function.Supplier<reactor.core.publisher.Mono<io.rsocket.Payload>>() {
      @java.lang.Override
      public reactor.core.publisher.Mono<io.rsocket.Payload> get() {
        final io.netty.buffer.ByteBuf data = serialize(message);
        final io.netty.buffer.ByteBuf tracing = io.rsocket.rpc.tracing.Tracing.mapToByteBuf(io.netty.buffer.ByteBufAllocator.DEFAULT, map);
        final io.netty.buffer.ByteBuf metadataBuf = io.rsocket.rpc.frames.Metadata.encode(io.netty.buffer.ByteBufAllocator.DEFAULT, HelloService.SERVICE, HelloService.METHOD_SAY_HELLO, tracing, metadata);
        tracing.release();
        metadata.release();
        return rSocket.requestResponse(io.rsocket.util.ByteBufPayload.create(data, metadataBuf));
      }
    }).map(deserializer(io.netifi.proteus.quickstart.service.protobuf.HelloResponse.parser())).transform(sayHello).transform(sayHelloTrace.apply(map));
  }

  private static io.netty.buffer.ByteBuf serialize(final com.google.protobuf.MessageLite message) {
    int length = message.getSerializedSize();
    io.netty.buffer.ByteBuf byteBuf = io.netty.buffer.ByteBufAllocator.DEFAULT.buffer(length);
    try {
      message.writeTo(com.google.protobuf.CodedOutputStream.newInstance(byteBuf.internalNioBuffer(0, length)));
      byteBuf.writerIndex(length);
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
