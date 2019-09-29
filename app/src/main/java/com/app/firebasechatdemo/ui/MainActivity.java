package com.app.firebasechatdemo.ui;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.app.firebasechatdemo.R;
import com.app.firebasechatdemo.data.model.ChatModel;
import com.app.firebasechatdemo.ui.adapter.MainAdapter;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ChildEventListener, ValueEventListener {

    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;

    @BindView(R.id.editText)
    public EditText etData;

    private List<ChatModel> items;
    private Map<String, ChatModel> map;
    private MainAdapter adapter;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private final String TAG = "demoChat";
    private int pos = 001;

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        items = new ArrayList<>();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("message");

        initAdapter();

        databaseReference.addChildEventListener(this);
        databaseReference.addValueEventListener(this);

    }

    private void initAdapter() {
        adapter = new MainAdapter(this, items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.iv_send)
    public void onClickSend() {
        if (etData.getText().toString().isEmpty()) {
            return;
        }
        map = new HashMap<>();
        ChatModel model = new ChatModel();
        model.setId("006");
        model.setDate("10/09/2019");
        model.setChatId("chat" + pos);
        model.setText(etData.getText().toString());
        databaseReference.child("manishToNitish").child("chat" + pos).setValue(model);
//        databaseReference.child("nitishToManish").child("chat" + pos).setValue(model);
        ++pos;
        etData.setText(null);
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        Log.v(TAG, "Data is: " + dataSnapshot);
        items.clear();
        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
            for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                ChatModel model = dataSnapshot2.getValue(ChatModel.class);
                items.add(model);
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        Log.v(TAG, "Error is: " + databaseError.getMessage());
    }
}
