package com.example.myapplication.fragments;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.NavActivity;
import com.example.myapplication.R;
import com.example.myapplication.database.AppDataBase;
import com.example.myapplication.database.dao.UserDao;
import com.example.myapplication.database.entities.User;

import java.io.Serializable;

public class Login_Fragment extends Fragment {
    private AppDataBase dataBase;
    private UserDao userDao;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataBase = AppDataBase.getInstance(getContext());
        userDao = dataBase.userDao();
        Button button = view.findViewById(R.id.button);
        TextView createAccount = view.findViewById(R.id.toregister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    User user_login = userDao.findByEmail("jcanov@unsa.edu.pe","vd9mnzbd");
                    if ( user_login!= null) {
                        Toast.makeText(getContext(), "Login Exitoso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), NavActivity.class);
                        intent.putExtra("user", user_login.getFirstName());
                        startActivity(intent);
                    }else {
                        Toast.makeText(getContext(), "Email o password incorrectos", Toast.LENGTH_SHORT).show();
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

}