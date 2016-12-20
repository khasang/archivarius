package io.khasang.archivarius.service;

import io.khasang.archivarius.dao.DocTypeDAO;
import io.khasang.archivarius.entity.DocType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
@Transactional
public class DocTypeService {
    @Autowired
    DocTypeDAO docTypeDAO;

    public void addDocType(DocType docType) {
        docTypeDAO.addDocType(docType);
    }

    public DocType getDocTypeById(int id) {
        return docTypeDAO.getDocTypeById(id);
    }

    public List<DocType> getDocTypeList() {
        return docTypeDAO.getDocTypeList();
    }

    public void updateDocType(DocType docType) {
        docTypeDAO.updateDocType(docType);
    }

    public void deleteDocType(int id) {
        docTypeDAO.deleteDocType(id);
    }
}
