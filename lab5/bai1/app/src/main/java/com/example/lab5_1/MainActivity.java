package com.example.lab5_1;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver broadcastReceiver;
    private IntentFilter filter;

    @SuppressLint("MissingInflatedId")
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
        Toast.makeText(context, getString(R.string.you_have_a_new_message),Toast.LENGTH_LONG).show();

        TextView tvContent = findViewById(R.id.tv_content);
        HashMap<String, String> hashmap = null;

        final String SMS_EXTRA = "pdus";
        Bundle bundle = intent.getExtras();

        Object[] messages = (Object[]) bundle.get(SMS_EXTRA);
        String format = bundle.getString("format");
        String sms = "";
        String address = "";

        if (messages != null) {
            SmsMessage[] smsMsg = new SmsMessage[messages.length];
            hashmap = new HashMap<String, String>(messages.length);
            for (int i = 0; i < messages.length; i++) {
                if (Build.VERSION.SDK_INT >= 23)
                    smsMsg[i] = SmsMessage.createFromPdu((byte[]) messages[i], format);
                else
                    smsMsg[i] = SmsMessage.createFromPdu((byte[]) messages[i]);
                String msgBody = smsMsg[i].getMessageBody();
                address = smsMsg[i].getDisplayOriginatingAddress();
                if (!hashmap.containsKey(address)) {
                    hashmap.put(address, msgBody);
                } else {
                    String previousmsg = hashmap.get(address);
                    hashmap.put(address, previousmsg + msgBody);
                }
            }
            for (HashMap.Entry<String, String> entry : hashmap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                sms += key + ":\n" + value + "\n\n";
            }
        }

        tvContent.setText(sms);
    }

    public void initBroadcastReceiver() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, "android.permission.RECEIVE_SMS") == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{"android.permission.RECEIVE_SMS"}, 1);
        filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

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