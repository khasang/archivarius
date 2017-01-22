package io.khasang.archivarius.service;

import io.khasang.archivarius.dao.DocumentDAO;
import io.khasang.archivarius.entity.DocKey;
import io.khasang.archivarius.entity.Document;
import io.khasang.archivarius.messaging.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DocumentService {
    @Autowired
    DocumentDAO documentDAO;

    @Autowired
    MessageSender messageSender;

    static final Logger LOG = LoggerFactory.getLogger(DocumentService.class);

    public Document getDocumentById(int id) {
        return documentDAO.getDocumentById(id);
    }

    public List<Document> getDocumentList() {
        return documentDAO.getDocumentList();
    }

    public List<Document> getDocKeyList(DocKey docKey) {
        return documentDAO.getDocKeyList(docKey);
    }

    public void updateDocument(Document document) {
        LOG.debug("Application : sending order request {}", document);
        messageSender.sendMessage(document);
        documentDAO.updateDocument(document);
    }

    public void deleteDocument(int id) {
        documentDAO.deleteDocument(id);
    }

    public List<Document> searchDocument(String searchRequest) {
        return documentDAO.searchDocument(searchRequest);
    }
}

