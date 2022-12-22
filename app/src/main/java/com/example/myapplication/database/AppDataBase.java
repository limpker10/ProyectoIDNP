package com.example.myapplication.database;

import static com.example.myapplication.database.BitmapManager.bitmapToByte;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.R;
import com.example.myapplication.database.dao.HistoryDao;
import com.example.myapplication.database.dao.TypePlasticDao;
import com.example.myapplication.database.dao.UserDao;
import com.example.myapplication.database.entities.PlasticHistory;
import com.example.myapplication.database.entities.PlasticType;
import com.example.myapplication.database.entities.User;

@Database(entities = {User.class, PlasticType.class, PlasticHistory.class}, version = 2_2)
public abstract class AppDataBase extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "Plastic.db";
    private static AppDataBase sInstance;

    public abstract UserDao userDao();
    public abstract TypePlasticDao plasticDao();
    public abstract HistoryDao historyDao();

    public static AppDataBase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context, AppDataBase.class, DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
                LoadPlasticType(sInstance,context);
            }
        }
        return sInstance;
    }

    public static void LoadPlasticType(AppDataBase sInstance, Context context) {

        //bitmapToByte(BitmapFactory.decodeResource(context.getResources(),R.drawable.pet));
        sInstance.plasticDao().insertAll(
                new PlasticType("PET O PETE", "#775447", "Botellas de bebidas,paquetes de comida, botes de crema y otros usos farmaceuticos", bitmapToByte(BitmapFactory.decodeResource(context.getResources(), R.drawable.pet)))
        );
        sInstance.plasticDao().insertAll(
                new PlasticType("HDPE", "#775447", "Envases no transparantes como botelals de leche detergentes o de aceite para motores", bitmapToByte(BitmapFactory.decodeResource(context.getResources(), R.drawable.hdpe)))
        );
        sInstance.plasticDao().insertAll(
                new PlasticType("PVC", "#775447", "Tarjetas de credito.tuberoias,revestimiento de cables,pieles sinteticas y marcos de puertas y ventanas", bitmapToByte(BitmapFactory.decodeResource(context.getResources(), R.drawable.pvc)))
        );
        sInstance.plasticDao().insertAll(
                new PlasticType("LDPE", "#775447", "Film adhesivo, bolsas de la compra, botellas flexibles, bolsas de suero o aislantes de cableado", bitmapToByte(BitmapFactory.decodeResource(context.getResources(), R.drawable.ldpe)))
        );
        sInstance.plasticDao().insertAll(
                new PlasticType("PP", "#775447", "Tapones, jeringas. tupperwares,neveras portatiles, piezas de automoviles, fibras de tejidos", bitmapToByte(BitmapFactory.decodeResource(context.getResources(), R.drawable.pp)))
        );
        sInstance.plasticDao().insertAll(
                new PlasticType("PS", "#775447", "Vasos para bebidas calientes, envases, cubiertos de plastico, rellenos para embalaje o bandejas de comida", bitmapToByte(BitmapFactory.decodeResource(context.getResources(), R.drawable.ps)))
        );
        sInstance.plasticDao().insertAll(
                new PlasticType("Otros", "#775447", "Mezclas de varios plasticos empleados en articulos electronicos, electrodomesticos, articulos medicos, etc", bitmapToByte(BitmapFactory.decodeResource(context.getResources(), R.drawable.otros)))
        );

    }
}
