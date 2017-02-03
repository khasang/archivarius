package io.khasang.archivarius.service;

import io.khasang.archivarius.entity.DocType;
import io.khasang.archivarius.repository.DocTypeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class DocTypeServiceTest {
    private static final int ID = 12345;
    private static final String NAME = "Tested DocType";
    @InjectMocks
    DocTypeService docTypeService = new DocTypeService();

    @Mock
    DocTypeRepository docTypeRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getDocTypeByIdTest() {
        DocType docTypeToSave = new DocType();
        docTypeToSave.setName(NAME);
        docTypeToSave.setId(ID);
        when(docTypeRepository.findOne(ID)).thenReturn(docTypeToSave);
        DocType docTypeById = docTypeService.getDocTypeById(ID);
        assertEquals(NAME, docTypeById.getName());
    }

    @Test
    public void addDocTypeTestSize() {
        List<DocType> companies = new ArrayList<>();
        DocType docTypeToSave = new DocType();
        docTypeToSave.setName(NAME);
        docTypeToSave.setId(ID);
        companies.add(docTypeToSave);

        when(docTypeRepository.findAll()).thenReturn(companies);
        assertEquals(1, docTypeService.getDocTypeList().size());
    }

}
