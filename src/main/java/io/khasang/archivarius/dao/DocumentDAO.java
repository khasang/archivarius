package io.khasang.archivarius.dao;

import io.khasang.archivarius.entity.Document;
import io.khasang.archivarius.entity.DocumentLifeCycle;

import java.util.List;

public interface DocumentDAO {

    Document getDocumentById(int id);

    List<Document> getDocumentList();
}
