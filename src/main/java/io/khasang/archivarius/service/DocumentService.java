package io.khasang.archivarius.service;

import io.khasang.archivarius.entity.DocKey;
import io.khasang.archivarius.entity.Document;
import io.khasang.archivarius.messaging.MessageSender;
import io.khasang.archivarius.repository.DocumentRepository;
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
    DocumentRepository documentRepository;

    @Autowired
    MessageSender messageSender;

    static final Logger LOG = LoggerFactory.getLogger(DocumentService.class);

    public Document getDocumentById(int id) {
        return documentRepository.findOne(id);
    }

    public List<Document> getDocumentList() {
        return documentRepository.findAll();
    }

    public List<Document> getDocKeyList(DocKey docKey) {
        return documentRepository.findByDocKey(docKey);
    }

    public void updateDocument(Document document) {
        LOG.debug("Application : sending order request {}", document);
        messageSender.sendMessage(document);
        documentRepository.save(document);
    }

    public void deleteDocument(int id) {
        documentRepository.delete(id);
    }

    public List<Document> searchDocument(String searchRequest) {
        return documentRepository.findByTitleContaining(searchRequest);
    }
}

