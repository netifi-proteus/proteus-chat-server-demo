package io.netifi.proteus.demo.chat;

/**
 */
@javax.annotation.Generated(
    value = "by Proteus proto compiler (version 0.7.18)",
    comments = "Source: chat.proto")
public interface Chat {
  String SERVICE = "io.netifi.proteus.demo.chat.Chat";
  String METHOD_CHAT = "Chat";
  String METHOD_JOIN = "Join";

  /**
   */
  reactor.core.publisher.Mono<Void> chat(io.netifi.proteus.demo.chat.ChatEvent message, io.netty.buffer.ByteBuf metadata);

  /**
   */
  reactor.core.publisher.Mono<Void> join(io.netifi.proteus.demo.chat.JoinEvent message, io.netty.buffer.ByteBuf metadata);
}
