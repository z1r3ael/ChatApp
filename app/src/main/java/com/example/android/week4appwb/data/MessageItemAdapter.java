package com.example.android.week4appwb.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.week4appwb.R;

import java.util.ArrayList;

public class MessageItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<MessageItem> messageItems;
    Context context;

    public MessageItemAdapter(ArrayList<MessageItem> messageItems, Context context) {
        this.messageItems = messageItems;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        int flag;
        MessageItem messageItem = messageItems.get(position);
        if (messageItem.isMine()) {
            flag = 0;
        } else {
            flag = 1;
        }
        return flag;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == 0) {
            viewHolder = new MyMessageItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_message_item, parent, false));
        } else if (viewType == 1) {
            viewHolder = new MessageItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false));
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageItem messageItem = messageItems.get(position);
        switch (holder.getItemViewType()) {
            case 0:
                MyMessageItemViewHolder myMessageItemViewHolder = (MyMessageItemViewHolder) holder;

                myMessageItemViewHolder.messageTextView.setText(messageItem.getMessage());
                myMessageItemViewHolder.messageSendingTimeTextView.setText(messageItem.getMessageSendingTime());
                break;
            case 1:
                MessageItemViewHolder MessageItemViewHolder = (MessageItemViewHolder) holder;
                MessageItemViewHolder.messageTextView.setText(messageItem.getMessage());
                MessageItemViewHolder.messageSendingTimeTextView.setText(messageItem.getMessageSendingTime());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return messageItems.size();
    }

    public static class MessageItemViewHolder extends RecyclerView.ViewHolder {

        private TextView messageTextView;
        private TextView messageSendingTimeTextView;

        public MessageItemViewHolder(@NonNull View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
            messageSendingTimeTextView = itemView.findViewById(R.id.messageSendingTimeTextView);
        }
    }

    public static class MyMessageItemViewHolder extends RecyclerView.ViewHolder {

        private TextView messageTextView;
        private TextView messageSendingTimeTextView;

        public MyMessageItemViewHolder(@NonNull View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
            messageSendingTimeTextView = itemView.findViewById(R.id.messageSendingTimeTextView);
        }
    }

    public void deleteMessages(int unreadCount) {
        ArrayList<MessageItem> deletedMessages = new ArrayList<>();
        ArrayList<MessageItem> deletedMessagesCounter = new ArrayList<>(messageItems.subList(0, unreadCount));
        ArrayList<MessageItem> savedMessages = new ArrayList<>(messageItems.subList(unreadCount - 1, messageItems.size()));
        for (int i = 0; i < deletedMessagesCounter.size() - 1; i++) {
            deletedMessages.add(new MessageItem("(сообщене удалено)", ""));
        }
        messageItems.clear();
        messageItems.addAll(deletedMessages);
        messageItems.addAll(savedMessages);
        notifyDataSetChanged();
    }

    public void addMyMessage(MessageItem myMessage) {
        messageItems.add(myMessage);
        notifyDataSetChanged();
    }
}
