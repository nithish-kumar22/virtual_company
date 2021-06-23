package com.example.virtualcompany;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private Context context;
    private List<Object> userList;
    private List<Object> ids;

    public UserListAdapter(Context context,List<Object> userList,List<Object> ids)
    {
        this.context = context;
        this.userList = userList;
        this.ids = ids;
        //this.message = message;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_users_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Object user_name = userList.get(position);
        holder.userName.setText(user_name.toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Chatlayout.class);
                intent.putExtra("userName",userList.get(position).toString());
                intent.putExtra("userId",ids.get(position).toString());
                context.startActivity(intent);
            }
        });
        //holder.message.setText(message[position]);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView userName;
        public TextView message;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.user);
            message = itemView.findViewById(R.id.msg);

        }
    }

}
