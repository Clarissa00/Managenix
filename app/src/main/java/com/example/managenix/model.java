package com.example.managenix;

public class model {
    String firstname, lastname, phoneno, personalemail;

    model(){

    }

    public model(String firstname, String lastname, String phoneno, String personalemail) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneno = phoneno;
        this.personalemail = personalemail;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getPersonalemail() {
        return personalemail;
    }

    public void setPersonalemail(String personalemail) {
        this.personalemail = personalemail;
    }
}
