package com.example.lab02_6;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DishAdapter extends ArrayAdapter<Dish> {
    private Activity context;
    private int LayoutID;

    public DishAdapter(Activity context, int LayoutID, List<Dish>objects){
        super(context, LayoutID, objects);
        this.context=context;
        this.LayoutID= LayoutID;

    }
    @Override
    public View getView(final int position, View contextView, ViewGroup parent){
        if(contextView==null){
            contextView= LayoutInflater.from(context).inflate(LayoutID, null, false);
        }
        Dish Dish = getItem(position);

        //getView
        TextView tvname = contextView.findViewById(R.id.tvname);
        ImageView ivthumb = contextView.findViewById(R.id.iv_thumbnail);
        ImageView ivpro = contextView.findViewById(R.id.iv_promotion);

        // show thumbnail
        if(Dish.getThumbnail() != null){
            ivthumb.setImageResource(Dish.getThumbnail().getImg());
        }
        //show name
        if(Dish.getName() != null){
            tvname.setText(Dish.getName());
            tvname.setHorizontallyScrolling(true);
            tvname.setSelected(true);
        }
        //show promotion
        if(Dish.isPromotion()){
            ivpro.setVisibility(View.VISIBLE);
        } else {
            ivpro.setVisibility(View.GONE);
        }
        return contextView;
    }
}
