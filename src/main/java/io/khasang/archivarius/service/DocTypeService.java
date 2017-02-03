package io.khasang.archivarius.service;

import io.khasang.archivarius.entity.DocType;
import io.khasang.archivarius.repository.DocTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
@Transactional
public class DocTypeService {
    @Autowired
    DocTypeRepository docTypeRepository;

    public DocType getDocTypeById(int id) {
        return docTypeRepository.findOne(id);
    }

    public List<DocType> getDocTypeList() {
        return docTypeRepository.findAll();
    }

    public void updateDocType(DocType docType) {
        docTypeRepository.save(docType);
    }

    public void deleteDocType(int id) {
        docTypeRepository.delete(id);
    }
}
