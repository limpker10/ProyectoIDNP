package com.example.myapplication.ui.dashboard;

import android.content.Context;

import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;

import android.util.Log;
import android.view.View;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.myapplication.database.AppDataBase;
import com.example.myapplication.database.entities.PlasticHistory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DonutsView extends View {

    private List<String> listaPais = new ArrayList();;
    private List<Double> listPlace = new ArrayList();;
    private ArrayList<Color> listaColores = new ArrayList();
    private int ancho;
    private static final String TAG = "donuts";
    private int alto;
    private float radio;

    public DonutsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.radio = 0.0f;
    }


    @Override
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        ingresandoDatos();

        this.ancho = getWidth();
        this.alto = getHeight();

        Paint pincelNegro = new Paint();
        pincelNegro.setColor(Color.WHITE);
        Paint colorPais = new Paint();
        leyendaCirculo(canvas, colorPais, pincelNegro);
        creandoGraficoCircular(canvas, pincelNegro, colorPais);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void creandoGraficoCircular(Canvas canvas, Paint pincelNegro, Paint colorPais){

        RectF rectangulo = new RectF(0.1f*ancho, 0.25f*alto, 0.7f*ancho, 0.85f*alto);

        float sumaAngulos = 0.0f;
        float sweepAngle = 0.0f;
        float [] xy;

        pincelNegro.setFakeBoldText(true);

        for(int i = 0; i < listPlace.size(); i++){
            sweepAngle = listPlace.get(i).floatValue()*360.0f/100.0f;
            colorPais.setColor(listaColores.get(i).toArgb());
            //pintando grafico
            canvas.drawArc(rectangulo, sumaAngulos, sweepAngle,true, colorPais);
            //porcentajes(texto) dentro del grafico
            //contador
            sumaAngulos += listPlace.get(i).floatValue()*360.0f/100.0f;
        }



    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void leyendaCirculo(Canvas canvas, Paint colorPais, Paint pincelNegro) {
        Typeface tf = Typeface.create("poppings",Typeface.BOLD);
        pincelNegro.setTypeface(tf);
        pincelNegro.setStrokeWidth(3);
        pincelNegro.setTextSize(50);

        canvas.drawText("Lugares de Recoleccion", 0.25f*ancho,
                0.1f*alto, pincelNegro);

        pincelNegro.setTextSize(27);
        pincelNegro.setStrokeWidth(1);

        //leyenda
        for(int i = 0; i < listaPais.size(); i++){
            colorPais.setColor(listaColores.get(i).toArgb());
            canvas.drawRect(0.75f*ancho,(0.25f + 0.04f * i)*alto,
                    0.77f*ancho, (0.27f + 0.04f * i)*alto, colorPais);
            canvas.drawText(listaPais.get(i), 0.79f*ancho,
                    (0.27f + 0.04f * i)*alto, pincelNegro);
        }
    }

    private float[] textoPorcentajeGrafico(float startAngle, float sweepAngle) {
        float[] xy = new float[2];
        float currentAngle = (startAngle + sweepAngle / 2) * (float) Math.PI / 180.0f;
        float reajusteX = 126.0f;
        float reajusteY = 72.0f;

        //el 0.7f es del tamaño de circunferencia que toman los textos
        xy[0] = (float) Math.cos(currentAngle) * 0.75f * (this. radio * this.ancho)
                + this.ancho / 2.0f - reajusteX; //eje x
        xy[1] = (float) Math.sin(currentAngle) * 0.75f * (this. radio * this.ancho)
                + this.alto / 2.0f + reajusteY;  //eje y
        return xy;
    }

    public void ingresandoDatos(){

        SharedPreferences preferences = getContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        int id = preferences.getInt("id",0);

        int cantidad = AppDataBase.getInstance(getContext()).historyDao().getAmountPlasticAll(id);
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
                Log.i(TAG, "MyClass.getView() — get item number " + (double) (can*100)/cantidad);
                listPlace.add((double) (can*100)/cantidad );
            }
        }
        colorDePaises();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void colorDePaises() {
        int r = 240;
        int g = 240;
        int b = 240;
        Color color;
        //r, g, b >= 15 para que no sea de color negro
        for (int i = 0; i < listaPais.size(); i++){
            r = (int) (Math.random()*240) + 15;
            g = (int) (Math.random()*240) + 15;
            b = (int) (Math.random()*240) + 15;
            color = Color.valueOf(Color.rgb(r, g, b));
            listaColores.add(color);
        }
    }


}