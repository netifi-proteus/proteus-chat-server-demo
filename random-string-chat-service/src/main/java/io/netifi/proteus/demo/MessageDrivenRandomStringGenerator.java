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

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class MessageDrivenRandomStringGenerator implements RandomStringGenerator, Chat {
    private static final Logger logger = LogManager.getLogger(MessageDrivenRandomStringGenerator.class);

    private final ArrayBlockingQueue<String> _buffer;

    public MessageDrivenRandomStringGenerator() {
        this._buffer = new ArrayBlockingQueue<>(1000);
    }

    public Flux<RandomStringResponse> generateString(RandomStringRequest message, ByteBuf metadata) {
        int min = message.getMin();
        int max = message.getMax();

        return Flux.<Integer>generate(
                sink -> {
                    int size = ThreadLocalRandom.current().nextInt(min, max);
                    sink.next(size);
                })
                .map(
                    i -> {
                        //Default to size-worth of " " characters
                        String str = new String(new char[i]).replace("\0", " ");;
                        try {
                            str = _buffer.take();
                            while(str.length() < i){
                                str = new String(new char[2 * str.length()]).replace("\0", str);
                            }
                            str = str.substring(0, i);
                        } catch (InterruptedException ex){
                            //Swallow, just move on?
                        }
                        System.out.println("Providing string:" + str);
                        return str;
                    })
                .map(s -> RandomStringResponse.newBuilder().setGenerated(s).build())
                .doOnError(Throwable::printStackTrace)
                .doOnNext(s -> logger.info(s.toString()));
    }

    public Mono<Void> join(JoinEvent evt, ByteBuf metadata){
        return Mono.empty();
    }

    public Mono<Void> chat(ChatEvent evt, ByteBuf metadata){
        String msg = evt.getMessage();
        System.out.println("Received message:" + msg);
        this._buffer.offer(evt.getMessage());
        return Mono.empty();
    }
}
