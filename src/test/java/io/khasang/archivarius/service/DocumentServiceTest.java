package io.khasang.archivarius.service;


import io.khasang.archivarius.entity.Document;
import io.khasang.archivarius.repository.DocumentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class DocumentServiceTest {
    private static final int ID = 12345;
    private static final String NAME = "Tested Document";
    @InjectMocks
    DocumentService documentService = new DocumentService();

    @Mock
    DocumentRepository documentRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getDocumentByIdTest() {
        Document documentToSave = new Document();
        documentToSave.setTitle(NAME);
        documentToSave.setId(ID);
        when(documentRepository.findOne(ID)).thenReturn(documentToSave);
        Document documentById = documentService.getDocumentById(ID);
        assertEquals(NAME, documentById.getTitle());
    }

    @Test
    public void addDocumentTestSize() {
        List<Document> documents = new ArrayList<>();
        Document documentToSave = new Document();
        documentToSave.setTitle(NAME);
        documentToSave.setId(ID);
        documents.add(documentToSave);

        when(documentRepository.findAll()).thenReturn(documents);
        assertEquals(1, documentService.getDocumentList().size());
    }

}
