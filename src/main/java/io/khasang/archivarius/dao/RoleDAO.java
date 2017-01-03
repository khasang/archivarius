package io.khasang.archivarius.dao;

import io.khasang.archivarius.entity.Role;

import java.util.List;

public interface RoleDAO {
    void addRole(Role role);

    void updateRole(Role role);

    void deleteRole(Role role);

    void deleteRoleById(int id);

    Role getRoleById(int id);

    List<Role> getRoleList();
}
