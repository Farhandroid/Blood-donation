package com.example.hp.blooddonation.ModelClass;

/**
 * Created by HP on 28-Nov-17.
 */

public class UserBloodDonationMC {

    String userName  , bloodGroup , location , contactNo , email ;

    public UserBloodDonationMC(String userName, String bloodGroup, String location, String contactNo, String email) {
        this.userName = userName;

        this.bloodGroup = bloodGroup;
        this.location = location;
        this.contactNo = contactNo;
        this.email = email;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
