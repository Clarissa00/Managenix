package com.example.managenix;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        // Get the current user ID from Firebase Authentication
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();

            // Get a reference to your Firebase database
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

            // Fetch user details from Firebase database
            databaseReference.child("users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // Fetch user details from dataSnapshot and display in XML views
                    String userName = dataSnapshot.child("firstname").getValue(String.class);
                    String userEmail = dataSnapshot.child("emailcollege").getValue(String.class);

                    // Example: Set fetched user details to TextViews in your XML file
                    TextView textViewName = findViewById(R.id.tvUserName);
                    TextView textViewEmail = findViewById(R.id.tvUserEmail);
                    textViewName.setText(userName);
                    textViewEmail.setText(userEmail);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle error if any
                }
            });
        } else {
            // Handle the case where the user is not logged in
        }
    }
}
