package io.khasang.archivarius.dao;

import io.khasang.archivarius.entity.DocKey;
import io.khasang.archivarius.entity.Document;

import java.util.List;

public interface DocumentDAO {

    void updateDocument(Document document);

    void deleteDocument(int id);

    Document getDocumentById(int id);

    List<Document> getDocumentList();

    List<Document> getDocKeyList(DocKey docKey);

    List<Document> searchDocument(String searchRequest);
}

