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

public class StudentprofileActivity extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://managenix2-default-rtdb.firebaseio.com/");

    private Button submit;
    private TextView dob;
    private EditText fn, mn, ln, studid, paddress, nationality, relegion, phoneno, personalemail, blood, age, gender, fathersname, fathersphone, fathersoccup, mothersname, mothersphone, mothersoccup;
    int year;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentprofile);

//        Toolbar toolbar = findViewById(R.id.toolbar13);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("");

        fn = findViewById(R.id.Sfirstname);
        mn = findViewById(R.id.Smiddlename);
        ln = findViewById(R.id.Slastname);
        studid = findViewById(R.id.Ssid);
        paddress = findViewById(R.id.Saddress);
        nationality = findViewById(R.id.SNationality1);
        relegion = findViewById(R.id.Sreligion);
        phoneno = findViewById(R.id.SPhone);
        personalemail = findViewById(R.id.SEmailAddressP);
        blood = findViewById(R.id.Sbloodgrp);
        age = findViewById(R.id.Sage);
        gender = findViewById(R.id.Sgender);
        fathersname = findViewById(R.id.Sfathersname);
        fathersphone = findViewById(R.id.Sfatherphoneno);
        fathersoccup = findViewById(R.id.Sfathersoccupation);
        mothersname = findViewById(R.id.Smothersname);
        mothersphone = findViewById(R.id.Smothersphoneno);
        mothersoccup = findViewById(R.id.Smothersoccupation);
        dob = findViewById(R.id.SDATE);
        Calendar calendar = Calendar.getInstance();
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(StudentprofileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        dob.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                }, year,month, day);
                datePickerDialog.show();
            }
        });
        submit = findViewById(R.id.SSUBMIT);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String fntxt = fn.getText().toString();
                final String mntxt = mn.getText().toString();
                final String lntxt = ln.getText().toString();
                final String studidtxt = studid.getText().toString();
                final String paddresstxt = paddress.getText().toString();
                final String nationalitytxt = nationality.getText().toString();
                final String relegiontxt = relegion.getText().toString();
                final String phonenotxt = phoneno.getText().toString();
                final String personalemailtxt = personalemail.getText().toString();
                final String bloodtxt = blood.getText().toString();
                final String agetxt = age.getText().toString();
                final String gendertxt = gender.getText().toString();
                final String fathersnametxt = fathersname.getText().toString();
                final String fathersphonetxt = fathersphone.getText().toString();
                final String fathersoccuptxt = fathersoccup.getText().toString();
                final String mothersnametxt = mothersname.getText().toString();
                final String mothersphonetxt = mothersphone.getText().toString();
                final String mothersoccuptxt = mothersoccup.getText().toString();
                final String dobtxt = dob.getText().toString();

                String emailPattern2 = "[a-zA-Z0-9._-]+@gmail.com";

                boolean isFirstNameValid = fntxt.matches("[a-zA-Z]+");
                boolean isMiddleNameValid = mntxt.matches("[a-zA-Z]+");
                boolean isLastNameValid = lntxt.matches("[a-zA-Z]+");
                boolean isStudIdValid = studidtxt.matches("[0-9]+");
                boolean isNationalityValid = nationalitytxt.matches("[a-zA-Z]+");
                boolean isRelegionValid = relegiontxt.matches("[a-zA-Z]+");
                boolean isPhoneNumberValid = phonenotxt.matches("[0-9]{10}");
                boolean isEmailValid2 = personalemailtxt.matches(emailPattern2);
                boolean isAgeValid = agetxt.matches("[0-9]+");
                boolean isGenderValid = gendertxt.matches("[a-zA-Z]+");
                boolean isFathersNameValid = fathersnametxt.matches("[a-zA-Z]+");
                boolean isFathersPhoneNumberValid = fathersphonetxt.matches("[0-9]{10}");
                boolean isFathersOccupValid = fathersoccuptxt.matches("[a-zA-Z]+");
                boolean isMothersNameValid = mothersnametxt.matches("[a-zA-Z]+");
                boolean isMothersPhoneNumberValid = mothersphonetxt.matches("[0-9]{10}");
                boolean isMothersOccupValid = mothersoccuptxt.matches("[a-zA-Z]+");

                if(fntxt.isEmpty() || mntxt.isEmpty() || lntxt.isEmpty() || studidtxt.isEmpty() || paddresstxt.isEmpty() || nationalitytxt.isEmpty() || relegiontxt.isEmpty() || phonenotxt.isEmpty() || personalemailtxt.isEmpty() || bloodtxt.isEmpty() || agetxt.isEmpty() || gendertxt.isEmpty() || fathersnametxt.isEmpty() || fathersphonetxt.isEmpty() || fathersoccuptxt.isEmpty() || mothersnametxt.isEmpty() || mothersphonetxt.isEmpty() || mothersoccuptxt.isEmpty()){
                    Toast.makeText(StudentprofileActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                }
                else if (!isFirstNameValid) {
                    Toast.makeText(StudentprofileActivity.this, "First name should contain only alphabets", Toast.LENGTH_SHORT).show();
                }
                else if (!isMiddleNameValid) {
                    Toast.makeText(StudentprofileActivity.this, "Middle name should contain only alphabets", Toast.LENGTH_SHORT).show();
                }
                else if (!isLastNameValid) {
                    Toast.makeText(StudentprofileActivity.this, "Last name should contain only alphabets", Toast.LENGTH_SHORT).show();
                }
                else if (!isStudIdValid) {
                    Toast.makeText(StudentprofileActivity.this, "Student ID should contain only numbers", Toast.LENGTH_SHORT).show();
                }
                else if (!isNationalityValid) {
                    Toast.makeText(StudentprofileActivity.this, "Nationality should contain only alphabets", Toast.LENGTH_SHORT).show();
                }
                else if (!isRelegionValid) {
                    Toast.makeText(StudentprofileActivity.this, "Relegion should contain only alphabets", Toast.LENGTH_SHORT).show();
                }
                else if (!isPhoneNumberValid) {
                    Toast.makeText(StudentprofileActivity.this, "Phone Number should contain only numbers & 10 digits", Toast.LENGTH_SHORT).show();
                }
                else if(!isEmailValid2){
                    Toast.makeText(StudentprofileActivity.this, "Please enter a valid personal email", Toast.LENGTH_LONG).show();
                }
                else if (!isAgeValid) {
                    Toast.makeText(StudentprofileActivity.this, "Age should contain only numbers", Toast.LENGTH_SHORT).show();
                }
                else if (!isGenderValid) {
                    Toast.makeText(StudentprofileActivity.this, "Gender should contain only alphabets", Toast.LENGTH_SHORT).show();
                }
                else if (!isFathersNameValid) {
                    Toast.makeText(StudentprofileActivity.this, "Fathers name should contain only alphabets", Toast.LENGTH_SHORT).show();
                }
                else if (!isFathersPhoneNumberValid) {
                    Toast.makeText(StudentprofileActivity.this, "Fathers Phone Number should contain only numbers & 10 digits", Toast.LENGTH_SHORT).show();
                }
                else if (!isFathersOccupValid) {
                    Toast.makeText(StudentprofileActivity.this, "First occupation should contain only alphabets", Toast.LENGTH_SHORT).show();
                }
                else if (!isMothersNameValid) {
                    Toast.makeText(StudentprofileActivity.this, "Mothers name should contain only alphabets", Toast.LENGTH_SHORT).show();
                }
                else if (!isMothersPhoneNumberValid) {
                    Toast.makeText(StudentprofileActivity.this, "Mothers Phone Number should contain only numbers & 10 digits", Toast.LENGTH_SHORT).show();
                }
                else if (!isMothersOccupValid) {
                    Toast.makeText ( StudentprofileActivity.this, "Mothers occupation should contain only alphabets", Toast.LENGTH_SHORT ).show ();
                }
                else{

                    databaseReference.child("student_profile").addListenerForSingleValueEvent(new ValueEventListener () {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.hasChild(studidtxt)){

                                Toast.makeText(StudentprofileActivity.this, "Student already exist, try using different Student ID", Toast.LENGTH_SHORT).show();
                            }
                            else{

                                databaseReference.child("student_profile").child(studidtxt).child("firstname").setValue(fntxt);
                                databaseReference.child("student_profile").child(studidtxt).child("middlename").setValue(mntxt);
                                databaseReference.child("student_profile").child(studidtxt).child("lastname").setValue(lntxt);
                                databaseReference.child("student_profile").child(studidtxt).child("paddress").setValue(paddresstxt);
                                databaseReference.child("student_profile").child(studidtxt).child("nationality").setValue(nationalitytxt);
                                databaseReference.child("student_profile").child(studidtxt).child("relegion").setValue(relegiontxt);
                                databaseReference.child("student_profile").child(studidtxt).child("phoneno").setValue(phonenotxt);
                                databaseReference.child("student_profile").child(studidtxt).child("personalemail").setValue(personalemailtxt);
                                databaseReference.child("student_profile").child(studidtxt).child("bloodgrp").setValue(bloodtxt);
                                databaseReference.child("student_profile").child(studidtxt).child("age").setValue(agetxt);
                                databaseReference.child("student_profile").child(studidtxt).child("gender").setValue(gendertxt);
                                databaseReference.child("student_profile").child(studidtxt).child("fathersname").setValue(fathersnametxt);
                                databaseReference.child("student_profile").child(studidtxt).child("fathersphone").setValue(fathersphonetxt);
                                databaseReference.child("student_profile").child(studidtxt).child("fathersoccupation").setValue(fathersoccuptxt);
                                databaseReference.child("student_profile").child(studidtxt).child("mothersname").setValue(mothersnametxt);
                                databaseReference.child("student_profile").child(studidtxt).child("mothersphone").setValue(mothersphonetxt);
                                databaseReference.child("student_profile").child(studidtxt).child("mothersoccupation").setValue(mothersoccuptxt);
                                databaseReference.child("student_profile").child(studidtxt).child("dob").setValue(dobtxt);

                                Toast.makeText(StudentprofileActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(StudentprofileActivity.this,AcademicdetailsActivity.class);
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
//                Intent intent = new Intent (StudentprofileActivity.this,Dashboard.class);
//                startActivity ( intent );
//                finish();
//                break;
//
//            case R.id.logout1:
//                Toast.makeText(this, "Clicked logout", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent (StudentprofileActivity.this,MainActivity.class);
//                startActivity ( i );
//                finish();
//                break;
//
//        }
//        return super.onOptionsItemSelected(item);
//    }
}


