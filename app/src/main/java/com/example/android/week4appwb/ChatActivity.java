package com.example.android.week4appwb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.week4appwb.data.ChatItem;
import com.example.android.week4appwb.data.MessageItem;
import com.example.android.week4appwb.data.MessageItemAdapter;
import com.example.android.week4appwb.data.Repository;
import com.example.android.week4appwb.data.Utils;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private ImageView avatarPreviewImage;
    private TextView senderNamePreview;
    private androidx.appcompat.widget.Toolbar toolbar;
    private ArrayList<MessageItem> messageItems;
    private RecyclerView messageRecycleView;
    private MessageItemAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipeToRefresh;
    private int unreadMessageCount;
    private MessageItem messageFromChatList;
    private EditText messageEditText;
    private ImageButton sendMessageImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageItems = new ArrayList<>();

        viewsInitialization();
        actionBarInitialization();

        Intent intent = getIntent();
        if (intent != null) {
            senderNamePreview.setText(intent.getStringExtra("senderName"));
            avatarPreviewImage.setImageResource(intent.getIntExtra("avatarImage", R.drawable.face_0));
            unreadMessageCount = intent.getIntExtra("unreadMessageCount", -1);
            messageFromChatList = new MessageItem(intent.getStringExtra("messagePreview"), intent.getStringExtra("messageSendingTime"));
            messageItems.add(messageFromChatList);
        }

        if (unreadMessageCount != -1) {
            ArrayList<MessageItem> tempItem = new ArrayList<>();
            ArrayList<MessageItem> addedMessages = new ArrayList<>();
            for (int i = 0; i < unreadMessageCount - 1; i++) {
                addedMessages.addAll(Repository.getMessages());
            }
            messageItems.addAll(addedMessages);
            tempItem.add(messageItems.get(0));
            messageItems.set(0, messageItems.get(messageItems.size() - 1));
            messageItems.set(messageItems.size() - 1, tempItem.get(0));
        }

        adapter = new MessageItemAdapter(messageItems, this);
        layoutManager = new LinearLayoutManager(this);

        messageRecycleView.setAdapter(adapter);
        messageRecycleView.setLayoutManager(layoutManager);

        swipeToRefreshListener();
        editTextListener();
        sendMyMessageListener();
        MessageRecyclerViewListener();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void MessageRecyclerViewListener() {
        messageRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int topRowVerticalPosition =
                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                swipeToRefresh.setEnabled(topRowVerticalPosition >= 0);

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    private void sendMyMessageListener() {
        sendMessageImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageItem myMessage = new MessageItem(messageEditText.getText().toString(), Utils.getRandomMessageSendingTime());
                myMessage.setMine(true);
                adapter.addMyMessage(myMessage);
                messageRecycleView.scrollToPosition(messageItems.size() - 1);
                messageEditText.setText("");
            }
        });
    }

    private void swipeToRefreshListener() {

        swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.deleteMessages(unreadMessageCount);
                Toast.makeText(ChatActivity.this, "Пользователь удалил сообщения", Toast.LENGTH_SHORT).show();
                swipeToRefresh.setRefreshing(false);
            }
        });
    }

    private void editTextListener() {
        messageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                sendMessageImageButton.animate().alpha(1).setDuration(200);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void viewsInitialization() {
        messageRecycleView = findViewById(R.id.messageRecycleView);
        avatarPreviewImage = findViewById(R.id.avatarPreviewImage);
        swipeToRefresh = findViewById(R.id.swipeToRefresh);
        senderNamePreview = findViewById(R.id.senderNamePreview);
        toolbar = findViewById(R.id.toolbar);
        messageEditText = findViewById(R.id.messageEditText);
        sendMessageImageButton = findViewById(R.id.sendMessageImageButton);
    }

    private void actionBarInitialization() {
        setSupportActionBar(toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}