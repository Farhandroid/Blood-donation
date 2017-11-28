package com.example.hp.blooddonation.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.UserHandle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.hp.blooddonation.Database.DatabaseHelper;
import com.example.hp.blooddonation.HelperClass.RecyclerAdapter;
import com.example.hp.blooddonation.ModelClass.UserBloodDonationMC;
import com.example.hp.blooddonation.R;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    FloatingActionButton floatingActionButton;

    private MaterialSearchView searchView;

    ArrayList<UserBloodDonationMC> userBloodDonationMCS;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        userBloodDonationMCS = new ArrayList<>();

        getDataFromDatabase();




        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);



        floatingActionButton = findViewById(R.id.floattingButton);

        ///updateRecyclerView(userBloodDonationMCS);



        searchView = (MaterialSearchView) findViewById(R.id.search_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new getData().execute();

        updateRecyclerView(userBloodDonationMCS);




    }

    public void startLoginActivity(View view) {

        Intent intent = new Intent(this, Register.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        this.startActivity(intent);
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
        finish();


    }

    public  void getDataFromDatabase()
    {
        userBloodDonationMCS = databaseHelper.getAllData();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<UserBloodDonationMC> userBloodDonationMCS1=new ArrayList<>();
                userBloodDonationMCS1.clear();

                for (int i=0;i<userBloodDonationMCS.size();i++)
                {

                    if (userBloodDonationMCS.get(i).getBloodGroup().toLowerCase().contains(newText.toLowerCase()) || userBloodDonationMCS.get(i).getLocation().toLowerCase().trim().contains(newText.toLowerCase().trim()) )
                    {


                        userBloodDonationMCS1.add(userBloodDonationMCS.get(i));


                    }
                }
                updateRecyclerView(userBloodDonationMCS1);





                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic

                floatingActionButton.hide();




            }

            @Override
            public void onSearchViewClosed() {

                floatingActionButton.show();

            }
        });

        return true;
    }

    public void updateRecyclerView(ArrayList<UserBloodDonationMC> userBloodDonationMCS)
    {
        adapter = new RecyclerAdapter(this,userBloodDonationMCS);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);



    }


    private class getData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {



            userBloodDonationMCS = databaseHelper.getAllData();

            return null;
        }



    }



}
