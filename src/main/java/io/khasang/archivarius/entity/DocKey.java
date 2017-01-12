package io.khasang.archivarius.entity;

public enum DocKey {
    INBOX("входящий"),
    OUTBOX("исходящий"),
    INTERNAL("внутренний");

    private String name;

    DocKey(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
