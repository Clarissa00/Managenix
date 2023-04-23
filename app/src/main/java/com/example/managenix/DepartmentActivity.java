package com.example.managenix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DepartmentActivity extends AppCompatActivity {

    private Button comps,it,extc,mech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_department );

//        Toolbar toolbar = findViewById(R.id.toolbar12);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("");

        comps=findViewById ( R.id.Comps1 );
        it=findViewById ( R.id.IT );
        extc=findViewById ( R.id.Electronics );
        mech=findViewById ( R.id.Mechanical );

        comps.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (DepartmentActivity.this,StudentprofileActivity.class);
                startActivity ( intent );
            }
        } );

        it.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (DepartmentActivity.this,StudentprofileActivity.class);
                startActivity ( intent );
            }
        } );

        extc.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (DepartmentActivity.this, StudentprofileActivity.class);
                startActivity ( intent );
            }
        } );

        mech.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (DepartmentActivity.this,StudentprofileActivity.class);
                startActivity ( intent );
            }
        } );
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.menu_option,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.profile:
//                Toast.makeText(this, "Clicked Profile", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.dashboard:
//                Toast.makeText(this, "Clicked dashboard", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent (DepartmentActivity.this,Dashboard.class);
//                startActivity ( intent );
//                finish();
//                break;
//
//            case R.id.logout1:
//                Toast.makeText(this, "Clicked logout", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent (DepartmentActivity.this,MainActivity.class);
//                startActivity ( i );
//                finish();
//                break;
//
//        }
//        return super.onOptionsItemSelected(item);
//    }
}