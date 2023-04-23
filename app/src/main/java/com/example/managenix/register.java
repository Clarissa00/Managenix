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

public class register extends AppCompatActivity {

    private EditText username,password,confirmpass,emailcollage,emailpersonal,firstname,surname, studid;
    private Button submit, back;
    private TextView faculty;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://managenix2-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_register );

        username = findViewById(R.id.RUsername);
        password = findViewById(R.id.RPassword);
        confirmpass = findViewById(R.id.RCPassword);
        emailcollage = findViewById ( R.id.REmailidc );
        emailpersonal = findViewById ( R.id.REmailidp );
        firstname = findViewById(R.id.RFirstname);
        surname = findViewById(R.id.RSurname);
        studid = findViewById(R.id.RStudid);
        submit = findViewById ( R.id.submit );

        back = findViewById(R.id.Rback);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        faculty = findViewById(R.id.REGFACULTY);

        faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this,faculty_register.class);
                startActivity(intent);
                finish();
            }
        });

        submit.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                final String firstnametxt = firstname.getText().toString();
                final String surnametxt = surname.getText().toString();
                final String usernametxt = username.getText().toString();
                final String passwordtxt = password.getText().toString();
                final String confirmpasstxt = confirmpass.getText().toString();
                final String emailcollagetxt = emailcollage.getText().toString();
                final String emailpersonaltxt = emailpersonal.getText().toString();
                final String studidtxt = studid.getText().toString();

                // Define regular expression patterns for validation
                String emailPattern = "[a-zA-Z0-9._-]+@dbit.in";
                String emailPattern2 = "[a-zA-Z0-9._-]+@gmail.com";
                String uppercasePattern = "(.*[A-Z].*)";
                String lowercasePattern = "(.*[a-z].*)";
                String digitPattern = "(.*[0-9].*)";
                String specialCharPattern = "(.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*)";

                // Define boolean flags for validation
                boolean isFirstNameValid = firstnametxt.matches("[a-zA-Z]+");
                boolean isLastNameValid = surnametxt.matches("[a-zA-Z]+");
                boolean isStudIdValid = studidtxt.matches("[0-9]+");
                boolean isEmailValid = emailcollagetxt.matches(emailPattern);
                boolean isEmailValid2 = emailpersonaltxt.matches(emailPattern2);
                boolean isPasswordValid = passwordtxt.matches(uppercasePattern) &&
                        passwordtxt.matches(lowercasePattern) &&
                        passwordtxt.matches(digitPattern) &&
                        passwordtxt.matches(specialCharPattern);

                if(firstnametxt.isEmpty() || surnametxt.isEmpty() || usernametxt.isEmpty() || passwordtxt.isEmpty() || confirmpasstxt.isEmpty() || emailcollagetxt.isEmpty () || emailpersonaltxt.isEmpty () || studidtxt.isEmpty()){
                    Toast.makeText(register.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                }
                else if(passwordtxt.equals ( confirmpasstxt )){
                    databaseReference.child ( "users" ).addListenerForSingleValueEvent ( new ValueEventListener () {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(usernametxt)){
                                Toast.makeText(register.this, "Username already taken", Toast.LENGTH_SHORT).show();
                            }
                            // Check if the first name is valid
                            else if (!isFirstNameValid) {
                                Toast.makeText(register.this, "First name should contain only alphabets", Toast.LENGTH_SHORT).show();
                            }
                            // Check if the last name is valid
                            else if (!isLastNameValid) {
                                Toast.makeText(register.this, "Surname should contain only alphabets", Toast.LENGTH_SHORT).show();
                            }
                            // Check if the student ID is valid
                            else if (!isStudIdValid) {
                                Toast.makeText(register.this, "Student ID should contain only numbers", Toast.LENGTH_SHORT).show();
                            }
                            // Check if the password is valid
                            else if (!isPasswordValid) {
                                Toast.makeText(register.this, "Password should contain at least one uppercase letter, one lowercase letter, one digit, and one special character", Toast.LENGTH_LONG).show();
                            }
                            else if(!isEmailValid){
                                Toast.makeText(register.this, "Please enter a valid college email", Toast.LENGTH_LONG).show();
                            }
                            else if(!isEmailValid2) {
                                Toast.makeText ( register.this, "Please enter a valid personal email", Toast.LENGTH_LONG ).show ();
                            }
                            else{
                                databaseReference.child("users").child(usernametxt).child("firstname").setValue(firstnametxt);
                                databaseReference.child("users").child(usernametxt).child("lastname").setValue(surnametxt);
                                databaseReference.child("users").child(usernametxt).child("password").setValue(passwordtxt);
                                databaseReference.child("users").child(usernametxt).child("confirmpass").setValue(confirmpasstxt);
                                databaseReference.child("users").child(usernametxt).child("emailcollage").setValue(emailcollagetxt);
                                databaseReference.child("users").child(usernametxt).child("emailpersonal").setValue(emailpersonaltxt);
                                databaseReference.child("users").child(usernametxt).child("studentid").setValue(studidtxt);

                                Toast.makeText(register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent (register.this,MainActivity.class);
                                startActivity ( intent );
                                finish ();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    } );

                }
                else{
                    Toast.makeText(register.this, "Password and confirm password does not match", Toast.LENGTH_SHORT).show();
                }

            }
        } );

    }
}