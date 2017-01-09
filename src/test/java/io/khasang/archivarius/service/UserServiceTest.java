package io.khasang.archivarius.service;

import io.khasang.archivarius.dao.UserDAO;
import io.khasang.archivarius.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private static final int ID = 12345;
    private static final String NAME = "Tested User";
    @InjectMocks
    UserService userService = new UserService();

    @Mock
    UserDAO userDAO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUserByIdTest() {
        User userToSave = new User();
        userToSave.setLogin(NAME);
        userToSave.setId(ID);
        when(userDAO.getUserById(ID)).thenReturn(userToSave);
        User userById = userService.getUserById(ID);
        assertEquals(NAME, userById.getLogin());
    }

    @Test
    public void addUserTestSize() {
        List<User> companies = new ArrayList<>();
        User userToSave = new User();
        userToSave.setLogin(NAME);
        userToSave.setId(ID);
        companies.add(userToSave);

        when(userDAO.getUserList()).thenReturn(companies);
        assertEquals(1, userService.getUserList().size());
    }

}
