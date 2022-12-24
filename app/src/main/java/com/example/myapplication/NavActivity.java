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


    public static final int CHANNEL_ID2 = 0x00002;
    public static final String GROUP_ID_1 = "CustomServiceGroup1";
    public static final String CHANNEL_ID_2 = "CustomServiceChannel2";

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
            manager.createNotificationChannel(channel2);

        }
    }


}