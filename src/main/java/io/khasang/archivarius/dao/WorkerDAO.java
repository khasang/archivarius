package io.khasang.archivarius.dao;

import io.khasang.archivarius.entity.Worker;

import java.util.List;

public interface WorkerDAO {
    void addWorker(Worker worker);

    void updateWorker(Worker worker);

    void deleteWorker(Worker worker);

    void deleteWorkerById(int id);

    Worker getWorkerById(int id);

    List<Worker> getWorkerList();
}
