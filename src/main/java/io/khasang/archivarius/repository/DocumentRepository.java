package io.khasang.archivarius.repository;

import io.khasang.archivarius.entity.DocKey;
import io.khasang.archivarius.entity.Document;
import io.khasang.archivarius.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
    List<Document> findByTitleContaining(String title);
    List<Document> findByDocKeyAndAuthor(DocKey docKey, User author);
    List<Document> findByAuthor(User author);
}
