package com.example.myapplication.fragments;


import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.navigation.Navigation;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.NavActivity;
import com.example.myapplication.R;
import com.example.myapplication.database.AppDataBase;
import com.example.myapplication.database.dao.UserDao;
import com.example.myapplication.database.entities.User;
import com.example.myapplication.properties;
import com.google.android.material.textfield.TextInputLayout;


public class Login_Fragment extends Fragment {

    private AppDataBase dataBase;
    private UserDao userDao;
    private TextInputLayout email,password;
    private String user_email,user_password;
    private CheckBox checkGuardarSesion;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataBase = AppDataBase.getInstance(getContext());
        userDao = dataBase.userDao();
        Button button = view.findViewById(R.id.button);
        TextView createAccount = view.findViewById(R.id.toregister);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        checkGuardarSesion = view.findViewById(R.id.guardar_session);

        inicializarElementos();

        if (revisarSession()){
            preferences = this.getActivity().getSharedPreferences("credenciasles", Context.MODE_PRIVATE);
            int id = preferences.getInt("id",0);

            Bundle result = new Bundle();
            result.putInt("userLoged", id);
            getParentFragmentManager().setFragmentResult("userKey", result);

            Intent intent = new Intent(getActivity(), NavActivity.class);
            startActivity(intent);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_email = email.getEditText().getText().toString();
                user_password = password.getEditText().getText().toString();
                try {
                    User user_login = userDao.findByEmail(user_email,user_password);
                    if ( user_login!= null) {

                        Intent intent = new Intent(getActivity(), NavActivity.class);
                        //intent.putExtra("user", (Serializable) user_login);
                        //properties.getInstance().setUser(user_login);
                        guardarPreferencias(user_login,checkGuardarSesion.isChecked());
                        Toast.makeText(getContext(), "Login Exitoso", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }else {
                        Toast.makeText(getContext(), "Email o Contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    }
                }catch ( SQLiteConstraintException ex) {
                    Toast.makeText(getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.registerFragment);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_, container, false);
    }

    private void guardarPreferencias(User user_data,boolean checked) {

        editor.putBoolean("session",checked);
        editor.putInt("id",user_data.getUid());
        editor.putString("nombre",user_data.getFirstName());
        editor.commit();
    }
    private boolean revisarSession(){
        return this.preferences.getBoolean("session",false);
    }
    private void inicializarElementos(){
        preferences = this.getActivity().getSharedPreferences("credenciasles", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

}