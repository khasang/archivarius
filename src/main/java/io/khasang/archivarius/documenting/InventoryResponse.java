package io.khasang.archivarius.documenting;

import java.io.Serializable;

public class InventoryResponse implements Serializable {
    private String documentId;
    private int returnCode;
    private String comment;

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "InventoryResponse{" +
                "documentId='" + documentId + '\'' +
                ", returnCode=" + returnCode +
                ", comment='" + comment + '\'' +
                '}';
    }
}
