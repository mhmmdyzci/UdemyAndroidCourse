package com.mhmmdyzci.javamaps.adapter;

import android.content.Intent;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Insert;

import com.mhmmdyzci.javamaps.databinding.RcylerRowBinding;
import com.mhmmdyzci.javamaps.model.Place;
import com.mhmmdyzci.javamaps.view.MapsActivity;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceHolder> {



    List<Place> placeList;
    public PlaceAdapter(List<Place> placeList){
        this.placeList = placeList;
    }

    @NonNull
    @Override
    public PlaceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Bizden bir placeHolder döndürmemizi bekliyor
        RcylerRowBinding rcylerRowBinding = RcylerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new PlaceHolder(rcylerRowBinding);
    }

    @Override
    // kaç tane mekan varsa o kadarlık recycler row oluştur
    public int getItemCount() {

        return placeList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceHolder holder, int position) {
        //Bağlanınca ne olcak kısmı
        holder.rcylerRowBinding.recyclerViewTextView.setText(placeList.get(position).name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), MapsActivity.class);
                intent.putExtra("info","old");
                intent.putExtra("place",placeList.get(position));


                holder.itemView.getContext().startActivity(intent);

            }
        });

    }

    //PlaceHolder görünüm tutucu (ViewHolder)
    public class PlaceHolder extends RecyclerView.ViewHolder{
        //(1A)
        RcylerRowBinding rcylerRowBinding;
        public PlaceHolder(RcylerRowBinding rcylerRowBinding){
            super(rcylerRowBinding.getRoot());
            this.rcylerRowBinding = rcylerRowBinding;
        }
    }

}
