package io.khasang.archivarius.repository;

import io.khasang.archivarius.entity.DocKey;
import io.khasang.archivarius.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
    List<Document> findByTitleContaining(String title);
    List<Document> findByDocKey(DocKey docKey);
}
