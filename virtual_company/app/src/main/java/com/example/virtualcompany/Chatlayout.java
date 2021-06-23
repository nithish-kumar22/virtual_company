package com.example.virtualcompany;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chatlayout extends AppCompatActivity {

    Toolbar toolbar;
    EditText editText;
    ImageButton imageButton;
    List<String> messages;
    List<ChatGetSet> mChat;
    ChatListAdapter chatListAdapter;
    private RecyclerView recyclerView;
    FirebaseUser user;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatlayout);

        toolbar = findViewById(R.id.ChatLayoutToolbar);
        toolbar.setTitle(getIntent().getExtras().getString("userName"));
        final String id = getIntent().getExtras().getString("userId");
        editText = findViewById(R.id.enterMessage);
        imageButton = findViewById(R.id.send_button);
        user = FirebaseAuth.getInstance().getCurrentUser();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editText.getText().toString();
                if(!message.equals(""))
                {
                    sendMessage(user.getUid(),id,message);
                    editText.setText("");
                }
            }
        });

       /* DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");

        final List<Object> users = new ArrayList<>();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot != null) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Map<String, Object> td = (HashMap<String, Object>) dataSnapshot.getValue();
                        readMessage(td.get(""));


                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

       readMessage(user.getUid(),id);

    }

    private void sendMessage(String sender,String receiver, String message)
    {
        reference = FirebaseDatabase.getInstance().getReference();
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("sender",sender);
        hashMap.put("receiver",receiver);
        hashMap.put("message",message);
        reference.child("Chats").push().setValue(hashMap);
    }

    private  void readMessage(final String myId, final String userId)
    {
        messages = new ArrayList<>();
        mChat = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mChat.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    ChatGetSet chat = dataSnapshot.getValue(ChatGetSet.class);

                    if(chat.getReceiver().equals(myId) && chat.getSender().equals(userId) || chat.getReceiver().equals(userId) && chat.getSender().equals(myId))
                    {
                        mChat.add(chat);
                        //Toast.makeText(getApplicationContext(), td.get("message").toString(), Toast.LENGTH_SHORT).show();
                    }
                    chatListAdapter = new ChatListAdapter(getApplicationContext(),mChat);
                    recyclerView.setAdapter(chatListAdapter);

                   /* String hello = dataSnapshot.getValue(ChatGetSet.class).get
                    Map<String, Object> td = (HashMap<String, Object>) dataSnapshot.getValue();
                    senderId.add(td.get("sender").toString());

                    */
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}