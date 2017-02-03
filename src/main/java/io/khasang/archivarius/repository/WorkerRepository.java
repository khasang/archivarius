package io.khasang.archivarius.repository;

import io.khasang.archivarius.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {

}
