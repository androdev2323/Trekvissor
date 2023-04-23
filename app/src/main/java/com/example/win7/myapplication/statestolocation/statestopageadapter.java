package com.example.win7.myapplication.statestolocation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.win7.myapplication.R;


public class statestopageadapter extends RecyclerView.Adapter<statestopageadapter.ViewHolder> {
    private statestopageadapter.Listener listener;
    private String[] captions;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardview;
        public ViewHolder(CardView v){
            super(v);
            cardview=v;
        }
    }
    public statestopageadapter(String[] captions){

        this.captions=captions;
    }
    interface Listener{
        void onClick(String position);
    }
    public void setListener(statestopageadapter.Listener listener){
        this.listener=listener;
    }




    @Override



    public int getItemCount()
    {
        return captions.length;
    }

    public statestopageadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
        CardView cv=(CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_locationcardview,parent,false);
        return new statestopageadapter.ViewHolder(cv);
    }
    public void onBindViewHolder(statestopageadapter.ViewHolder holder, final int position) {
        CardView cardView=holder.cardview;
        final TextView text=(TextView) cardView.findViewById(R.id.textlocation);
        text.setText(captions[position]);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onClick((String) text.getText());
                }
            }
        });
    }






}