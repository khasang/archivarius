package io.khasang.archivarius.service;

import io.khasang.archivarius.entity.*;
import io.khasang.archivarius.repository.DocumentRepository;
import io.khasang.archivarius.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DocumentService {
    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    UserRepository userRepository;

    static final Logger LOG = LoggerFactory.getLogger(DocumentService.class);

    public Document getDocumentById(int id) {
        return documentRepository.findOne(id);
    }

    public List<Document> getDocumentList() {
        return documentRepository.findByAuthor(findUserForAccess());
    }

    public List<Document> getDocKeyList(DocKey docKey) {
        return documentRepository.findByDocKeyAndAuthorOrWorkerOrDepartment(docKey, findUserForAccess(), findWorkerByUser(), findDepartmentByUser());
    }

    public void updateDocument(Document document) {
        documentRepository.save(document);
    }

    public void deleteDocument(int id) {
        documentRepository.delete(id);
    }

    public List<Document> searchDocument(String searchRequest) {
        return documentRepository.findByTitleContaining(searchRequest);
    }

    private User findUserForAccess() {
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByLogin(((UserDetails) principal).getUsername());
    }

    private Worker findWorkerByUser() {
        return findUserForAccess().getWorker();
    }

    private Department findDepartmentByUser() {
        return findWorkerByUser().getDepartment();
    }
}

