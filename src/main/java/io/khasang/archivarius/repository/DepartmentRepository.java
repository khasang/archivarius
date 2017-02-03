package io.khasang.archivarius.repository;

import io.khasang.archivarius.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
