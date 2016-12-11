package io.khasang.archivarius.dao;

import io.khasang.archivarius.entity.Company;
import io.khasang.archivarius.entity.DocumentLifeCycle;

import java.util.List;

public interface DocumentLifeCycleDAO {

    void updateDocumentLifeCycle(DocumentLifeCycle documentLifeCycle);

    void deleteDocumentLifeCycle(DocumentLifeCycle documentLifeCycle);

    void deleteDocumentLifeCycleById(int id);

    DocumentLifeCycle getDocumentLifeCycleById(int id);

    List<DocumentLifeCycle> getDocumentLifeCycleList();
}
