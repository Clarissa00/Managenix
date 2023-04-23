package com.example.managenix;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class fee_entry extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://managenix2-default-rtdb.firebaseio.com/");
    private EditText studname, rollno, branch, year, studid, amount, status;
    private Button submit, next;
    private TextView dob;
    int year2;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_fee_entry );

        Toolbar toolbar = findViewById(R.id.toolbar17);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        dob = findViewById(R.id.date2);
        Calendar calendar = Calendar.getInstance();
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year2 = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(fee_entry.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year2, int month, int dayOfMonth) {
                        month = month+1;
                        dob.setText(dayOfMonth+"/"+month+"/"+year2);
//                        dob.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                    }
                }, year2,month, day);
                datePickerDialog.show();
            }
        });

        studname = findViewById(R.id.FESNAME);
        rollno = findViewById(R.id.FESROLLNO);
        branch = findViewById(R.id.FESBRANCH);
        year = findViewById(R.id.FESYEAR);
        studid = findViewById(R.id.FESSTUDID);
        amount = findViewById(R.id.FESAMOUNT);
        status = findViewById(R.id.FESSTATUS);
        next = findViewById(R.id.FEENEXT);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fee_entry.this,fees_view.class);
                startActivity(intent);
                finish();
            }
        });
        submit = findViewById ( R.id.FESSUBMIT );

        submit.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                final String studnametxt = studname.getText().toString();
                final String rollnotxt = rollno.getText().toString();
                final String branchtxt = branch.getText().toString();
                final String yeartxt = year.getText().toString();
                final String studidtxt = studid.getText().toString();
                final String amounttxt = amount.getText().toString();
                final String statustxt = status.getText().toString();
                final String dobtxt = dob.getText().toString();

                boolean isBranchValid = branchtxt.matches("[a-zA-Z]+");
                boolean isStudNameValid = studnametxt.matches("[a-zA-Z]+");
                boolean isRollNoValid = rollnotxt.matches("[0-9]+");
                boolean isYearValid = yeartxt.matches("[a-zA-Z]+");
                boolean isStudIdValid = studidtxt.matches("[0-9]+");
                boolean isAmountValid = amounttxt.matches("[0-9]+");
                boolean isStatusValid = statustxt.matches("[a-zA-Z]+");


                if(studnametxt.isEmpty() || rollnotxt.isEmpty() || branchtxt.isEmpty() || yeartxt.isEmpty() || studidtxt.isEmpty() || amounttxt.isEmpty() || statustxt.isEmpty() || dobtxt.isEmpty()){
                    Toast.makeText(fee_entry.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                }
                else if (!isStudNameValid) {
                    Toast.makeText(fee_entry.this, "First name should contain only alphabets", Toast.LENGTH_SHORT).show();
                }
                else if (!isBranchValid) {
                    Toast.makeText(fee_entry.this, "Branch should contain only alphabets", Toast.LENGTH_SHORT).show();
                }
                else if (!isRollNoValid) {
                    Toast.makeText(fee_entry.this, "Roll No should contain only numbers", Toast.LENGTH_SHORT).show();
                }
                else if (!isYearValid) {
                    Toast.makeText(fee_entry.this, "Year should contain only alphabets (Second Year or SE)", Toast.LENGTH_SHORT).show();
                }
                else if (!isStudIdValid) {
                    Toast.makeText(fee_entry.this, "Student ID should contain only numbers", Toast.LENGTH_SHORT).show();
                }
                else if (!isAmountValid) {
                    Toast.makeText(fee_entry.this, "Amount should contain only numbers", Toast.LENGTH_SHORT).show();
                }
                else if (!isStatusValid) {
                    Toast.makeText ( fee_entry.this, "Status should contain only alphabets (Paid or Unpaid)", Toast.LENGTH_SHORT ).show ();
                }
                else{

                    databaseReference.child("Fees_details").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.hasChild(rollnotxt)){

                                Toast.makeText(fee_entry.this, "Student already exist, try using different Student ID", Toast.LENGTH_SHORT).show();
                            }
                            else{

                                databaseReference.child("Fees_details").child(rollnotxt).child("Student_Name").setValue(studnametxt);
                                databaseReference.child("Fees_details").child(rollnotxt).child("Branch").setValue(branchtxt);
                                databaseReference.child("Fees_details").child(rollnotxt).child("Year").setValue(yeartxt);
                                databaseReference.child("Fees_details").child(rollnotxt).child("Student_ID").setValue(studidtxt);
                                databaseReference.child("Fees_details").child(rollnotxt).child("Amount").setValue(amounttxt);
                                databaseReference.child("Fees_details").child(rollnotxt).child("Status").setValue(statustxt);
                                databaseReference.child("Fees_details").child(rollnotxt).child("Paid On").setValue(dobtxt);

                                Toast.makeText(fee_entry.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(fee_entry.this,fees_view.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }


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
                Intent intent = new Intent (fee_entry.this,faculty_options.class);
                startActivity ( intent );
                finish();
                break;

            case R.id.logout1:
                Toast.makeText(this, "Clicked logout", Toast.LENGTH_SHORT).show();
                Intent i = new Intent (fee_entry.this,faculty_login.class);
                startActivity ( i );
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}