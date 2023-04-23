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


public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://managenix2-default-rtdb.firebaseio.com/");

    private EditText username, password;
    private Button login, register;
    private TextView faculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        username = findViewById ( R.id.Username );
        password = findViewById ( R.id.Password );
        login = findViewById ( R.id.Login );
        register = findViewById ( R.id.Register );
        faculty = findViewById(R.id.FACULTY);

        faculty.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this,faculty_login.class);
                startActivity ( intent );
                finish();
            }
        } );


        login.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                final String usernametxt = username.getText().toString();
                final String passwordtxt = password.getText().toString();

                if(usernametxt.isEmpty() || passwordtxt.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child ( "users" ).addListenerForSingleValueEvent ( new ValueEventListener () {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(usernametxt)){

                                final String getpassword = snapshot.child(usernametxt).child("password").getValue(String.class);

                                if(getpassword.equals(passwordtxt)){
                                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this,Dashboard.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Toast.makeText(MainActivity.this, "Wrong Passwrod", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Wrong username", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    } );
                }
            }
        } );

        register.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent ( MainActivity.this, register.class );
                startActivity ( intent );
                finish();
            }
        } );

    }

}

