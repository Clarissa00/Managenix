package com.example.managenix;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.util.Calendar;

public class AcademicdetailsActivity extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://managenix2-default-rtdb.firebaseio.com/");

    private Button submit;
    private EditText branch, sem, rollno, ssc, hsce, hscc, hscp, hscm, hsc, cet, jee;
    private TextView doa;
    int year;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academicdetails);

//        Toolbar toolbar = findViewById(R.id.toolbar14);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("");

        branch = findViewById(R.id.ABranch);
        sem = findViewById(R.id.ASemester);
        rollno = findViewById(R.id.ARollNo);
        ssc = findViewById(R.id.ASSC);
        hsce = findViewById(R.id.AHSCE);
        hscc = findViewById(R.id.AHSCC);
        hscp = findViewById(R.id.AHSCP);
        hscm = findViewById(R.id.AHSCM);
        hsc = findViewById(R.id.Ahscpercentage);
        cet = findViewById(R.id.Amhtcetpercentile);
        jee = findViewById(R.id.Ajeepercentile);
        doa = findViewById(R.id.ADOA);
        Calendar calendar = Calendar.getInstance();
        doa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AcademicdetailsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        doa.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                }, year,month, day);
                datePickerDialog.show();
            }
        });
        submit = findViewById(R.id.ASUBMIT);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String branchtxt = branch.getText().toString();
                final String semtxt = sem.getText().toString();
                final String rollnotxt = rollno.getText().toString();
                final String ssctxt = ssc.getText().toString();
                final String hscetxt = hsce.getText().toString();
                final String hscctxt = hscc.getText().toString();
                final String hscptxt = hscp.getText().toString();
                final String hscmtxt = hscm.getText().toString();
                final String hsctxt = hsc.getText().toString();
                final String cettxt = cet.getText().toString();
                final String jeetxt = jee.getText().toString();
                final String doatxt = doa.getText().toString();

                boolean isBranchValid = branchtxt.matches("[a-zA-Z]+");
                boolean isSemValid = semtxt.matches("[a-zA-Z0-9]+");
                boolean isRollNoValid = rollnotxt.matches("[0-9]+");

                if(branchtxt.isEmpty() || semtxt.isEmpty() || rollnotxt.isEmpty() || ssctxt.isEmpty() || hscetxt.isEmpty() || hscctxt.isEmpty() || hscptxt.isEmpty() || hscmtxt.isEmpty() || hsctxt.isEmpty() || cettxt.isEmpty() || jeetxt.isEmpty() || doatxt.isEmpty()){
                    Toast.makeText(AcademicdetailsActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                }
                else if (!isBranchValid) {
                    Toast.makeText(AcademicdetailsActivity.this, "Branch should contain only alphabets", Toast.LENGTH_SHORT).show();
                }
                else if (!isSemValid) {
                    Toast.makeText(AcademicdetailsActivity.this, "Semester format is Sem 03", Toast.LENGTH_SHORT).show();
                }
                else if (!isRollNoValid) {
                    Toast.makeText ( AcademicdetailsActivity.this, "Roll No should contain only numbers", Toast.LENGTH_SHORT ).show ();
                }
                else{
                    databaseReference.child("academic_details").addListenerForSingleValueEvent(new ValueEventListener () {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(rollnotxt)){
                                Toast.makeText(AcademicdetailsActivity.this, "Student already exist, try using correct rollno", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                databaseReference.child("academic_details").child(rollnotxt).child("branch").setValue(branchtxt);
                                databaseReference.child("academic_details").child(rollnotxt).child("semester").setValue(semtxt);
                                databaseReference.child("academic_details").child(rollnotxt).child("ssc %").setValue(ssctxt);
                                databaseReference.child("academic_details").child(rollnotxt).child("hsc marks in ENG").setValue(hscetxt);
                                databaseReference.child("academic_details").child(rollnotxt).child("hsc marks in Chem").setValue(hscctxt);
                                databaseReference.child("academic_details").child(rollnotxt).child("hsc marks in Phy").setValue(hscptxt);
                                databaseReference.child("academic_details").child(rollnotxt).child("hsc marks in Maths").setValue(hscmtxt);
                                databaseReference.child("academic_details").child(rollnotxt).child("hsc %").setValue(hsctxt);
                                databaseReference.child("academic_details").child(rollnotxt).child("MHT-CET %ile").setValue(cettxt);
                                databaseReference.child("academic_details").child(rollnotxt).child("JEE %ile").setValue(jeetxt);
                                databaseReference.child("academic_details").child(rollnotxt).child("doa").setValue(doatxt);

                                Toast.makeText(AcademicdetailsActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(AcademicdetailsActivity.this,logout.class);
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
        });
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
//                Intent intent = new Intent (AcademicdetailsActivity.this,Dashboard.class);
//                startActivity ( intent );
//                finish();
//                break;
//
//            case R.id.logout1:
//                Toast.makeText(this, "Clicked logout", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent (AcademicdetailsActivity.this,MainActivity.class);
//                startActivity ( i );
//                finish();
//                break;
//
//        }
//        return super.onOptionsItemSelected(item);
//    }
}