package io.khasang.archivarius.messaging;

import io.khasang.archivarius.service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
public class MessageReceiver {
    static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);

    private static final String DOC_QUEUE = "doc-queue";

    @Autowired
    DocumentService documentService;

    @JmsListener(destination = DOC_QUEUE)
    public void receiveMessage(final Message message) throws JMSException {
        MessageHeaders headers =  message.getHeaders();
        LOG.debug("Application : headers received : {}", headers);
    }
}
