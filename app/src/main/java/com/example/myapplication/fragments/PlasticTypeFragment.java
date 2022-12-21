package com.example.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.ListAdapter;
import com.example.myapplication.database.entities.PlasticType;

import java.util.ArrayList;
import java.util.List;

public class PlasticTypeFragment extends Fragment {

    List<PlasticType> elements;
    RecyclerView recyclerView;
    ListAdapter listAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_typeplastic, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        elements = new ArrayList<>();
        elements.add(new PlasticType("Botella de Plastico","#775447" ,"e.g. agua, jugos, gaseosas, etc"));
        elements.add(new PlasticType("Envoltorio de Plastico","#680447", "e.g. dulces, caramelos, goma de mascar, etc "));
        elements.add(new PlasticType("Bolsa de Plastico", "#03a9f4","e.g. supermercado, ......, ...., etc "));
        //ListAdapter listAdapter = new ListAdapter(elements,getContext());
        listAdapter = new ListAdapter(elements,getContext());
        //RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
