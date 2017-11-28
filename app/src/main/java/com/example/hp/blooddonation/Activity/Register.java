package com.example.hp.blooddonation.Activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hp.blooddonation.Database.DatabaseHelper;
import com.example.hp.blooddonation.ModelClass.UserBloodDonationMC;
import com.example.hp.blooddonation.R;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;
import java.util.Collections;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class Register extends AppCompatActivity {

    EditText userNameET , contactNoET , emailET;
    AutoCompleteTextView  bloodGroupET , locationET;

    DatabaseHelper databaseHelper;

    CircularProgressButton circularProgressButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseHelper = new DatabaseHelper(this);

        userNameET = findViewById(R.id.userNameInSignUpActivity);

        contactNoET= findViewById(R.id.contactNoInSignUP);
        emailET=findViewById(R.id.emailNoInSignUP);
        bloodGroupET = findViewById(R.id.bloodGroupInSignUP);
        locationET= findViewById(R.id.locationInSignUP);

        circularProgressButton=findViewById(R.id.registerBTN);

        ArrayList<String> countryNameArrayList = new ArrayList<String>();
        Resources res = getResources();
        Collections.addAll(countryNameArrayList, res.getStringArray(R.array.country_array));



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,countryNameArrayList);
        locationET.setAdapter(adapter);

        ArrayList<String> bloodNameArrayList = new ArrayList<String>();
        Resources res2 = getResources();
        Collections.addAll(countryNameArrayList, res2.getStringArray(R.array.blood_group));

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,countryNameArrayList);
        bloodGroupET.setAdapter(adapter2);



    }



    public void createUser(View view) {


        final String email = emailET.getText().toString();
        final String userName = userNameET.getText().toString();
        final String bloodGroup = bloodGroupET.getText().toString();
        final String location = locationET.getText().toString();
        final String contactNo = contactNoET.getText().toString();

        circularProgressButton.startAnimation();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){

                circularProgressButton.revertAnimation();

                if ( email.length()>0 && userName.length()>0 && bloodGroup.length()>0 && location.length()>0 && contactNo.length()>0)
                {
                    UserBloodDonationMC userBloodDonationMC = new UserBloodDonationMC(userName,bloodGroup,location,contactNo,email);

                    boolean reult = databaseHelper.insertDataInDatabase(userBloodDonationMC);

                    if (reult)
                    {
                        startMainActivity();
                        TastyToast.makeText(getApplicationContext(), "Success ", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
                    }

                    else
                    {
                        TastyToast.makeText(getApplicationContext(), "Donor registration failed. \n Possible reason  user name already taken \n please try different user name", TastyToast.LENGTH_LONG, TastyToast.ERROR);
                    }
                }
                else
                {
                    TastyToast.makeText(getApplicationContext(), "Please Fill up all field  ", TastyToast.LENGTH_LONG, TastyToast.ERROR);
                }

            }
        }, 1500);





    }

    public void startMainActivity()
    {

        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        this.startActivity(myIntent);
        overridePendingTransition(R.anim.right_in,R.anim.right_out);
        finish();


    }

    public void onBackPressed()
    {
        super.onBackPressed();

        startMainActivity();

    }
}
