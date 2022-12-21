package com.example.myapplication.adapters;

import static com.example.myapplication.database.BitmapManager.byteToBitmap;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.database.entities.PlasticHistory;
import com.example.myapplication.database.entities.PlasticType;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<PlasticHistory> eData;
    private LayoutInflater minflater;
    private Context context;

    public HistoryAdapter(List<PlasticHistory> itemList, Context context){
        this.minflater = LayoutInflater.from(context);
        this.context = context;
        this.eData = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = minflater.inflate(R.layout.list_element,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        holder.bindData(eData.get(position));
    }

    @Override
    public int getItemCount() {
        return eData.size();
    }

    public void setItems(List<PlasticHistory> items) { eData = items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView name,description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.item_image);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
        }

        void bindData(final PlasticHistory item){
            iconImage.setImageBitmap(byteToBitmap(item.getImage()));
            name.setText(item.getPlasticType());
            description.setText(item.getPlace());
        }
    }

}
