package com.example.android.week4appwb.data;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.week4appwb.ChatActivity;
import com.example.android.week4appwb.R;

import java.util.ArrayList;
import java.util.List;

public class ChatItemAdapter extends RecyclerView.Adapter<ChatItemAdapter.ChatItemViewHolder> {

    ArrayList<ChatItem> chatItems;
    Context context;

    public ChatItemAdapter(ArrayList<ChatItem> chatItems, Context context) {
        this.chatItems = chatItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatItemAdapter.ChatItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        return new ChatItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatItemAdapter.ChatItemViewHolder holder, int position) {
        ChatItem chatItem = chatItems.get(position);
        holder.avatarImage.setImageResource(chatItem.getAvatarImage());
        holder.senderName.setText(chatItem.getSenderName());
        holder.messagePreview.setText(chatItem.getMessagePreview());
        holder.messageSendingTime.setText(chatItem.getMessageSendingTime());
        holder.unreadMessageCount.setText(String.valueOf(chatItem.getUnreadMessageCount()));
    }

    @Override
    public int getItemCount() {
        return chatItems.size();
    }

    public class ChatItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView avatarImage;
        public TextView senderName;
        public TextView messagePreview;
        public TextView messageSendingTime;
        public TextView unreadMessageCount;

        public ChatItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            avatarImage = itemView.findViewById(R.id.avatarImage);
            senderName = itemView.findViewById(R.id.senderName);
            messagePreview = itemView.findViewById(R.id.messagePreview);
            messageSendingTime = itemView.findViewById(R.id.messageSendingTime);
            unreadMessageCount = itemView.findViewById(R.id.unreadMessageCount);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            ChatItem chatItem = chatItems.get(position);
            Intent intent = new Intent(context, ChatActivity.class);
            intent.putExtra("avatarImage", chatItem.getAvatarImage());
            intent.putExtra("senderName", chatItem.getSenderName());
            intent.putExtra("messagePreview", chatItem.getMessagePreview());
            intent.putExtra("messageSendingTime", chatItem.getMessageSendingTime());
            intent.putExtra("unreadMessageCount", chatItem.getUnreadMessageCount());
            context.startActivity(intent);
        }
    }

    public void addChatList(ArrayList<ChatItem> newChatList) {
        ChatDiffUtilCallback chatDiffUtilCallback = new ChatDiffUtilCallback(chatItems, newChatList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(chatDiffUtilCallback);
        chatItems.clear();
        chatItems.addAll(newChatList);
        diffResult.dispatchUpdatesTo(this);
    }
}
