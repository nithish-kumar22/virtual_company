package com.example.virtualcompany;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

public class Signup extends AppCompatActivity {

    TextView textView;
    private EditText userName;
    private EditText Email;
    private EditText Password;
    private Button button;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        textView = findViewById(R.id.account_exists);
        userName = findViewById(R.id.user_name);
        Email = findViewById(R.id.signup_email);
        Password = findViewById(R.id.signup_passw);
        button = findViewById(R.id.signup_btn);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this,Signin.class);
                startActivity(intent);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation =  AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale_btn);
                button.startAnimation(animation);
                final String user_name = userName.getText().toString();
                final String email = Email.getText().toString();
                String password = Password.getText().toString();
                if(user_name.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter valid user name",Toast.LENGTH_SHORT).show();
                }
                if(email.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter valid email",Toast.LENGTH_SHORT).show();
                }
                if(password.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter valid password",Toast.LENGTH_SHORT).show();
                }
                if(!user_name.equals("") && !email.equals("") && !password.equals(""))
                {

                    mAuth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        user = FirebaseAuth.getInstance().getCurrentUser();
                                        ref = database.getReference().child("Users").child(user.getUid());

                                        HashMap<String,String> hashMap = new HashMap<>();
                                        hashMap.put("id",user.getUid());
                                        hashMap.put("userName",user_name);
                                        hashMap.put("email",email);

                                        ref.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Intent intent = new Intent(Signup.this,MainActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        });
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), ""+task.getException(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

    }

}