package io.khasang.archivarius.service;

import io.khasang.archivarius.entity.Worker;
import io.khasang.archivarius.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("WorkerService")
@Transactional
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;

    public Worker getWorkerById(int id) {
        return workerRepository.findOne(id);
    }

    public List<Worker> getWorkerList() {
        return workerRepository.findAll();
    }

    public void updateWorker(Worker worker) {
        workerRepository.save(worker);
    }

    public void deleteWorker(Worker worker) {
        workerRepository.delete(worker);
    }
}
