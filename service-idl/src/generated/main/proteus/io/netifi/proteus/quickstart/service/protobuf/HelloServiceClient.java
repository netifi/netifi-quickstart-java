package io.netifi.proteus.quickstart.service.protobuf;

@javax.annotation.Generated(
    value = "by Proteus proto compiler (version 0.2.1)",
    comments = "Source: io/netifi/proteus/quickstart/service/protobuf/service.proto")
public final class HelloServiceClient implements HelloService {
  private final io.rsocket.RSocket rSocket;

  public HelloServiceClient(io.rsocket.RSocket rSocket) {
    this.rSocket = rSocket;
  }

  @java.lang.Override
  public reactor.core.publisher.Mono<io.netifi.proteus.quickstart.service.protobuf.HelloResponse> sayHello(io.netifi.proteus.quickstart.service.protobuf.HelloRequest message) {
    final int length = io.netifi.proteus.frames.ProteusMetadata.computeLength();
    io.netty.buffer.ByteBuf metadata = io.netty.buffer.ByteBufAllocator.DEFAULT.directBuffer(length);
    io.netifi.proteus.frames.ProteusMetadata.encode(metadata, HelloService.NAMESPACE_ID, HelloService.SERVICE_ID, HelloService.METHOD_SAY_HELLO);
    java.nio.ByteBuffer data = message.toByteString().asReadOnlyByteBuffer();

    return rSocket.requestResponse(new io.rsocket.util.PayloadImpl(data, metadata.nioBuffer(0, length)))
      .map(deserializer(io.netifi.proteus.quickstart.service.protobuf.HelloResponse.parser()));
  }

  @java.lang.Override
  public double availability() {
    return rSocket.availability();
  }

  @java.lang.Override
  public reactor.core.publisher.Mono<Void> close() {
    return rSocket.close();
  }

  @java.lang.Override
  public reactor.core.publisher.Mono<Void> onClose() {
    return rSocket.onClose();
  }

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
