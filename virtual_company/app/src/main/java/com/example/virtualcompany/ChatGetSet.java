package com.example.virtualcompany;

public class ChatGetSet {

    String sender;
    String receiver;
    String message;

    public ChatGetSet(String sender,String receiver,String message)
    {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    public ChatGetSet() {

    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

}
