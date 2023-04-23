package com.example.managenix;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class fees_view extends AppCompatActivity {
    private RecyclerView recyclerView;
    MyAdaptorFee myAdaptorFee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees_view);

        Toolbar toolbar = findViewById(R.id.toolbar16);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        recyclerView = findViewById(R.id.FEERECVIEW);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<fee_model> options =
                new FirebaseRecyclerOptions.Builder<fee_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Fees_details"),fee_model.class)
                        .build();

        myAdaptorFee = new MyAdaptorFee(options);
        recyclerView.setAdapter(myAdaptorFee);

    }


    @Override
    protected void onStart() {
        super.onStart();
        myAdaptorFee.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myAdaptorFee.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_mix,menu);

        MenuItem item = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String newText) {
                mysearch(newText);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mysearch(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                Toast.makeText(this, "Clicked Profile", Toast.LENGTH_SHORT).show();
                break;
            case R.id.dashboard:
                Toast.makeText(this, "Clicked dashboard", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (fees_view.this,faculty_options.class);
                startActivity ( intent );
                finish();
                break;

            case R.id.logout1:
                Toast.makeText(this, "Clicked logout", Toast.LENGTH_SHORT).show();
                Intent i = new Intent (fees_view.this,faculty_login.class);
                startActivity ( i );
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void mysearch(String newText) {

        FirebaseRecyclerOptions<fee_model> options =
                new FirebaseRecyclerOptions.Builder<fee_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Fees_details").orderByChild("Student_Name").startAt(newText).endAt(newText+"\uf8ff"),fee_model.class)
                        .build();

        myAdaptorFee = new MyAdaptorFee(options);
        myAdaptorFee.startListening();
        recyclerView.setAdapter(myAdaptorFee);
    }
}