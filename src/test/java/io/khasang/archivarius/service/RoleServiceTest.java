package io.khasang.archivarius.service;

import io.khasang.archivarius.entity.Role;
import io.khasang.archivarius.repository.RoleRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class RoleServiceTest {
    private static final int ID = 12345;
    private static final String NAME = "Tested Role";
    @InjectMocks
    RoleService roleService = new RoleService();

    @Mock
    RoleRepository roleRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getRoleByIdTest() {
        Role roleToSave = new Role();
        roleToSave.setName(NAME);
        roleToSave.setId(ID);
        when(roleRepository.findOne(ID)).thenReturn(roleToSave);
        Role roleById = roleService.getRoleById(ID);
        assertEquals(NAME, roleById.getName());
    }

    @Test
    public void addRoleTestSize() {
        List<Role> companies = new ArrayList<>();
        Role roleToSave = new Role();
        roleToSave.setName(NAME);
        roleToSave.setId(ID);
        companies.add(roleToSave);

        when(roleRepository.findAll()).thenReturn(companies);
        assertEquals(1, roleService.getRoleList().size());
    }

}
