package com.example.android.week4appwb.data;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;

public class ChatDiffUtilCallback extends DiffUtil.Callback {

    private final ArrayList<ChatItem> oldList;
    private final ArrayList<ChatItem> newList;

    public ChatDiffUtilCallback(ArrayList<ChatItem> oldList, ArrayList<ChatItem> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        ChatItem oldChatItem = oldList.get(oldItemPosition);
        ChatItem newChatItem = newList.get(newItemPosition);
        return oldChatItem.getId() == newChatItem.getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        ChatItem oldChatItem = oldList.get(oldItemPosition);
        ChatItem newChatItem = newList.get(newItemPosition);

        return oldChatItem.getAvatarImage() == newChatItem.getAvatarImage() &&
                oldChatItem.getSenderName().equals(newChatItem.getSenderName()) &&
                oldChatItem.getMessagePreview().equals(newChatItem.getMessagePreview()) &&
                oldChatItem.getMessageSendingTime().equals(newChatItem.getMessageSendingTime()) &&
                oldChatItem.getUnreadMessageCount() == newChatItem.getUnreadMessageCount();
    }
}
