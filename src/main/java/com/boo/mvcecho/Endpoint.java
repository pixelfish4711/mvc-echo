package com.boo.mvcecho;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class Endpoint {

    private AtomicInteger counter = new AtomicInteger();

    private static final Logger LOGGER = LogManager.getLogger();

    @GetMapping("/hallo")
    public String getDelayedEcho() throws InterruptedException {
        LOGGER.info("call /hallo " + counter.incrementAndGet());
        TimeUnit.MILLISECONDS.sleep(1000);
        return "Echo";
    }

}
