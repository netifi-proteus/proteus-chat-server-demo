package io.netifi.proteus.demo.randomstring;

/**
 */
@javax.annotation.Generated(
    value = "by Proteus proto compiler (version 0.7.18)",
    comments = "Source: random-strings.proto")
public interface BlockingRandomStringGenerator {
  String SERVICE_ID = "io.netifi.proteus.demo.randomstring.RandomStringGenerator";
  String METHOD_GENERATE_STRING = "GenerateString";

  /**
   * <pre>
   * Returns a Hello World Message
   * </pre>
   */
  Iterable<io.netifi.proteus.demo.randomstring.RandomStringResponse> generateString(io.netifi.proteus.demo.randomstring.RandomStringRequest message, io.netty.buffer.ByteBuf metadata);
}
