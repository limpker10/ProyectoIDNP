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
import com.example.myapplication.adapters.ListAdapter;
import com.example.myapplication.database.entities.Assistance;
import com.example.myapplication.database.entities.PlasticType;

import java.util.ArrayList;
import java.util.List;

public class InfoFragment extends Fragment {

    List<Assistance> elements;
    RecyclerView recyclerView;
    InfoAdapter listAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        elements = new ArrayList<>();
        elements.add(new Assistance("Botella de Plastico","#775447" ,"e.g. agua, jugos, gaseosas, etc"));
        elements.add(new Assistance("Envoltorio de Plastico","#680447", "e.g. dulces, caramelos, goma de mascar, etc "));
        elements.add(new Assistance("Bolsa de Plastico", "#03a9f4","e.g. supermercado, ......, ...., etc "));
        listAdapter = new InfoAdapter(elements,getContext(), new InfoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Assistance item) {
                moveToDescription(item,view);
            }

        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void moveToDescription(Assistance item, View view) {
        Bundle result = new Bundle();
        result.putSerializable("info",item);
        getParentFragmentManager().setFragmentResult("requestKey", result);
        Navigation.findNavController(view).navigate(R.id.descriptionFragment);
    }
}