package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class GrepTest {
    private static final Logger logger = LoggerFactory.getLogger(GrepTest.class);

    @BeforeAll
    static void setUp() throws IOException {

    }

    @AfterAll
    static void tearDown() {

    }

    /**
     * This test case Tests the end to end flow of distributed log querier.
     * @throws InterruptedException
     */
    @Test
    public void endToEndTest() throws InterruptedException {

        Thread serverThread = new Thread(() -> {
            try {
                server s = new server();
                logger.info("Starting server in Grep Test");
                s.run_server();
            } catch (Exception e) {
               throw new RuntimeException("Error while running server in GrepTest", e);
            }
        });
        serverThread.start();

        Thread.sleep(1000);
        client_component c = new client_component("127.0.0.1", 5001, "junitMachine", "grep -n \"GET\"");
        logger.info("Starting client in Grep Test");
        c.run();
        logger.info("End to end flow successfully tested");

    }

}