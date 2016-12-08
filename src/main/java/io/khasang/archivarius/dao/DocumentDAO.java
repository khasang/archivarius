package io.khasang.archivarius.dao;

import io.khasang.archivarius.entity.Document;

import java.util.List;

public interface DocumentDAO {

    void addDocument(Document document);

    void updateDocumentTitle(Document document);

    void updateDocumentStatus(Document document);

    void updateDocumentDeadline(Document document);

    void updateDocumentDestination(Document document);

    void deleteDocument(Document document);

    Document getDocumentById(int id);

    Document getDocumentByAuthor(String author);

    Document getDocumentByDestination(String destination);

    List<Document> getDocumentList();
}

