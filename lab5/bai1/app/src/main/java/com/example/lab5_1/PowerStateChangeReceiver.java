package com.example.lab5_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Objects;

public class PowerStateChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (context == null) return;
        if (Objects.equals(intent.getAction(), Intent.ACTION_POWER_CONNECTED)){
            Toast.makeText(context, "Power Connected", Toast.LENGTH_LONG).show();
        }
        if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)){
            Toast.makeText(context, "Power Disconnected", Toast.LENGTH_LONG).show();
        }
    }
}