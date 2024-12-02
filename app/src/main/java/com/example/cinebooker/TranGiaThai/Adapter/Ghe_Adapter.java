package com.example.cinebooker.TranGiaThai.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.R;
import com.example.cinebooker.TranGiaThai.Entity.Ghe_Entity;
import com.example.cinebooker.TranGiaThai.Entity.Rap_Entity;

import java.util.List;

public class Ghe_Adapter extends RecyclerView.Adapter<Ghe_Adapter.viewHoler> {

    private List<Ghe_Entity> list;

    public Ghe_Adapter() {

    }
    public Ghe_Adapter(List<Ghe_Entity> list)
    {
        this.list = list;
    }


    @NonNull
    @Override
    public viewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_ghe_ngoi, parent, false);

        return new viewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHoler holder, int position) {
        Ghe_Entity ghe_entity = list.get(position);
      holder.tenghe.setText(ghe_entity.getTen_ghe());
      holder.soluongghe.setText(ghe_entity.getSo_luong_ghe());
      holder.item_ghe.setImageResource(ghe_entity.getItem_ghe());

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
        TextView tenghe,soluongghe;
        ImageView item_ghe;
        public viewHoler(@NonNull View itemView) {
            super(itemView);
            tenghe = itemView.findViewById(R.id.ten_ghe);
            soluongghe = itemView.findViewById(R.id.so_luong_ghe);
            item_ghe = itemView.findViewById(R.id.items_ghe);

        }


    }
}