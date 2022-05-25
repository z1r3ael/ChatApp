package com.example.android.week4appwb.data;

public class ChatItem {
    private int id;
    private int avatarImage;
    private String senderName;
    private String messagePreview;
    private String messageSendingTime;
    private int unreadMessageCount;

    public ChatItem(int id ,int avatarImage, String senderName, String messagePreview, String messageSendingTime, int unreadMessageTime) {
        this.id = id;
        this.avatarImage = avatarImage;
        this.senderName = senderName;
        this.messagePreview = messagePreview;
        this.messageSendingTime = messageSendingTime;
        this.unreadMessageCount = unreadMessageTime;
    }

    public void setAvatarImage(int avatarImage) {
        this.avatarImage = avatarImage;
    }

    public void setMessagePreview(String messagePreview) {
        this.messagePreview = messagePreview;
    }

    public void setMessageSendingTime(String messageSendingTime) {
        this.messageSendingTime = messageSendingTime;
    }

    public void setUnreadMessageCount(int unreadMessageCount) {
        this.unreadMessageCount = unreadMessageCount;
    }

    public int getId() {
        return id;
    }

    public int getAvatarImage() {
        return avatarImage;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getMessagePreview() {
        return messagePreview;
    }

    public String getMessageSendingTime() {
        return messageSendingTime;
    }

    public Integer getUnreadMessageCount() {
        return unreadMessageCount;
    }

    @Override
    public String toString() {
        return "ChatItem{" +
                "id=" + id +
                ", avatarImage=" + avatarImage +
                ", senderName='" + senderName + '\'' +
                ", messagePreview='" + messagePreview + '\'' +
                ", messageSendingTime='" + messageSendingTime + '\'' +
                ", unreadMessageCount=" + unreadMessageCount +
                '}';
    }
}
