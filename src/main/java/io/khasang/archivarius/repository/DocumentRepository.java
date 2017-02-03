package io.khasang.archivarius.repository;

import io.khasang.archivarius.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
    List<Document> findByTitleContaining(String title);

    @Query("select d from Document d where d.docKey = ?1 and (d.author = ?2 or d.worker = ?3 or d.department = ?4)")
    List<Document> findByDocKeyAndAuthorOrWorkerOrDepartment(DocKey docKey, User author, Worker worker, Department department);

    List<Document> findByAuthorOrWorkerOrDepartment(User author, Worker worker, Department department);
}
