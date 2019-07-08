package com.example.cmoproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmoproject.Models.ShowValues;
import com.example.cmoproject.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyviewHolder> {

   private Context mContext;
    private ArrayList<ShowValues> showValues;

    public RecyclerViewAdapter(Context mContext, ArrayList<ShowValues> showValues) {
        this.mContext = mContext;
        this.showValues = showValues;
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder {

        public TextView valuetext;
        public TextView categorietext;

        public MyviewHolder(@NonNull View v) {
            super(v);

            valuetext = (TextView) v.findViewById(R.id.valuetextview);
            categorietext= (TextView) v.findViewById(R.id.categorietextview);

        }
    }
    public RecyclerViewAdapter(ArrayList<ShowValues> showValues){
        this.showValues=showValues;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public void setShowValues(ArrayList<ShowValues> showValues) {
        this.showValues = showValues;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_value,parent,false);
        MyviewHolder vHolder = new MyviewHolder(v);



        return vHolder;
    }

    @Override
    public void onBindViewHolder( MyviewHolder holder, int position) {

        holder.getAdapterPosition();
        holder.categorietext.setText(showValues.get(position).categoriename);
        holder.valuetext.setText(showValues.get(position).value);



    }

    @Override
    public int getItemCount() {
        if (showValues == null)
            return 0;
        else
            return  showValues.size();
    }

    public void updateDataset(ArrayList<ShowValues> newData) {
        showValues = newData;
        notifyDataSetChanged();
    }
}
