package com.example.managenix;

public class ReadWriteUserDetails {
    public String firstname, emailpersonal, emailcollage, phoneno, studentid;

    public ReadWriteUserDetails(String txtfirstname, String txtemailpersonal, String txtemailcollage, String txtphoneno, String txtid){
        this.firstname=txtfirstname;
        this.emailpersonal=txtemailpersonal;
        this.emailcollage=txtemailcollage;
        this.phoneno=txtphoneno;
        this.studentid=txtid;
    }
}
