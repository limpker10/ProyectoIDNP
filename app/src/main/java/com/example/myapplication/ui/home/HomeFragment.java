package com.example.myapplication.ui.home;

import static com.example.myapplication.database.BitmapManager.byteToBitmap;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.myapplication.R;
import com.example.myapplication.database.AppDataBase;
import com.example.myapplication.database.entities.User;
import com.example.myapplication.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private User user_data;
    private FragmentHomeBinding binding;
    private static final String TAG = "ErrorUser";
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadDataUser(view);
        ConstraintLayout registrarProducto = view.findViewById(R.id.registrarProducto);
        registrarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.cameraFragment);
            }
        });
        ConstraintLayout infolist = view.findViewById(R.id.infolist);
        infolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.infoFragment);
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Log.i(TAG, "fragment home");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void loadDataUser(View view){
        cargarPreferencias();
        Log.i(TAG, user_data.getFirstName());
        TextView textViewName = view.findViewById(R.id.User_Name);
        ImageView a = view.findViewById(R.id.imageView);
        if (user_data.getImage() != null) {
            a.setImageBitmap(byteToBitmap(user_data.getImage()));
        }else {
            Bitmap mIcon1 = BitmapFactory.decodeResource(getResources(),R.drawable.person3);
            a.setImageBitmap(mIcon1);
        }
        String name = user_data.getFirstName() + user_data.getLastName();
        textViewName.setText(name);
    }

    private void cargarPreferencias(){
        SharedPreferences preferences = this.getActivity().getSharedPreferences("credenciasles", Context.MODE_PRIVATE);
        int id = preferences.getInt("id",0);
        Log.i(TAG, "cargando credenciales");
        user_data = AppDataBase.getInstance(getContext()).userDao().findById(id);
    }
}