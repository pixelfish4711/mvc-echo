package com.boo.mvcecho;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class EndpointTest {

    private static final Logger LOGGER = LogManager.getLogger();

    private WebClient webClient = WebClient.builder().build();

    @Test
    public void getEcho() throws InterruptedException {

        AtomicInteger counter = new AtomicInteger();

        IntStream.range(0, 3000).parallel().forEach(i ->
                webClient.get().uri("http://localhost:8001/hallo")
                        .retrieve()
                        .bodyToMono(String.class)
                        .subscribe(b -> LOGGER.info("result: {}, counter:{}", b, counter.incrementAndGet()))
        );

        Thread.sleep(10000);
    }

}
