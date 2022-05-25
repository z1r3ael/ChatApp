package com.example.android.week4appwb;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.week4appwb.data.ChatItem;
import com.example.android.week4appwb.data.Repository;

import java.util.ArrayList;

public class DeprecatedListActivity extends AppCompatActivity {

    private LinearLayout chatsLinearLayout;
    private ArrayList<ChatItem> chatItems;
    private SwipeRefreshLayout swipeToRefresh;
    private NestedScrollView nestedScrollView;
    private ProgressBar loadingProgressBar;
    private int chatsAddedCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deprecated_list);
        setActionbarColor();

        viewsInitialization();
        chatItems = Repository.getChats();

        setChatsToLinearLayout();
        chatsAddedCounter++;

        scrollChangeListener();
        swipeToRefreshListener();
    }

    private void setChatsToLinearLayout() {
        for (int i = 0; i < chatItems.size(); i++){
            chatItems = Repository.getChats();
            FrameLayout frameLayout = new FrameLayout(this);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, this.getResources().getDisplayMetrics()), Gravity.CENTER);
            frameLayout.setLayoutParams(layoutParams);
            View chatView = getLayoutInflater().inflate(R.layout.chat_item, null);
            frameLayout.addView(chatView);


            ImageView avatarImage = chatView.findViewById(R.id.avatarImage);
            TextView senderName = chatView.findViewById(R.id.senderName);
            TextView messagePreview = chatView.findViewById(R.id.messagePreview);
            TextView messageSendingTime = chatView.findViewById(R.id.messageSendingTime);
            TextView unreadMessageCount = chatView.findViewById(R.id.unreadMessageCount);

            ChatItem currentChatItem = chatItems.get(i);

            avatarImage.setImageResource(currentChatItem.getAvatarImage());
            senderName.setText(currentChatItem.getSenderName());
            messagePreview.setText(currentChatItem.getMessagePreview());
            messageSendingTime.setText(currentChatItem.getMessageSendingTime());
            unreadMessageCount.setText(String.valueOf(currentChatItem.getUnreadMessageCount()));

            chatsLinearLayout.addView(frameLayout);
        }
    }

    private void scrollChangeListener() {
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    loadingProgressBar.setVisibility(View.VISIBLE);
                    setChatsToLinearLayout();
                    chatsAddedCounter++;
                }
            }
        });
    }

    private void swipeToRefreshListener() {
        swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                chatsLinearLayout.removeAllViews();
                for (int i = 0; i < chatsAddedCounter; i++){
                    setChatsToLinearLayout();
                }
                swipeToRefresh.setRefreshing(false);
            }
        });
    }

    private void viewsInitialization() {
        chatsLinearLayout = findViewById(R.id.chatsLinearLayout);
        nestedScrollView = findViewById(R.id.nestedScrollView);
        swipeToRefresh = findViewById(R.id.swipeToRefresh);
        loadingProgressBar = findViewById(R.id.loadingProgressBar);
    }

    private void setActionbarColor() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.app_bar_color)));
        }
    }
}