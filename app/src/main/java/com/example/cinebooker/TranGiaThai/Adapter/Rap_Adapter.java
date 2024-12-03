package com.example.cinebooker.TranGiaThai.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.R;
import com.example.cinebooker.TranGiaThai.Entity.Rap_Entity;

import java.util.List;

public class Rap_Adapter extends RecyclerView.Adapter<Rap_Adapter.viewHoler> {

    private List<Rap_Entity> list;

    public Rap_Adapter() {

    }
    public Rap_Adapter(List<Rap_Entity> list)
    {
        this.list = list;
    }


    @NonNull
    @Override
    public viewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_rap_giathai, parent, false);

        return new viewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHoler holder, int position) {
        Rap_Entity Rap_entity = list.get(position);
       holder.tenrap.setText(Rap_entity.getTenrap());
       holder.item_rap.setImageResource(Rap_entity.getItem_rap());
       holder.item_map.setImageResource(Rap_entity.getItem_map());

    }

    @Override
    public int getItemCount()
    {
        if (list != null)
        {
            return list.size();
        }
        return 0;
    }



    public class viewHoler extends RecyclerView.ViewHolder {
        TextView tenrap;
        ImageView item_rap, item_map;
        public viewHoler(@NonNull View itemView) {
            super(itemView);
            tenrap = itemView.findViewById(R.id.tenrap);
            item_rap = itemView.findViewById(R.id.item_rap);
            item_map = itemView.findViewById(R.id.item_map);

        }


    }
}