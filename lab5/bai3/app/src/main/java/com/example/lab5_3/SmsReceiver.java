package com.example.lab5_3;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;

import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.Objects;

public class SmsReceiver extends BroadcastReceiver {
    public static final String SMS_FORWARD_BROADCAST_RECEIVER = "sms_forward_broadcast_receiver";
    public static final String SMS_MESSAGE_ADDRESS_KEY = "sms_messages_key";
    public static final int NOTIFICATION_ID = 111111;
    public static final String CHANNEL_ID = "channel_1111";
    public static final String TAG_NOTIFICATION = "NOTIFICATION_MESSAGE";
    @Override
    public void onReceive(Context context, Intent intent) {
        String queryString = "Are you OK?".toLowerCase();

        Bundle bundle = intent.getExtras();
        String format = bundle.getString("format");

        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i = 0; i < pdus.length; i++) {
                if (Build.VERSION.SDK_INT >= 23) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                } else {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
            }

            // Create ArrayList of OriginatingAddress of messages which contains queryString
            ArrayList<String> addresses = new ArrayList<>();

            for (SmsMessage message : messages) {
                if (message.getMessageBody().toLowerCase().contains(queryString)) {
                    addresses.add(message.getOriginatingAddress());
                }
            }

            if (addresses.size() > 0) {
                if (!MainActivity.isRunning) {
                    // Start MainActivity if it is stopped
                    // If android 10 or higher
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                        startActivityNotification(
                                context,
                                NOTIFICATION_ID,
                                context.getResources().getString(R.string.open_app),
                                context.getResources().getString(R.string.click_app),
                                addresses
                        );
                    } else {
                        Intent iMain = new Intent(context, MainActivity.class);
                        iMain.putStringArrayListExtra(SMS_MESSAGE_ADDRESS_KEY, addresses);
                        iMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
                else {
                    // Forward these addresses to MainActivity to process
                    Intent iForwardBroadcastReceiver = new Intent(SMS_FORWARD_BROADCAST_RECEIVER);
                    iForwardBroadcastReceiver.putStringArrayListExtra(SMS_MESSAGE_ADDRESS_KEY, addresses);
                    context.sendBroadcast(iForwardBroadcastReceiver);
                }
            }
        }
    }
    // notification method to support opening activities on Android 10
    public static void startActivityNotification (Context context, int notificationID,
                                                  String title, String message, ArrayList<String> addresses) {

        NotificationManager mNotificationManager =
                (NotificationManager)
                        context.getSystemService(Context.NOTIFICATION_SERVICE);
        //Create GPSNotification builder
        NotificationCompat.Builder mBuilder;

        //Initialise ContentIntent
        Intent ContentIntent = new Intent(context, MainActivity.class);
        ContentIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TOP);
        ContentIntent.putStringArrayListExtra(SMS_MESSAGE_ADDRESS_KEY, addresses);
        PendingIntent ContentPendingIntent = PendingIntent . getActivity (context,
                0,
                ContentIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        mBuilder = new NotificationCompat . Builder (context)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(message)
                .setColor(context.getResources().getColor(R.color.black))
                .setAutoCancel(true)
                .setContentIntent(ContentPendingIntent)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)
                .setCategory(NotificationCompat.CATEGORY_SYSTEM)
                .setPriority(NotificationCompat.PRIORITY_MAX);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Activity Opening Notification",
                    NotificationManager.IMPORTANCE_HIGH
            );
            mChannel.enableLights(true);
            mChannel.enableVibration(true);
            mChannel.setDescription("Activity opening notification");

            mBuilder.setChannelId(CHANNEL_ID);

            Objects.requireNonNull(mNotificationManager).createNotificationChannel(mChannel);
        }
        Objects.requireNonNull(mNotificationManager).notify(
                TAG_NOTIFICATION, notificationID,
                mBuilder.build()
        );
    }

}