package com.example.hp.blooddonation.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.hp.blooddonation.ModelClass.UserBloodDonationMC;

import java.util.ArrayList;


/**
 * Created by USER on 04-Jul-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    public DatabaseHelper(Context context) {
        super(context, TableAttribute.DATABASE_NAME, null, TableAttribute.DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        TableAttribute tableAttribute = new TableAttribute();
        String query = tableAttribute.userTableCreation();
        sqLiteDatabase.execSQL(query);


        try {
            sqLiteDatabase.execSQL(query);
        } catch (Exception e) {
            Toast.makeText(context, "Problem in create User Table", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertDataInDatabase(UserBloodDonationMC userBloodDonationMC) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TableAttribute.COL_USERNAME, userBloodDonationMC.getUserName());
        contentValues.put(TableAttribute.COL_BLOOD_GROUP, userBloodDonationMC.getBloodGroup());
        contentValues.put(TableAttribute.COL_LOCATION, userBloodDonationMC.getLocation());
        contentValues.put(TableAttribute.COL_CONTACT_NO, userBloodDonationMC.getContactNo());
        contentValues.put(TableAttribute.COL_EMAIL, userBloodDonationMC.getEmail());

        long result = db.insert(TableAttribute.USER_TABLE, null, contentValues);

        if (result > 0) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }

    /*public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TableAttribute.USER_TABLE, null);


        return result;
    }


    public boolean deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();


        int result = db.delete(TableAttribute.USER_TABLE, null, null);
        db.close();

        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }


    public boolean deleteNOteFromDatabase(String userName) {
        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete(TableAttribute.USER_TABLE, TableAttribute.COL_USERNAME + " = ?", new String[]{String.valueOf(userName)});
        db.close();

        if (result > 0) {
            return true;
        } else {
            return false;
        }

    }


   /* public  boolean checkLogin(String userName,String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor;

        String selectString = "SELECT * FROM " + TableAttribute.USER_TABLE + " WHERE " + TableAttribute.COL_USERNAME + " = ?  AND "+ TableAttribute.COL_Password + " = ?";

        cursor = db.rawQuery(selectString, new String[] {userName,password});

        if (cursor.getCount()>0)
        {
            cursor.moveToFirst();
            cursor.close();
            db.close();
            return  true;
        }
        else
        {
            cursor.close();
            db.close();
            return  false;
        }

    }

    /*public boolean addBusInDatabase(BusINfoMC busINfoMC)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TableAttribute.COL_BUS_NAME,busINfoMC.getBusName());
        contentValues.put(TableAttribute.COL_BUS_ID,busINfoMC.getBusID());
        contentValues.put(TableAttribute.COL_BUS_TYPE,busINfoMC.getBusType());
        contentValues.put(TableAttribute.COL_DEPARTURE_DATE,busINfoMC.getDepartureDate());
        contentValues.put(TableAttribute.COL_DEPARTURE_TIME,busINfoMC.getDepartureTime());
        contentValues.put(TableAttribute.COL_START_FROM,busINfoMC.getStartFrom());
        contentValues.put(TableAttribute.COL_DESTINATION,busINfoMC.getDestination());
        contentValues.put(TableAttribute.COL_TOTAL_BUS_SEAT,busINfoMC.getTotalBusSeat());
        contentValues.put(TableAttribute.COL_BUS_SEAT_PRICE,busINfoMC.getBusSeatPrice());

        ///Toast.makeText(context,busINfoMC.getBusName()+"\n"+busINfoMC.getBusID()+"\n"+busINfoMC.getBusType()+"\n"+busINfoMC.getDepartureDate()+"\n"+busINfoMC.getDepartureTime()+"\n"+busINfoMC.getStartFrom()+"\n"+busINfoMC.getDestination()+"\n"+busINfoMC.getTotalBusSeat()+"\n"+busINfoMC.getBusSeatPrice(), Toast.LENGTH_LONG).show();

       long result = db.insert(TableAttribute.BUS_TABLE,null,contentValues);

        if (result > 0) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }


    public ArrayList<BusINfoMC> getBusListFromBusTable(String startFrom, String destination, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TableAttribute.USER_TABLE, null);


        Cursor cursor;

        String selectString = "SELECT * FROM " + TableAttribute.BUS_TABLE + " WHERE " + TableAttribute.COL_START_FROM + " = ?  AND " + TableAttribute.COL_DESTINATION + " = ?" + " AND " + TableAttribute.COL_DEPARTURE_DATE + " = ?";

        cursor = db.rawQuery(selectString, new String[]{startFrom, destination, date});

        ArrayList<BusINfoMC> busINfoMCArrayList = new ArrayList<>();


        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                BusINfoMC busINfoMC = new BusINfoMC();

                busINfoMC.setBusName(cursor.getString(cursor.getColumnIndex(TableAttribute.COL_BUS_NAME)));
                busINfoMC.setBusType(cursor.getString(cursor.getColumnIndex(TableAttribute.COL_BUS_TYPE)));
                busINfoMC.setDepartureTime(cursor.getString(cursor.getColumnIndex(TableAttribute.COL_DEPARTURE_TIME)));
                busINfoMC.setBusSeatPrice(cursor.getString(cursor.getColumnIndex(TableAttribute.COL_BUS_SEAT_PRICE)));

                busINfoMCArrayList.add(busINfoMC);

                cursor.moveToNext();
            }



        }
        return busINfoMCArrayList;
    }*/

    public ArrayList<UserBloodDonationMC> getAllData() {

        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<UserBloodDonationMC> UserBloodDonationMCS = new ArrayList<>();

        ///Toast.makeText(context, "Enter getPostForUser", Toast.LENGTH_SHORT).show();




        Cursor cursor = db.rawQuery("SELECT * FROM "+TableAttribute.USER_TABLE,null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {


                String email = cursor.getString(cursor.getColumnIndex(TableAttribute.COL_EMAIL));
                String userName = cursor.getString(cursor.getColumnIndex(TableAttribute.COL_USERNAME));
                String bloodGroup = cursor.getString(cursor.getColumnIndex(TableAttribute.COL_BLOOD_GROUP));
                String location = cursor.getString(cursor.getColumnIndex(TableAttribute.COL_LOCATION));
                String contactNo = cursor.getString(cursor.getColumnIndex(TableAttribute.COL_CONTACT_NO));


                UserBloodDonationMC UserBloodDonationMC = new UserBloodDonationMC(userName, bloodGroup, location, contactNo, email);

                UserBloodDonationMCS.add(UserBloodDonationMC);


                cursor.moveToNext();
            }


        }


        return UserBloodDonationMCS;

    }


}
