package io.khasang.archivarius.documenting;

import io.khasang.archivarius.entity.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.io.Serializable;

@Component
public class DocumentSender {
    @Autowired
    JmsTemplate jmsTemplate;

    public void sendDocument(final Document document) {
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage objectMessage = session.createObjectMessage((Serializable) document);
                return objectMessage;
            }
        });
    }
}
