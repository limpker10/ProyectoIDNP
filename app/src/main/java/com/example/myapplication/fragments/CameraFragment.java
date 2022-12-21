package com.example.myapplication.fragments;

import static android.app.Activity.RESULT_OK;

import static com.example.myapplication.database.BitmapManager.bitmapToByte;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.database.AppDataBase;
import com.example.myapplication.database.entities.PlasticHistory;
import com.google.android.material.textfield.TextInputLayout;


public class CameraFragment extends Fragment {

    ImageView imageView;
    String[] items = AppDataBase.getInstance(getContext()).plasticDao().getAllName();
    String[] items2 =  {"Casa","Espacio Urbano","Centro Educativo","Centro Trabajo","Centro deportivo","Parques","Otros"};
    AutoCompleteTextView autoCompleteTxt,autoCompleteTxt2;
    ArrayAdapter<String> adapterItems;
    String itemTyplePlastic;
    String itemPlace;
    Bitmap imgBitmap;
    int cantidad;
    int result;
    Button register;
    private TextInputLayout addplastico;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        camaraLauncher.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
        return inflater.inflate(R.layout.fragment_camera, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = view.findViewById(R.id.imagen);
        autoCompleteTxt = view.findViewById(R.id.auto_complete_txt);
        autoCompleteTxt2 = view.findViewById(R.id.auto_complete_txt2);
        selectItemPlastic(autoCompleteTxt,items);
        selectItemPlace(autoCompleteTxt2,items2);
        //imageView = view.findViewById(R.id.imagen);
        addplastico = view.findViewById(R.id.cantidad_plastico);

        register = view.findViewById(R.id.register_plastic);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cantidad = Integer.parseInt(addplastico.getEditText().getText().toString());

                getParentFragmentManager().setFragmentResultListener("key", getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                        result = bundle.getInt("userLoged");
                    }
                });

                try {
                    AppDataBase.getInstance(getContext()).historyDao().insertAll(new PlasticHistory(1,itemTyplePlastic,itemPlace,cantidad,bitmapToByte(imgBitmap)));
                    Toast.makeText(getContext(), "Registro Exitoso", Toast.LENGTH_SHORT).show();
                }catch ( SQLiteConstraintException ex) {
                    Toast.makeText(getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(getContext(), "Registro Exitoso", Toast.LENGTH_SHORT).show();
                //AppDataBase.getInstance(getContext()).historyDao().insertAll(new PlasticHistory());
            }
        });
    }

    ActivityResultLauncher<Intent>camaraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK){
                Bundle extras = result.getData().getExtras();
                imgBitmap = (Bitmap) extras.get("data");
                imageView.setImageBitmap(imgBitmap);
            }
        }
    });

    public void selectItemPlace(AutoCompleteTextView autoCompleteTxt , String[] items){
        adapterItems = new ArrayAdapter<String>(getContext(),R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemTyplePlastic = parent.getItemAtPosition(position).toString();
                Toast.makeText(getContext(),"Item: "+itemTyplePlastic,Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void selectItemPlastic(AutoCompleteTextView autoCompleteTxt , String[] items){
        adapterItems = new ArrayAdapter<String>(getContext(),R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemPlace = parent.getItemAtPosition(position).toString();
                Toast.makeText(getContext(),"Item: "+itemPlace,Toast.LENGTH_SHORT).show();
            }
        });
    }
}