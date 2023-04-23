package com.example.managenix;

public class fee_model {
    String Student_Name, Branch, Student_ID, Status;

    fee_model(){

    }

    public fee_model(String student_Name, String branch, String student_ID, String status) {
        Student_Name = student_Name;
        Branch = branch;
        Student_ID = student_ID;
        Status = status;
    }

    public String getStudent_Name() {
        return Student_Name;
    }

    public void setStudent_Name(String student_Name) {
        Student_Name = student_Name;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

    public String getStudent_ID() {
        return Student_ID;
    }

    public void setStudent_ID(String student_ID) {
        Student_ID = student_ID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}

