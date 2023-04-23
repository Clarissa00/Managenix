package com.example.managenix;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class faculty_options extends AppCompatActivity {
    private Button department,fee,placement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_faculty_options );

        Toolbar toolbar = findViewById(R.id.toolbar15);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        department=findViewById ( R.id.FODEPT );
        fee=findViewById ( R.id.FOFEE );
        fee.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (faculty_options.this,fee_entry.class);
                startActivity ( intent );
                finish ();

            }
        } );
        placement=findViewById ( R.id.FOPLACEMENT );
        placement.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (faculty_options.this,placement_view.class);
                startActivity ( intent );


            }
        } );

        department.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (faculty_options.this,faculty_dashboard.class);
                startActivity ( intent );
                finish ();
            }
        } );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_option,menu);
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
                Intent intent = new Intent (faculty_options.this,faculty_options.class);
                startActivity ( intent );
                finish();
                break;

            case R.id.logout1:
                Toast.makeText(this, "Clicked logout", Toast.LENGTH_SHORT).show();
                Intent i = new Intent (faculty_options.this,faculty_login.class);
                startActivity ( i );
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
