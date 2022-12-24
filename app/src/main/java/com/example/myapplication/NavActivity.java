package com.example.myapplication;

import static com.example.myapplication.database.BitmapManager.byteToBitmap;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.database.AppDataBase;
import com.example.myapplication.database.dao.UserDao;
import com.example.myapplication.database.entities.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityNavBinding;

public class NavActivity extends AppCompatActivity {

    public static final int CHANNEL_ID1 = 0x00001;
    public static final int CHANNEL_ID2 = 0x00002;
    public static final int CHANNEL_ID3 = 0x00003;
    public static final int CHANNEL_ID4 = 0x00004;
    public static final int CHANNEL_ID5 = 0x00005;
    public static final String GROUP_ID_1 = "CustomServiceGroup1";
    public static final String CHANNEL_ID_1 = "CustomServiceChannel1";
    public static final String CHANNEL_ID_2 = "CustomServiceChannel2";
    public static final String CHANNEL_ID_3 = "CustomServiceChannel3";
    public static final String CHANNEL_ID_4 = "CustomServiceChannel4";
    public static final String CHANNEL_ID_5 = "CustomServiceChannel5";
    private User user_data;
    private ActivityNavBinding binding;
    private static final String TAG = "ErrorUser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        createNotificationChannel();
        //guardarPreferencias(properties.getInstance().user)....
    }

    @Override
    protected void onStart() {
        super.onStart();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_nav);
        NavController navController = navHostFragment.getNavController();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_charts, R.id.navigation_info,R.id.navigation_profile)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannelGroup group1 = new NotificationChannelGroup(
                    GROUP_ID_1,
                    "Group 1"
            );

            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_ID_1,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel1.setDescription("This is Channel 1");
            channel1.setGroup(GROUP_ID_1);

            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_ID_2,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_HIGH
            );
            //channel2.setSound(null,null); //For No Sounds
            //channel2.enableVibration(false); //For No Vibration
            channel2.setDescription("This is Channel 2");
            channel2.setGroup(GROUP_ID_1);

            NotificationChannel channel3 = new NotificationChannel(
                    CHANNEL_ID_3,
                    "Channel 3",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel3.setDescription("This is Channel 3");
            channel3.setGroup(GROUP_ID_1);

            NotificationManagerCompat manager = NotificationManagerCompat.from(this);
            manager.createNotificationChannelGroup(group1);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
            manager.createNotificationChannel(channel3);
        }
    }


}