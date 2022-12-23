package com.example.myapplication.ui.dashboard;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.BarView;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentDashboardBinding;

import java.util.ArrayList;
import java.util.Collections;

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
        BarView barView= (BarView) getView().findViewById(R.id.bar_view);

        randomSet(barView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void randomSet(BarView barView){
        ArrayList<String> test = new ArrayList<String>();
        test.add("Semana 1");
        test.add("Semana 2");
        test.add("Semana 3");
        test.add("Semana 4");
        test.add("Semana 5");
        test.add("Semana 6");

        barView.setBottomTextList(test);

        ArrayList<Integer> barDataList = new ArrayList<Integer>();

        barDataList.add(40);
        barDataList.add(50);
        barDataList.add(10);
        barDataList.add(30);
        barDataList.add(70);
        barDataList.add(80);


        int limite = Collections.max(barDataList);

        barView.setDataList(barDataList, limite+(limite/4));
    }
}