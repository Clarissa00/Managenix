package com.example.managenix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class faculty_login extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://managenix2-default-rtdb.firebaseio.com/");

    private Button register, login;
    private EditText username, password;
    private TextView student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_login);

        username = findViewById(R.id.FACULTYUSERNAME);
        password = findViewById(R.id.FACULTYPASSWORD);
        login = findViewById(R.id.FACULTYLOGIN);
        register = findViewById(R.id.FACULTYREGISTER);
        student = findViewById(R.id.LOGINSTUDENT);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(faculty_login.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(faculty_login.this,faculty_register.class);
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usernametxt = username.getText().toString();
                final String passwordtxt = password.getText().toString();
                if(usernametxt.isEmpty() || passwordtxt.isEmpty()){
                    Toast.makeText(faculty_login.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                }
                else{

                    databaseReference.child("faculty").addListenerForSingleValueEvent(new ValueEventListener () {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(usernametxt)){

                                final String getpassword = snapshot.child(usernametxt).child("password").getValue(String.class);

                                if(getpassword.equals(passwordtxt)){
                                    Toast.makeText(faculty_login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(faculty_login.this,faculty_options.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Toast.makeText(faculty_login.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(faculty_login.this, "Wrong username", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });
    }
}