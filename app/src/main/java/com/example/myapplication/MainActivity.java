package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.os.Build;
import androidx.core.app.NotificationManagerCompat;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    public static final int CHANNEL_ID2 = 0x00002;

    public static final String GROUP_ID_1 = "CustomServiceGroup1";
    public static final String CHANNEL_ID_2 = "CustomServiceChannel2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannelGroup group1 = new NotificationChannelGroup(
                    GROUP_ID_1,
                    "Group 1"
            );

            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_ID_2,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_HIGH
            );
            //channel2.setSound(null,null);
            //channel2.enableVibration(false);
            channel2.setDescription("This is Channel 2");
            channel2.setGroup(GROUP_ID_1);

            NotificationManagerCompat manager = NotificationManagerCompat.from(this);
            manager.createNotificationChannelGroup(group1);

            manager.createNotificationChannel(channel2);

        }
    }


}