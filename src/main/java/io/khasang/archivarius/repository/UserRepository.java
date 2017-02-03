package io.khasang.archivarius.repository;

import io.khasang.archivarius.entity.User;
import io.khasang.archivarius.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);
    User findByWorker(Worker worker);
}
