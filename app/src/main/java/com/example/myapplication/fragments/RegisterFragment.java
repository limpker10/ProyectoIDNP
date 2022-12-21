package com.example.myapplication.fragments;

import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.database.AppDataBase;
import com.example.myapplication.database.dao.UserDao;
import com.example.myapplication.database.entities.User;
import com.google.android.material.textfield.TextInputLayout;

import java.sql.SQLClientInfoException;

public class RegisterFragment extends Fragment {
    private AppDataBase dataBase;
    private UserDao userDao;
    private static final String TAG = "myedittext";
    TextInputLayout nombres,apellidos,email,password;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataBase = AppDataBase.getInstance(getContext());
        userDao = dataBase.userDao();
        ImageView backLogin = view.findViewById(R.id.backLogin);
        Button registro = view.findViewById(R.id.registrar);
        nombres = view.findViewById(R.id.nombres);
        apellidos = view.findViewById(R.id.apellidos);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);

        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.login_Fragment);
            }
        });
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname = nombres.getEditText().getText().toString();
                String lastname = apellidos.getEditText().getText().toString();
                String email_ = email.getEditText().getText().toString();
                String pass = password.getEditText().getText().toString();
                try {
                    if (firstname!=null & lastname!=null & email_!= null & pass != null){
                        User user = new User(firstname,lastname,email_,pass);
                        userDao.insertAll(user);
                        Toast.makeText(getContext(), "Registro existoso", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).navigate(R.id.login_Fragment);
                    }else {
                        Toast.makeText(getContext(), "Todos los campos deben ser completados", Toast.LENGTH_SHORT).show();
                    }

                }catch ( SQLiteConstraintException ex) {
                    Toast.makeText(getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
}