package io.khasang.archivarius.service;

import io.khasang.archivarius.dao.RoleDAO;
import io.khasang.archivarius.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Component
@Transactional
public class RoleService {
    @Autowired
    RoleDAO roleDAO;

    public void addRole(Role role) {
        roleDAO.addRole(role);
    }

    public Role getRoleById(int id) {
        return roleDAO.getRoleById(id);
    }

    public List<Role> getRoleList() {
        return roleDAO.getRoleList();
    }

    public void updateRole(Role role) {
        roleDAO.updateRole(role);
    }

    public void deleteRole(Role role) {
        roleDAO.deleteRole(role);
    }

    public void deleteRoleById(int id) {
        roleDAO.deleteRoleById(id);
    }
}
