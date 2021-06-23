package com.example.virtualcompany;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Usersearch extends Fragment {

   /* ArrayList<String> globalUsersList;
    public ArrayAdapter<String> globalUserArrayAdapter;
    ListView globalUserListView;
    Context context;
    String text;
    //UpdateListCallBack updateListCallBack;
    public Usersearch(Context context) {
        // Required empty public constructor
        this.context = context;
        //this.globalUsersList = globalUsersList;
    }

    public Usersearch(String text)
    {
        this.text = text;
    }


    public interface UpdateListCallBack
    {
        String onListSend();
    }
 */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_usersearch, container, false);

   /*     //arrayAdapter.getFilter().filter(userName);
        globalUserListView = (ListView) v.findViewById(R.id.global_users_search);
        globalUsersList = new ArrayList<>();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    globalUsersList.add(dataSnapshot.getValue().toString());
                    Toast.makeText(context, ""+dataSnapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        globalUserArrayAdapter = new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,globalUsersList);
        globalUserListView.setAdapter(globalUserArrayAdapter);

        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();



        if(getArguments().getString("newText").length() != 0)
        {
            globalUserArrayAdapter.getFilter().filter(getArguments().getString("newText"));
        }

 */

        return v;
    }
}