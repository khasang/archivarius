package io.khasang.archivarius.dao;

import io.khasang.archivarius.entity.Company;
import io.khasang.archivarius.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleDAO {
    void addRole(Role role);

    void updateRole(Role role);

    void deleteRole(Role role);

    void deleteRoleById(int id);

    Role getRoleById(int id);

    Role getRoleByName(String name);

    List<Role> getRoleList();
}
