package io.netifi.proteus.demo.chat;

@javax.annotation.Generated(
    value = "by Proteus proto compiler (version 0.7.18)",
    comments = "Source: chat.proto")
@io.netifi.proteus.annotations.internal.ProteusGenerated(
    type = io.netifi.proteus.annotations.internal.ProteusResourceType.SERVICE,
    idlClass = BlockingChat.class)
@javax.inject.Named(
    value ="BlockingChatServer")
public final class BlockingChatServer extends io.netifi.proteus.AbstractProteusService {
  private final BlockingChat service;
  private final reactor.core.scheduler.Scheduler scheduler;
  private final java.util.function.Function<? super org.reactivestreams.Publisher<Void>, ? extends org.reactivestreams.Publisher<Void>> chat;
  private final java.util.function.Function<? super org.reactivestreams.Publisher<Void>, ? extends org.reactivestreams.Publisher<Void>> join;
  @javax.inject.Inject
  public BlockingChatServer(BlockingChat service, java.util.Optional<reactor.core.scheduler.Scheduler> scheduler, java.util.Optional<io.micrometer.core.instrument.MeterRegistry> registry) {
    this.scheduler = scheduler.orElse(reactor.core.scheduler.Schedulers.elastic());
    this.service = service;
    if (!registry.isPresent()) {
      this.chat = java.util.function.Function.identity();
      this.join = java.util.function.Function.identity();
    } else {
      this.chat = io.netifi.proteus.metrics.ProteusMetrics.timed(registry.get(), "proteus.server", "service", BlockingChat.SERVICE_ID, "method", BlockingChat.METHOD_CHAT);
      this.join = io.netifi.proteus.metrics.ProteusMetrics.timed(registry.get(), "proteus.server", "service", BlockingChat.SERVICE_ID, "method", BlockingChat.METHOD_JOIN);
    }

  }

  @java.lang.Override
  public String getService() {
    return BlockingChat.SERVICE_ID;
  }

  @java.lang.Override
  public Class<?> getServiceClass() {
    return service.getClass();
  }

  @java.lang.Override
  public reactor.core.publisher.Mono<Void> fireAndForget(io.rsocket.Payload payload) {
    try {
      io.netty.buffer.ByteBuf metadata = payload.sliceMetadata();
      switch(io.netifi.proteus.frames.ProteusMetadata.getMethod(metadata)) {
        case Chat.METHOD_CHAT: {
          com.google.protobuf.CodedInputStream is = com.google.protobuf.CodedInputStream.newInstance(payload.getData());
          io.netifi.proteus.demo.chat.ChatEvent message = io.netifi.proteus.demo.chat.ChatEvent.parseFrom(is);
          return reactor.core.publisher.Mono.<Void>fromRunnable(()->service.chat(message, metadata)).subscribeOn(scheduler);
        }
        case Chat.METHOD_JOIN: {
          com.google.protobuf.CodedInputStream is = com.google.protobuf.CodedInputStream.newInstance(payload.getData());
          io.netifi.proteus.demo.chat.JoinEvent message = io.netifi.proteus.demo.chat.JoinEvent.parseFrom(is);
          return reactor.core.publisher.Mono.<Void>fromRunnable(()->service.join(message, metadata)).subscribeOn(scheduler);
        }
        default: {
          return reactor.core.publisher.Mono.error(new UnsupportedOperationException());
        }
      }
    } catch (Throwable t) {
      return reactor.core.publisher.Mono.error(t);
    } finally {
      payload.release();
    }
  }

  @java.lang.Override
  public reactor.core.publisher.Mono<io.rsocket.Payload> requestResponse(io.rsocket.Payload payload) {
    return reactor.core.publisher.Mono.error(new UnsupportedOperationException("Request-Response not implemented."));
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

  private static final java.util.function.Function<com.google.protobuf.MessageLite, io.rsocket.Payload> serializer =
    new java.util.function.Function<com.google.protobuf.MessageLite, io.rsocket.Payload>() {
      @java.lang.Override
      public io.rsocket.Payload apply(com.google.protobuf.MessageLite message) {
        int length = message.getSerializedSize();
        io.netty.buffer.ByteBuf byteBuf = io.netty.buffer.ByteBufAllocator.DEFAULT.buffer(length);
        try {
          message.writeTo(com.google.protobuf.CodedOutputStream.newInstance(byteBuf.internalNioBuffer(0, length)));
          byteBuf.writerIndex(length);
          return io.rsocket.util.ByteBufPayload.create(byteBuf);
        } catch (Throwable t) {
          byteBuf.release();
          throw new RuntimeException(t);
        }
      }
    };

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
