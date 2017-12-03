package io.netifi.proteus.quickstart.service.protobuf;

/**
 */
@javax.annotation.Generated(
    value = "by Proteus proto compiler (version 0.2.1)",
    comments = "Source: io/netifi/proteus/quickstart/service/protobuf/service.proto")
public interface HelloService extends io.rsocket.Availability, io.rsocket.Closeable {
  int NAMESPACE_ID = -1678469490;
  int SERVICE_ID = -1062476274;
  int METHOD_SAY_HELLO = 420423514;

  /**
   * <pre>
   * Returns a Hello World Message
   * </pre>
   */
  reactor.core.publisher.Mono<io.netifi.proteus.quickstart.service.protobuf.HelloResponse> sayHello(io.netifi.proteus.quickstart.service.protobuf.HelloRequest message);
}
