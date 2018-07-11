package io.netifi.proteus.demo;

import io.netifi.proteus.fanout.randomstring.RandomStringGenerator;
import io.netifi.proteus.fanout.randomstring.RandomStringRequest;
import io.netifi.proteus.fanout.randomstring.RandomStringResponse;

import io.netifi.proteus.demo.chat.ChatEvent;
import io.netifi.proteus.demo.chat.JoinEvent;
import io.netifi.proteus.demo.chat.Chat;


import io.netty.buffer.ByteBuf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ThreadLocalRandom;

public class MessageDrivenRandomStringGenerator implements RandomStringGenerator, Chat {
    private static final Logger logger = LogManager.getLogger(MessageDrivenRandomStringGenerator.class);

    private final Flux<String> _source;

    public MessageDrivenRandomStringGenerator(Flux<String> source) {
        this._source = source;
    }

    public Flux<RandomStringResponse> generateString(RandomStringRequest message, ByteBuf metadata) {
        int min = message.getMin();
        int max = message.getMax();

        return Flux.<RandomStringResponse>empty();
//        return Flux.<Integer>generate(
//                sink -> {
//                    int size = ThreadLocalRandom.current().nextInt(min, max);
//                    sink.next(size);
//                })
//                .flatMap(
//                        i -> {
//                            return randomCharGeneratorClient
//                                    .generateChar(RandomCharRequest.getDefaultInstance())
//                                    .limitRequest(i)
//                                    .reduce(
//                                            "",
//                                            (s, randomCharResponse) -> {
//                                                return s + randomCharResponse.getGenerated();
//                                            });
//                        })
//                .map(s -> RandomStringResponse.newBuilder().setGenerated(s).build())
//                .doOnError(Throwable::printStackTrace)
//                .doOnNext(s -> logger.info(s.toString()));
    }

    public Mono<Void> join(JoinEvent evt, ByteBuf metadata){
        return Mono.empty();
    }

    public Mono<Void> chat(ChatEvent evt, ByteBuf metadata){
        return Mono.empty();
    }
}
