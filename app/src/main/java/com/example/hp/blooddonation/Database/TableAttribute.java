package com.example.hp.blooddonation.Database;

/**
 * Created by USER on 08-Oct-17.
 */

public class TableAttribute {

    public static final String DATABASE_NAME="DatabaseForBloodDonation";
    public static final int DATABASE_VERSION=1;

    public static final String USER_TABLE="User";
    public static final String COL_USERNAME="UserName";
    public static final String COL_EMAIL="Email";
    public static final String COL_BLOOD_GROUP="BloodGroup";
    public static final String COL_LOCATION="Location";
    public static final String COL_CONTACT_NO="ContactNo";





    public String userTableCreation()
    {
        String query = "CREATE TABLE IF NOT EXISTS "+ USER_TABLE +"( "+ COL_USERNAME +" TEXT PRIMARY KEY , " +COL_BLOOD_GROUP + " TEXT , "+COL_LOCATION+ " TEXT , "+COL_CONTACT_NO+ " TEXT , "+COL_EMAIL+" TEXT ) ";

        return query;
    }

}
