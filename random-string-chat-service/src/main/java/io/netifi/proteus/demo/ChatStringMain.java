package io.netifi.proteus.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.netflix.spectator.atlas.AtlasConfig;
import io.micrometer.atlas.AtlasMeterRegistry;
import io.netifi.proteus.Proteus;
import io.netifi.proteus.fanout.randomstring.RandomStringGeneratorServer;
import io.netifi.proteus.demo.chat.ChatServer;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

public class ChatStringMain {
    private static final Logger logger = LogManager.getLogger(ChatStringMain.class);

    public static void main(String... args) throws Exception {
        long accessKey = Long.getLong("ACCESS_KEY", 9007199254740991L);
        String accessToken = System.getProperty("ACCESS_TOKEN", "kTBDVtfRBO4tHOnZzSyY5ym2kfY=");
        String host = System.getProperty("BROKER_HOST", "edge.prd.netifi.io");
        int port = Integer.getInteger("BROKER_PORT", 8001);
        String randomStringDestination = UUID.randomUUID().toString();
        String chatDestination = UUID.randomUUID().toString();

        System.out.println("system properties [");
        System.getProperties()
                .forEach(
                        (k, v) -> {
                            System.out.print(k + ": " + v + ", ");
                        });

        System.out.println("\n]");

        AtlasMeterRegistry registry =
                new AtlasMeterRegistry(
                        new AtlasConfig() {
                            @Override
                            public Duration step() {
                                return Duration.ofSeconds(10);
                            }

                            @Override
                            public String get(String k) {
                                return null;
                            }

                            @Override
                            public boolean enabled() {
                                return false;
                            }
                        });

        // Build Netifi Connection
        Proteus stringServerProteus =
                Proteus.builder()
                        .group("fanout.randomStringGenerator") // Group name of service
                        .destination(randomStringDestination)
                        .accessKey(accessKey)
                        .accessToken(accessToken)
                        .meterRegistry(registry)
                        .host(host) // Proteus Router Host
                        .port(port) // Proteus Router Port
                        .poolSize(1)
                        .build();
        System.out.println("Registered to serve strings under destination:" + randomStringDestination);

        Proteus chatServerProteus =
                Proteus.builder()
                        .group("chat-demo") // Group name of service
                        .destination(chatDestination)
                        .accessKey(accessKey)
                        .accessToken(accessToken)
                        .meterRegistry(registry)
                        .host(host) // Proteus Router Host
                        .port(port) // Proteus Router Port
                        .poolSize(1)
                        .build();
        System.out.println("Registered to serve chat message under destination:" + chatDestination);

        MessageDrivenRandomStringGenerator comboGenerator =
                new MessageDrivenRandomStringGenerator();

        stringServerProteus.addService(
                new RandomStringGeneratorServer(comboGenerator, Optional.of(registry)));


        chatServerProteus.addService(new ChatServer(comboGenerator, Optional.of(registry)));

        chatServerProteus.onClose().block();
    }

}
