package com.example.managenix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class faculty_register extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://managenix2-default-rtdb.firebaseio.com/");

    private EditText firstname, lastname, facultyid, username, password, confirmpass;
    private Button register, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_register);

        register = findViewById(R.id.FACREGISTER);
        firstname = findViewById(R.id.FACFIRSTNAME);
        lastname = findViewById(R.id.FACLASTNAME);
        facultyid = findViewById(R.id.FACID);
        username = findViewById(R.id.FACUSERNAME);
        password = findViewById(R.id.FACPASSWORD);
        confirmpass = findViewById(R.id.FACCPASSWORD);
        back = findViewById(R.id.FACBACK);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (faculty_register.this,faculty_login.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String firstnametxt = firstname.getText().toString();
                final String lastnametxt = lastname.getText().toString();
                final String facultyidtxt = facultyid.getText().toString();
                final String usernametxt = username.getText().toString();
                final String passwordtxt = password.getText().toString();
                final String confirmpasstxt = confirmpass.getText().toString();

                // Define regular expression patterns for validation
                String uppercasePattern = "(.*[A-Z].*)";
                String lowercasePattern = "(.*[a-z].*)";
                String digitPattern = "(.*[0-9].*)";
                String specialCharPattern = "(.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*)";

                // Define boolean flags for validation
                boolean isFirstNameValid = firstnametxt.matches("[a-zA-Z]+");
                boolean isLastNameValid = lastnametxt.matches("[a-zA-Z]+");
                boolean isFacultyIdValid = facultyidtxt.matches("[0-9]+");
                boolean isPasswordValid = passwordtxt.matches(uppercasePattern) &&
                        passwordtxt.matches(lowercasePattern) &&
                        passwordtxt.matches(digitPattern) &&
                        passwordtxt.matches(specialCharPattern);

                if(firstnametxt.isEmpty() || lastnametxt.isEmpty() || facultyidtxt.isEmpty() || usernametxt.isEmpty() || passwordtxt.isEmpty() || confirmpasstxt.isEmpty()){
                    Toast.makeText(faculty_register.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                }
                // Check if the first name is valid
                else if (!isFirstNameValid) {
                    Toast.makeText(faculty_register.this, "First name should contain only alphabets", Toast.LENGTH_SHORT).show();
                }
                // Check if the last name is valid
                else if (!isLastNameValid) {
                    Toast.makeText(faculty_register.this, "Last name should contain only alphabets", Toast.LENGTH_SHORT).show();
                }
                // Check if the student ID is valid
                else if (!isFacultyIdValid) {
                    Toast.makeText(faculty_register.this, "Faculty ID should contain only numbers", Toast.LENGTH_SHORT).show();
                }
                // Check if the password is valid
                else if (!isPasswordValid) {
                    Toast.makeText ( faculty_register.this, "Password should contain at least one uppercase letter, one lowercase letter, one digit, and one special character", Toast.LENGTH_LONG ).show ();
                }
                else if(passwordtxt.equals(confirmpasstxt)){
                    databaseReference.child("faculty").addListenerForSingleValueEvent(new ValueEventListener () {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            //check if user already registered or not
                            if(snapshot.hasChild(usernametxt)){
                                Toast.makeText(faculty_register.this, "Username already taken", Toast.LENGTH_SHORT).show();
                            }
                            else{

                                //input data into the database
                                databaseReference.child("faculty").child(usernametxt).child("firstname").setValue(firstnametxt);
                                databaseReference.child("faculty").child(usernametxt).child("lastname").setValue(lastnametxt);
                                databaseReference.child("faculty").child(usernametxt).child("facultyid").setValue(facultyidtxt);
                                databaseReference.child("faculty").child(usernametxt).child("password").setValue(passwordtxt);
                                databaseReference.child("faculty").child(usernametxt).child("confirmpass").setValue(confirmpasstxt);

                                Toast.makeText(faculty_register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(faculty_register.this,faculty_login.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else{
                    Toast.makeText(faculty_register.this, "Password and confirm password does not match", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}

