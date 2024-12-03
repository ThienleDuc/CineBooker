package com.example.cinebooker.TranGiaThai.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.R;
import com.example.cinebooker.TranGiaThai.Entity.PTTT_Entity;

import java.util.List;

public class PTTT_Adapter extends RecyclerView.Adapter<PTTT_Adapter.viewHoler> {

    private List<PTTT_Entity> list;

    public PTTT_Adapter() {

    }
    public PTTT_Adapter(List<PTTT_Entity> list)
    {
        this.list = list;
    }


    @NonNull
    @Override
    public viewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_phuong_thuc_thanh_toan, parent, false);

        return new viewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHoler holder, int position) {
        PTTT_Entity ptttEntity = list.get(position);
      holder.tenUDTT.setText(ptttEntity.getTenPTTT());
      holder.SoDu.setText(ptttEntity.getSoDu());
      holder.NapThem.setText(ptttEntity.getNapThem());
      holder.item_PTTT.setImageResource(ptttEntity.getItemThanhtoan());

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
        TextView tenUDTT,SoDu,NapThem;
        ImageView item_PTTT;
        public viewHoler(@NonNull View itemView) {
            super(itemView);
            item_PTTT = itemView.findViewById(R.id.item_thanh_toan);
            tenUDTT = itemView.findViewById(R.id.UDTT);
            SoDu = itemView.findViewById(R.id.So_du);
            NapThem = itemView.findViewById(R.id.Nap_them);

        }


    }
}