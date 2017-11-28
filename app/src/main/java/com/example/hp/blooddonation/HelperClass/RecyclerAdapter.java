package com.example.hp.blooddonation.HelperClass;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hp.blooddonation.ModelClass.UserBloodDonationMC;
import com.example.hp.blooddonation.R;

import java.util.ArrayList;



/**
 * Created by USER on 01-Feb-17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {



    Context context;

    ArrayList<UserBloodDonationMC> userBloodDonationMCS;

    public RecyclerAdapter(Context context,ArrayList<UserBloodDonationMC> userBloodDonationMCS ) {
        this.context = context;
        this.userBloodDonationMCS=userBloodDonationMCS;


    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_to_inflate_in_recyclerview, parent, false);

        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, context,userBloodDonationMCS);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        holder.bloodGroupTV.setText(userBloodDonationMCS.get(position).getBloodGroup());
        holder.userNameTV.setText(userBloodDonationMCS.get(position).getUserName());



    }

    @Override
    public int getItemCount() {
        return userBloodDonationMCS.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener

    {


        Context context;

        TextView userNameTV;
       TextView  bloodGroupTV;
       LinearLayout linearLayout;



        public RecyclerViewHolder(View view, final Context context,  ArrayList<UserBloodDonationMC> userBloodDonationMCS) {
            super(view);


            this.context = context;

            userNameTV = view.findViewById(R.id.nameTVRV);
            bloodGroupTV = view.findViewById(R.id.bloodGroupRV);
            linearLayout=view.findViewById(R.id.llClick);
            linearLayout.setOnClickListener(this);





        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();

            TextView userNameTV , bloodGroupTV , locationTV , contactNoTV , emailTV;



            final AlertDialog alertDialog;

            final View donorsDetailsDialogView;




            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ;
            donorsDetailsDialogView = inflater.inflate(R.layout.user_details, null);

            userNameTV = donorsDetailsDialogView.findViewById(R.id.nameDD);
            bloodGroupTV = donorsDetailsDialogView.findViewById(R.id.bloodGroupDD);
            locationTV = donorsDetailsDialogView.findViewById(R.id.locationDD);
            contactNoTV = donorsDetailsDialogView.findViewById(R.id.phoneDD);
            emailTV = donorsDetailsDialogView.findViewById(R.id.emailDD);

            userNameTV.setText(userBloodDonationMCS.get(position).getUserName());
            bloodGroupTV.setText(userBloodDonationMCS.get(position).getBloodGroup());
            locationTV.setText(userBloodDonationMCS.get(position).getLocation());
            contactNoTV.setText(userBloodDonationMCS.get(position).getContactNo());
            emailTV.setText(userBloodDonationMCS.get(position).getEmail());


            dialogBuilder.setView(donorsDetailsDialogView);
            alertDialog = dialogBuilder.create();
            alertDialog.show();




        }

    }


}
