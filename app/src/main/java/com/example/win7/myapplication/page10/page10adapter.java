package com.example.win7.myapplication.page10;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;

import com.example.win7.myapplication.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class page10adapter  extends RecyclerView.Adapter<page10adapter.ViewHolder>{

    private page10adapter.Listener listener;
        private ArrayList<String> trekname;
    private ArrayList<String> trekdate;
    private ArrayList<String> noofcompanions;

        public static class ViewHolder extends RecyclerView.ViewHolder{
            private CardView cardview;
            public ViewHolder(CardView v){
                super(v);
                cardview=v;
            }
        }
        public page10adapter(ArrayList<String> captions,ArrayList<String> captions2,ArrayList<String>captions3){

            this.trekname=captions;
            this.trekdate=captions2;
            this.noofcompanions=captions3;
        }
        interface Listener{
            void onClick(int position);
        }
        public void setListener(page10adapter.Listener listener){
            this.listener=listener;
        }

        @Override
    public int getItemCount()
        {
            return trekname.size();
        }



        @NonNull
    @Override
    public page10adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv=(CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.pag10cardview,parent,false);
        return new ViewHolder(cv);
    }

    public void onBindViewHolder(page10adapter.ViewHolder holder, final int position) {
            CardView cardView=holder.cardview;


            TextView text1=(TextView) cardView.findViewById(R.id.TTREKNAME);
        TextView text2=(TextView) cardView.findViewById(R.id.trekdate);
        TextView text3=(TextView) cardView.findViewById(R.id.noofcompanions);
        TextView text4=(TextView) cardView.findViewById(R.id.status);
        text1.setText(trekname.get(position));
        text2.setText(trekdate.get(position));
        text3.setText(noofcompanions.get(position));

        try {
            SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            format.format(date);
            Date d1= format.parse(trekdate.get(position));
            format.format(d1);
            Log.d("n2","mydate :" + date.toString() );
            Log.d("n2","trekdate :" + d1.toString() );

            if(d1.compareTo(date)>0){
                text4.setText("Upcoming");
            }
            else if(d1.compareTo(date)==0){
                text4.setText("Today");
            }

            else if(d1.compareTo(date)<0){
                text4.setTextColor(Color.parseColor("#D3D3D3"));
                text4.setText("Expired");
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

}


