package io.khasang.archivarius.service;

import io.khasang.archivarius.entity.Role;
import io.khasang.archivarius.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role getRoleById(int id) {
        return roleRepository.findOne(id);
    }

    public List<Role> getRoleList() {
        return roleRepository.findAll();
    }

    public void updateRole(Role role) {
        roleRepository.save(role);
    }

    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }
}
