package com.powerdev.foodretrofitapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.powerdev.foodretrofitapp.model.Popular;

import java.util.List;

public class PopularAdepter extends RecyclerView.Adapter<PopularAdepter.PopularViewHolder> {


    private Context mContext;
    private List<Popular> popularList;

    // rec view adapter constructor
    public PopularAdepter(Context mContext, List<Popular> popularList) {
        this.mContext = mContext;
        this.popularList = popularList;
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.popular_recycler_item, parent, false);
        final PopularViewHolder viewHolder = new PopularViewHolder(view);
// recycler view set on click listner
      /*  viewHolder.popular_recycler_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(mContext,"test "+viewHolder.getAdapterPosition(),Toast.LENGTH_SHORT).show();
                //  Toast.makeText(mContext, "id" + popularList.get(viewHolder.getAdapterPosition()).getId(), Toast.LENGTH_SHORT).show();
            }
        });
        */
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, final int position) {
        holder.popularText.setText(popularList.get(position).getName());
        // use glid
        Glide.with(mContext).load(popularList.get(position).getImageUrl()).into(holder.popularImage);
        //set onclick listner
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(mContext,FoodDetails.class);
                i.putExtra("name",popularList.get(position).getName());
                i.putExtra("price",popularList.get(position).getPrice());
                i.putExtra("rating",popularList.get(position).getRating());
                i.putExtra("image",popularList.get(position).getImageUrl());
                mContext.startActivity(i);

            }
        });



    }

    @Override
    public int getItemCount() {
        return popularList.size();
    }

    // create viewHolder
    public static class PopularViewHolder extends RecyclerView.ViewHolder {

        ImageView popularImage;
        TextView popularText;


        // view holder constructor
        public PopularViewHolder(@NonNull View itemView) {

            super(itemView);
            // نقوم بتعريف عناصر item
            popularImage=itemView.findViewById(R.id.popular_image);
            popularText=itemView.findViewById(R.id.popular_name);

        }


    }
}
