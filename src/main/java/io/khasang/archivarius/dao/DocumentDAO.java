package io.khasang.archivarius.dao;

import io.khasang.archivarius.entity.Document;

import java.util.List;

public interface DocumentDAO {

    void addDocument(Document document);

    void updateDocument(Document document);

    void deleteDocument(int id);

    Document getDocumentById(int id);

    Document getDocumentByAuthor(String author);

    Document getDocumentByDestination(String destination);

    List<Document> getDocumentList();
}

