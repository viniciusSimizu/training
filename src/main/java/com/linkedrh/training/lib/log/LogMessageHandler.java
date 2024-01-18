package com.linkedrh.training.lib.log;

import org.slf4j.Logger;

public class LogMessageHandler {

    private static final String infoEndpointAccessTemplate = "O serviço %s foi chamado";

    public static void infoEndpointRegistry(String service, Logger logger) {
        String message = String.format(LogMessageHandler.infoEndpointAccessTemplate, service);
        logger.info(message);
    }
}
