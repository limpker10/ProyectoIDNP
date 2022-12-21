package com.example.myapplication.adapters;

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
import com.example.myapplication.database.entities.Assistance;
import com.example.myapplication.database.entities.PlasticType;

import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {

    private List<Assistance> eData;
    private LayoutInflater minflater;
    private Context context;
    final InfoAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Assistance item);
    }

    public InfoAdapter(List<Assistance> itemList, Context context, InfoAdapter.OnItemClickListener listener){
        this.minflater = LayoutInflater.from(context);
        this.context = context;
        this.eData = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = minflater.inflate(R.layout.info_element,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoAdapter.ViewHolder holder, int position) {
        holder.bindData(eData.get(position));
    }

    @Override
    public int getItemCount() {
        return eData.size();
    }

    public void setItems(List<Assistance> items) { eData = items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView name,description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.item_image);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
        }

        void bindData(final Assistance item){
            iconImage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            name.setText(item.getTitle());
            description.setText(item.getInfo());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }
    }

}
