package io.khasang.archivarius.dao;

import io.khasang.archivarius.entity.DocType;
import java.util.List;

public interface DocTypeDAO {
    void addDocType(DocType docType);

    void updateDocType(DocType docType);

    void deleteDocType(int id);

    DocType getDocTypeById(int id);

    List<DocType> getDocTypeList();
}

