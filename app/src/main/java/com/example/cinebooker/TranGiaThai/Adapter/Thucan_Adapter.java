package com.example.cinebooker.TranGiaThai.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.R;
import com.example.cinebooker.TranGiaThai.Entity.Thucan_Entity;

import java.util.List;

public class Thucan_Adapter extends RecyclerView.Adapter<Thucan_Adapter.viewHoler> {

    private List<Thucan_Entity> list;

    public Thucan_Adapter() {

    }
    public Thucan_Adapter(List<Thucan_Entity> list)
    {
        this.list = list;
    }


    @NonNull
    @Override
    public viewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_bapnuoc, parent, false);

        return new viewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHoler holder, int position) {
        Thucan_Entity thucanEntity = list.get(position);
       holder.tenmon.setText(thucanEntity.getName_thucan());
       holder.gia.setText(thucanEntity.getMoney_thucan());
       holder.soluong.setText(thucanEntity.getSoluong());
       holder.item_monan.setImageResource(thucanEntity.getItem_thucan());
       holder.item_cong.setImageResource(thucanEntity.getItem_cong());
       holder.item_tru.setImageResource(thucanEntity.getItem_tru());

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
        TextView tenmon,gia,soluong;
        ImageView item_monan, item_cong,item_tru;
        public viewHoler(@NonNull View itemView) {
            super(itemView);
            tenmon = itemView.findViewById(R.id.tenmon);
            gia = itemView.findViewById(R.id.gia);
            soluong = itemView.findViewById(R.id.soluongmon);
            item_monan = itemView.findViewById(R.id.item_monan);
            item_cong = itemView.findViewById(R.id.item_cong);
            item_tru = itemView.findViewById(R.id.item_tru);
        }


    }
}