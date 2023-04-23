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

public class Dashboard extends AppCompatActivity {

    private Button department,fee,placement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_dashboard );

        Toolbar toolbar = findViewById(R.id.toolbar13);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        department=findViewById ( R.id.Ddepartment2 );
//        fee=findViewById ( R.id.dfeemanagement );
        placement=findViewById ( R.id.dplacementcell );

        placement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Dashboard.this,placement_view.class);
                startActivity ( intent );

            }
        });

        department.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (Dashboard.this,DepartmentActivity.class);
                startActivity ( intent );
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
                Intent in = new Intent (Dashboard.this,profile_view.class);
                startActivity ( in );
                finish();
                break;
            case R.id.dashboard:
                Toast.makeText(this, "Clicked dashboard", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (Dashboard.this,Dashboard.class);
                startActivity ( intent );
                finish();
                break;

            case R.id.logout1:
                Toast.makeText(this, "Clicked logout", Toast.LENGTH_SHORT).show();
                Intent i = new Intent (Dashboard.this,MainActivity.class);
                startActivity ( i );
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}