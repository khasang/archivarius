package io.khasang.archivarius.service;

import io.khasang.archivarius.dao.WorkerDAO;
import io.khasang.archivarius.entity.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("WorkerService")
@Transactional
public class WorkerService {
    @Autowired
    WorkerDAO workerDAO;

    public void addWorker(Worker worker) {
        workerDAO.addWorker(worker);
    }

    public Worker getWorkerById(int id) {
        return workerDAO.getWorkerById(id);
    }

    public List<Worker> getWorkerList() {
        return workerDAO.getWorkerList();
    }

    public void updateWorker(Worker worker) {
        workerDAO.updateWorker(worker);
    }

    public void deleteWorker(Worker worker) {
        workerDAO.deleteWorker(worker);
    }

    public void deleteWorkerById(int id) {
        workerDAO.deleteWorkerById(id);
    }
}
