package com.example.android.week4appwb.data;

public class MessageItem {

    private String message;
    private String messageSendingTime;
    private boolean isMine;

    public MessageItem(String message, String messageSendingTime) {
        this.message = message;
        this.messageSendingTime = messageSendingTime;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageSendingTime() {
        return messageSendingTime;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }
}
