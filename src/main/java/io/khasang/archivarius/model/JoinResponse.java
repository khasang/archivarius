package io.khasang.archivarius.model;

import java.io.Serializable;

public class JoinResponse {

    private String workerName;
    private String companyName;

    public JoinResponse() {
    }

    public JoinResponse(String workerName, String companyName) {
        this.workerName = workerName;
        this.companyName = companyName;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
