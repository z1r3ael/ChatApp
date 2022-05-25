package com.example.android.week4appwb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.week4appwb.data.ChatDiffUtilCallback;
import com.example.android.week4appwb.data.ChatItem;
import com.example.android.week4appwb.data.ChatItemAdapter;
import com.example.android.week4appwb.data.Repository;
import com.example.android.week4appwb.data.Utils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ChatItem> chatItems;
    private ProgressBar loadingProgressBar;
    private NestedScrollView nestedScrollView;
    private RecyclerView chatRecycleView;
    private ChatItemAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipeToRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionbarColor();

        chatItems = Repository.getChats();

        viewsInitialization();

        adapter = new ChatItemAdapter(chatItems, this);
        layoutManager = new LinearLayoutManager(this);
        chatRecycleView.setAdapter(adapter);
        chatRecycleView.setLayoutManager(layoutManager);

        scrollChangeListener();
        swipeToRefreshListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chats_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.deprecatedList) {
            startDeprecatedListActivity();
        }
        return true;
    }

    private void startDeprecatedListActivity() {
        new AlertDialog.Builder(this)
                .setTitle("Устаревший список")
                .setMessage("Вы уверены, что хотите перейти на устаревший спсок?")
                .setPositiveButton("Перейти", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(MainActivity.this, DeprecatedListActivity.class));
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(R.drawable.warning)
                .show();
    }

    private void scrollChangeListener() {
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    loadingProgressBar.setVisibility(View.VISIBLE);
                    ArrayList<ChatItem> newChatList = new ArrayList<>(chatItems);
                    newChatList.addAll(Repository.getChats());
                    adapter.addChatList(newChatList);
                }
            }
        });
    }

    private void swipeToRefreshListener() {
        swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ArrayList<ChatItem> newChatList = new ArrayList<>();
                int counter = chatItems.size() / 10;
                for (int i = 0; i < counter; i++) {
                    newChatList.addAll(Repository.getChats());
                }
                adapter.addChatList(newChatList);
                swipeToRefresh.setRefreshing(false);
            }
        });
    }

    private void viewsInitialization() {
        loadingProgressBar = findViewById(R.id.loadingProgressBar);
        nestedScrollView = findViewById(R.id.nestedScrollView);
        swipeToRefresh = findViewById(R.id.swipeToRefresh);
        chatRecycleView = findViewById(R.id.chatRecycleView);
    }

    private void setActionbarColor() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.app_bar_color)));
        }
    }
}