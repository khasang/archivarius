package io.khasang.archivarius.service;

import io.khasang.archivarius.dao.DocumentDAO;
import io.khasang.archivarius.documenting.DocumentSender;
import io.khasang.archivarius.documenting.InventoryResponse;
import io.khasang.archivarius.entity.Document;
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
    DocumentSender documentSender;

    public void addDocument(Document document) {
        documentDAO.addDocument(document);
    }

    public Document getDocumentById(int id) {
        return documentDAO.getDocumentById(id);
    }

    public Document getDocumentByAuthor(String author) {
        return documentDAO.getDocumentByAuthor(author);
    }

    public Document getDocumentByDestination(String destination) {
        return documentDAO.getDocumentByDestination(destination);
    }

    public List<Document> getDocumentList() {
        return documentDAO.getDocumentList();
    }

    public List<Document> getInboxList() {
        return documentDAO.getInboxList();
    }

    public List<Document> getOutboxList() {
        return documentDAO.getOutboxList();
    }

    public List<Document> getInternalList() {
        return documentDAO.getInternalList();
    }

    public List<Document> getControlList() {
        return documentDAO.getControlList();
    }

    public void updateDocument(Document document) {
        documentSender.sendDocument(document);
        documentDAO.updateDocument(document);
    }

    public void deleteDocument(int id) {
        documentDAO.deleteDocument(id);
    }

    public void updateInventoryResponseDocument(InventoryResponse response) {
        Document document = documentDAO.getDocumentById(Integer.parseInt(response.getDocumentId()));
        documentDAO.updateDocument(document);
    }
}

