package com.example.myapplication.ui.dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.myapplication.database.AppDataBase;
import com.example.myapplication.database.entities.PlasticHistory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class BarView extends View {


    private ArrayList<String> listaPais = new ArrayList<>();
    private ArrayList<Double> listPlace = new ArrayList<>();
    private int limiteMaximo;

    public BarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.limiteMaximo = Integer.MAX_VALUE;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        ingresandoDatos();

        //recibiendo el ancho y alto
        int ancho = getWidth();
        int alto = getHeight();

        Paint pincel1 = new Paint();
        pincel1.setColor(Color.RED);

        Paint pincel2 = new Paint();
        pincel2.setColor(Color.BLACK);
        pincel2.setStrokeWidth(3);


        pincel2.setTextSize(40);

        canvas.drawText("Cantidad Plasticos Recolectados", 0.25f*ancho,
                0.1f*alto, pincel2);

        pincel2.setTextSize(22);


        canvas.drawLine(0.15f*ancho, 0.2f*alto, 0.15f*ancho, 0.8f*alto, pincel2);
        canvas.drawLine(0.15f*ancho, 0.8f*alto, 0.85f*ancho, 0.8f*alto, pincel2);


        pincel2.setStrokeWidth(1);
        for (int i = 0; i < 10; i++){
            float yLine = 0.2f*alto + (0.06f)*alto*(i);
            //numeros de la eje y
            canvas.drawText(""+(limiteMaximo/10)*(10-i), 0.1f*ancho, yLine, pincel2);

            canvas.drawLine(0.15f*ancho, yLine,
                    0.85f*ancho, yLine,
                    pincel2);
        }


        canvas.drawRect(0.34f*ancho,0.92f*alto,
                0.36f*ancho, 0.94f*alto, pincel1);
        canvas.drawText("Tasa de Natalidad", 0.38f*ancho,
                0.94f*alto, pincel2);


        float parts = (0.70f*ancho)/((float)listaPais.size()*2 + 1f);

        float espacios = parts;

        for (int i = 0; i < listaPais.size(); i++) {

            canvas.drawText(listaPais.get(i), 0,3,0.15f*ancho +espacios, 0.85f*alto, pincel2);

            canvas.drawRect(0.15f*ancho + espacios,
                    0.8f*alto-(0.6f*alto* listPlace.get(i).floatValue()/limiteMaximo),
                    0.15f*ancho + espacios+parts, 0.8f*alto, pincel1);
            espacios += parts*2;
        }

    }

    public void ingresandoDatos(){

        SharedPreferences preferences = getContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        int id = preferences.getInt("id",0);

        limiteMaximo = AppDataBase.getInstance(getContext()).historyDao().getAmountPlasticAll(id);
        List<PlasticHistory> a = AppDataBase.getInstance(getContext()).historyDao().getAllId(id);

        for (int i = 0 ; i < a.size(); i++) {
            this.listaPais.add(a.get(i).getPlasticType());
        }
        Set<String> hashSet = new HashSet<String>(listaPais);
        listaPais.clear();
        listaPais.addAll(hashSet);

        for (int i = 0 ; i < listaPais.size(); i++){
            //this.listaPais.add(a.get(i).getPlasticType());
            int can = AppDataBase.getInstance(getContext()).historyDao().getAmountByPlastic(listaPais.get(i));
            if(can != 0){
                listPlace.add((double) (can) );
            }
        }

    }

}
