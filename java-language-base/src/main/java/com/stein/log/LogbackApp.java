package com.stein.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author njl
 * @date 2023/1/30
 */
public class LogbackApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogbackApp.class);

    public static void main(String[] args) {
        // class ch.qos.logback.classic.Logger
        System.out.println(LOGGER.getClass());
        LOGGER.info("this is an {} message", "INFO");
        LOGGER.error("this is an {} message", "ERROR");
        LOGGER.debug("this is an {} message", "DEBUG");
    }
}
