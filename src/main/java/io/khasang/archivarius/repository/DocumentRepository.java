package io.khasang.archivarius.repository;

import io.khasang.archivarius.entity.DocKey;
import io.khasang.archivarius.entity.Document;
import io.khasang.archivarius.entity.User;
import io.khasang.archivarius.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
    List<Document> findByTitleContaining(String title);
    List<Document> findByDocKeyAndAuthorOrWorker(DocKey docKey, User author, Worker worker);
    List<Document> findByAuthor(User author);
}
