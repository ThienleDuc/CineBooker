package com.example.cinebooker.TranGiaThai.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.R;
import com.example.cinebooker.TranGiaThai.Entity.Khunggiochieu_Entity;

import java.util.List;

public class Khunggiochieu_Adapter extends RecyclerView.Adapter<Khunggiochieu_Adapter.viewHoler> {

    private List<Khunggiochieu_Entity> list;

    public Khunggiochieu_Adapter() {

    }
public Khunggiochieu_Adapter(List<Khunggiochieu_Entity> list)
        {
        this.list = list;
        }


    @NonNull
    @Override
    public Khunggiochieu_Adapter.viewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_khunggiochieu, parent, false);

        return new viewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Khunggiochieu_Adapter.viewHoler holder, int position) {
        Khunggiochieu_Entity khunggiochieu_entity = list.get(position);
            holder.giochieu.setText(khunggiochieu_entity.getGiochieu());
            holder.chotrong.setText(khunggiochieu_entity.getChotrong());

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
                    TextView giochieu, chotrong;
                    public viewHoler(@NonNull View itemView) {
                super(itemView);
                giochieu = itemView.findViewById(R.id.giochieu);
                chotrong = itemView.findViewById(R.id.chotrong);

            }


                }
}