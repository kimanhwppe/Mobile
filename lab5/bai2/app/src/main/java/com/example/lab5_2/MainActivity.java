package com.example.lab5_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver broadcastReceiver;
    private IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initBroadcastReceiver();
    }

    public void processReceive(Context context, Intent intent) {
        if (context == null) return;
        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED))
            Toast.makeText(context, R.string.power_conneted, Toast.LENGTH_LONG).show();
        if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED))
            Toast.makeText(context, R.string.power_disconnected, Toast.LENGTH_LONG).show();
    }

    public void initBroadcastReceiver() {
        filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                processReceive(context, intent);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (broadcastReceiver == null) initBroadcastReceiver();

        registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected  void onStop() {
        super.onStop();

        unregisterReceiver(broadcastReceiver);
    }
}