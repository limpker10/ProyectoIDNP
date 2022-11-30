package com.example.myapplication.ui.dashboard;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.database.DataBaseHelper;
import com.example.myapplication.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Button add_button = view.findViewById(R.id.button);
        EditText nombre = view.findViewById(R.id.nombre);
        EditText email = view.findViewById(R.id.email);
        DataBaseHelper conn=new DataBaseHelper(getActivity());
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=conn.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("Nombre", String.valueOf(nombre));
                values.put("Email", String.valueOf(email));
                Long idResultante=db.insert("Usuario",null,values);
                if(idResultante == -1){
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "Added Successfully!", Toast.LENGTH_SHORT).show();
                }
                db.close();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}