package io.netifi.proteus.demo.chat;

/**
 */
@javax.annotation.Generated(
    value = "by Proteus proto compiler (version 0.7.18)",
    comments = "Source: chat.proto")
public interface BlockingChat {
  String SERVICE_ID = "io.netifi.proteus.demo.chat.Chat";
  String METHOD_CHAT = "Chat";
  String METHOD_JOIN = "Join";

  /**
   */
  void chat(io.netifi.proteus.demo.chat.ChatEvent message, io.netty.buffer.ByteBuf metadata);

  /**
   */
  void join(io.netifi.proteus.demo.chat.JoinEvent message, io.netty.buffer.ByteBuf metadata);
}
