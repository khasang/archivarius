package io.khasang.archivarius.repository;

import io.khasang.archivarius.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
