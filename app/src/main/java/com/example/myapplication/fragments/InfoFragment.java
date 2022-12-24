package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapters.InfoAdapter;
import com.example.myapplication.database.AppDataBase;
import com.example.myapplication.database.entities.PlasticType;

import java.util.ArrayList;
import java.util.List;

public class InfoFragment extends Fragment {
    //AppDataBase.getInstance(getContext()).plasticDao().getAll();
    private List<PlasticType> elements;
    private RecyclerView recyclerView;
    private InfoAdapter listAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info, container, false);
        elements = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        elements = AppDataBase.getInstance(getContext()).plasticDao().getAll();
        //elements = AppDataBase.getInstance(getContext()).plasticDao().getAll();
        listAdapter = new InfoAdapter(elements, getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void moveToDescription(PlasticType item, View view) {
        Bundle result = new Bundle();
        result.putSerializable("info", item);
        getParentFragmentManager().setFragmentResult("requestKey", result);
        Navigation.findNavController(view).navigate(R.id.descriptionFragment);
    }
}