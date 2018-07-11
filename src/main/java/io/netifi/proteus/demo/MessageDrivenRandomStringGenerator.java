package io.netifi.proteus.demo;

import io.netifi.proteus.fanout.randomstring.RandomStringGenerator;
import io.netifi.proteus.fanout.randomstring.RandomStringRequest;
import io.netifi.proteus.fanout.randomstring.RandomStringResponse;
import io.netty.buffer.ByteBuf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactor.core.publisher.Flux;

import java.util.concurrent.ThreadLocalRandom;

public class MessageDrivenRandomStringGenerator implements RandomStringGenerator {
    private static final Logger logger = LogManager.getLogger(DefauMessageDrivenRandomStringGeneratorltRandomStringGenerator.class);

    private final Flux<String> _source;

    public MessageDrivenRandomStringGenerator(Flux<String> source) {
        this._source = source;
    }

    @Override
    public Flux<RandomStringResponse> generateString(RandomStringRequest message, ByteBuf metadata) {
        int min = message.getMin();
        int max = message.getMax();

        return Flux.<Integer>generate(
                sink -> {
                    int size = ThreadLocalRandom.current().nextInt(min, max);
                    sink.next(size);
                })
                .flatMap(
                        i -> {
                            return randomCharGeneratorClient
                                    .generateChar(RandomCharRequest.getDefaultInstance())
                                    .limitRequest(i)
                                    .reduce(
                                            "",
                                            (s, randomCharResponse) -> {
                                                return s + randomCharResponse.getGenerated();
                                            });
                        })
                .map(s -> RandomStringResponse.newBuilder().setGenerated(s).build())
                .doOnError(Throwable::printStackTrace)
                .doOnNext(s -> logger.info(s.toString()));
    }
}
