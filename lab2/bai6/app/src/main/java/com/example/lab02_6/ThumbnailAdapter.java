package com.example.lab02_6;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ThumbnailAdapter extends ArrayAdapter<Thumbnail> {

    private Context context;
    public ThumbnailAdapter(Context context, int layoutId, List<Thumbnail>objects){
        super(context,layoutId, objects);
        this.context=context;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent){
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_thumbnail, null, false);
        }
        Thumbnail thumbnail = getItem(position);

        TextView tvName = convertView.findViewById(R.id.tvitemname);
        ImageView ivThumbnail = convertView.findViewById(R.id.ivThumbnail);

        tvName.setText(thumbnail.getName());
        ivThumbnail.setImageResource(thumbnail.getImg());

        return convertView;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_selected_thumbnail, null, false);
        }
        Thumbnail thumbnail = getItem(position);
        TextView tvName = (TextView)convertView.findViewById(R.id.tvitemname    );

        tvName.setText(thumbnail.getName());
        tvName.setSelected(true);

        return convertView;
    }

}