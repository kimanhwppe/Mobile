package com.example.lab3_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {
    private Context mContext;
    private int mResource;
    private OnContactLongClickListener mLongClickListener; // Define the interface

    // Interface to handle long click events
    public interface OnContactLongClickListener {
        void onContactLongClick(Contact contact);
    }

    public ContactAdapter(Context context, int resource, List<Contact> objects, OnContactLongClickListener longClickListener) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.mLongClickListener = longClickListener; // Assign the listener
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
        }

        final Contact contact = getItem(position);

        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewPhoneNumber = convertView.findViewById(R.id.textViewPhoneNumber);

        if (contact != null) {
            textViewName.setText(contact.getName());
            textViewPhoneNumber.setText(contact.getPhoneNumber());
        }

        // Trigger the long click event
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mLongClickListener != null) {
                    mLongClickListener.onContactLongClick(contact);
                    return true;
                }
                return false;
            }
        });

        return convertView;
    }
}
