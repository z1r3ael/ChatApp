package com.example.android.week4appwb.data;

import android.util.Log;

import java.util.ArrayList;

public class Repository {

    public static int uniqueId = 0;

    public static ArrayList<ChatItem> getChats() {
        ArrayList<ChatItem> chatItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            uniqueId++;
            chatItems.add(new ChatItem(uniqueId, Utils.getRandomAvatarImage(), Utils.getRandomSenderName(),
                    Utils.getRandomMessagePreview(), Utils.getRandomMessageSendingTime(),
                    Utils.getRandomUnreadMessageCount()));
        }
        return chatItems;
    }

    public static ArrayList<MessageItem> getMessages() {
        ArrayList<MessageItem> messageItems = new ArrayList<>();
        messageItems.add(new MessageItem(Utils.getRandomMessagePreview(), Utils.getRandomMessageSendingTime()));
        return messageItems;
    }
}
