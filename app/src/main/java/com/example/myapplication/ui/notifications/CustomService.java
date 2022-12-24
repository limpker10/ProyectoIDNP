package com.example.myapplication.ui.notifications;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.navigation.Navigation;

import com.example.myapplication.NavActivity;
import com.example.myapplication.R;

public class CustomService extends Service {

    static private String TAG = CustomService.class.getSimpleName();

    static private CustomService instance;

    static public CustomService getInstance() {
        if (instance == null) {
            instance = new CustomService();
        }
        return instance;
    }


    private NotificationManagerCompat notificationManagerCompat;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate()");
        notificationManagerCompat = NotificationManagerCompat.from(this);
    }

    public void createNotification2(Context context, String title, String message) {
        Log.d(TAG,"createNotification2");
        if (notificationManagerCompat == null)
            notificationManagerCompat = NotificationManagerCompat.from(context);

        Notification notification = new NotificationCompat.Builder(context, NavActivity.CHANNEL_ID_2)
                .setOngoing(false)
                .setAutoCancel(false)
                //.setCategory(NotificationCompat.CATEGORY_CALL)
                .setDefaults(NotificationCompat.DEFAULT_SOUND | NotificationCompat.DEFAULT_LIGHTS | NotificationCompat.DEFAULT_VIBRATE)
                .setPriority(NotificationCompat.PRIORITY_MAX | NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.drawable.ic_email)
                .setContentTitle(title) //Set title of Notification First Row
                .setContentText(message) //Set Text for Notification Second Row
                .setSubText(message) //Set title of Notification Third Row
                .setOnlyAlertOnce(false)
                //.setSound(null) //For No Sounds
                //.setVibrate(new long[]{0L}) //For No Vibration
                //.setNotificationSilent()
                //.setContentIntent(getPendingIntent(context)) //onContentTapped
                //.setDeleteIntent(null) //onSwipedAway
                //.addAction(0, "Reply", null)
                //.addAction(0, "Call", null)
                .build();

        notificationManagerCompat.notify(NavActivity.CHANNEL_ID2, notification);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy()");
    }
}