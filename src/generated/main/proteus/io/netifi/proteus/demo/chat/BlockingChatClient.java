package io.netifi.proteus.demo.chat;

@javax.annotation.Generated(
    value = "by Proteus proto compiler (version 0.7.18)",
    comments = "Source: chat.proto")
@io.netifi.proteus.annotations.internal.ProteusGenerated(
    type = io.netifi.proteus.annotations.internal.ProteusResourceType.CLIENT,
    idlClass = BlockingChat.class)
public final class BlockingChatClient implements BlockingChat {
  private final io.netifi.proteus.demo.chat.ChatClient delegate;

  public BlockingChatClient(io.rsocket.RSocket rSocket) {
    this.delegate = new io.netifi.proteus.demo.chat.ChatClient(rSocket);
  }

  public BlockingChatClient(io.rsocket.RSocket rSocket, io.micrometer.core.instrument.MeterRegistry registry) {
    this.delegate = new io.netifi.proteus.demo.chat.ChatClient(rSocket, registry);
  }

  @io.netifi.proteus.annotations.internal.ProteusGeneratedMethod(returnTypeClass = Void.class)
  public void chat(io.netifi.proteus.demo.chat.ChatEvent message) {
    chat(message, io.netty.buffer.Unpooled.EMPTY_BUFFER);
  }

  @java.lang.Override
  @io.netifi.proteus.annotations.internal.ProteusGeneratedMethod(returnTypeClass = Void.class)
  public void chat(io.netifi.proteus.demo.chat.ChatEvent message, io.netty.buffer.ByteBuf metadata) {
    delegate.chat(message, metadata).block();
  }

  @io.netifi.proteus.annotations.internal.ProteusGeneratedMethod(returnTypeClass = Void.class)
  public void join(io.netifi.proteus.demo.chat.JoinEvent message) {
    join(message, io.netty.buffer.Unpooled.EMPTY_BUFFER);
  }

  @java.lang.Override
  @io.netifi.proteus.annotations.internal.ProteusGeneratedMethod(returnTypeClass = Void.class)
  public void join(io.netifi.proteus.demo.chat.JoinEvent message, io.netty.buffer.ByteBuf metadata) {
    delegate.join(message, metadata).block();
  }

}

