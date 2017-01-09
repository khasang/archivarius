package io.khasang.archivarius.documenting;

import io.khasang.archivarius.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
public class DocumentingReceiver {
    private static final String DOCUMENT_RESPONSE_QUEUE = "document-response-queue";

    @Autowired
    DocumentService documentService;

    @JmsListener(destination = DOCUMENT_RESPONSE_QUEUE)
    public void receiveDocument(final Message<InventoryResponse> message) throws JMSException {
        MessageHeaders headers = message.getHeaders();
        InventoryResponse response = message.getPayload();
        documentService.updateInventoryResponseDocument(response);
    }
}
