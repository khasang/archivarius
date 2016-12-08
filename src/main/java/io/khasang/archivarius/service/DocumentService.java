package io.khasang.archivarius.service;

import io.khasang.archivarius.dao.DocumentDAO;
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

    public void updateDocumentTitle(Document document) {
        documentDAO.updateDocumentTitle(document);
    }

    public void updateDocumentStatus(Document document) {
        documentDAO.updateDocumentStatus(document);
    }

    public void updateDocumentDeadline(Document document) {
        documentDAO.updateDocumentDeadline(document);
    }

    public void updateDocumentDestination(Document document) {
        documentDAO.updateDocumentDestination(document);
    }

    public void deleteDocument(Document document) {
        documentDAO.deleteDocument(document);
    }
}

