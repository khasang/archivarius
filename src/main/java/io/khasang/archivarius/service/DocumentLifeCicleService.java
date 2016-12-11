package io.khasang.archivarius.service;

import io.khasang.archivarius.dao.DocumentLifeCycleDAO;
import io.khasang.archivarius.entity.DocumentLifeCycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DocumentLifeCicleService {
    @Autowired
    DocumentLifeCycleDAO documentLifeCycleDAO;

    public DocumentLifeCycle getDocumentLifeCycleById(int id) {
        return documentLifeCycleDAO.getDocumentLifeCycleById(id);
    }

    public List<DocumentLifeCycle> getDocumentLifeCycleList() {
        return documentLifeCycleDAO.getDocumentLifeCycleList();
    }

    public void updateDocumentLifeCycle(DocumentLifeCycle documentLifeCycle) {
        documentLifeCycleDAO.updateDocumentLifeCycle(documentLifeCycle);
    }

    public void deleteDocumentLifeCycle(DocumentLifeCycle documentLifeCycle) {
        documentLifeCycleDAO.deleteDocumentLifeCycle(documentLifeCycle);
    }

    public void deleteDocumentLifeCycleById(int id) {
        documentLifeCycleDAO.deleteDocumentLifeCycleById(id);
    }
}
