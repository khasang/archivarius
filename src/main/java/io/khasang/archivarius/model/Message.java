package io.khasang.archivarius.model;

public class Message {
    private String helloMessage;

    public Message() {
    }

    public Message(String helloMessage) {
        this.helloMessage = helloMessage;
    }

    public String getHelloMessage() {
        return helloMessage;
    }

    public void setHelloMessage(String helloMessage) {
        this.helloMessage = helloMessage;
    }
}
