package com.example.myapplication.fragments;

import static android.app.Activity.RESULT_OK;

import static com.example.myapplication.database.BitmapManager.bitmapToByte;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.database.AppDataBase;
import com.example.myapplication.database.entities.User;
import com.google.android.material.textfield.TextInputLayout;


public class EditarFragment extends Fragment {

    private  User user_data;
    private static final int PICK_IMAGE_REQUEST = 99;
    private TextInputLayout nombres,email,password;
    private Uri imagePath;
    private Bitmap imageToStore;
    private ImageView image;
    private Button editar,editPhoto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SharedPreferences preferences = this.getActivity().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        int id = preferences.getInt("id",0);
        user_data = AppDataBase.getInstance(getContext()).userDao().findById(id);

        return inflater.inflate(R.layout.fragment_editar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nombres = view.findViewById(R.id.nombres);
        email = view.findViewById(R.id.email);
        nombres.setHint(user_data.getFirstName());
        email.setHint(user_data.getEmail());
        image = view.findViewById(R.id.imageUser);
        editPhoto = view.findViewById(R.id.editphoto);
        editar = view.findViewById(R.id.editarperfil);
        editPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDataBase.getInstance(getContext()).userDao().update(bitmapToByte(imageToStore),user_data.getUid());
                Navigation.findNavController(view).navigate(R.id.navigation_home);
            }
        });

    }
    private void chooseImage() {
        try {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, PICK_IMAGE_REQUEST);

        } catch (Exception e) {
            Toast.makeText(getContext().getApplicationContext(), "CHOOSE IMAGE" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                imagePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imagePath);
                image.setImageBitmap(imageToStore);
                AppDataBase.getInstance(getContext()).userDao().update(bitmapToByte(imageToStore),user_data.getUid());
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}