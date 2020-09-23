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
import com.powerdev.foodretrofitapp.model.Recommended;

import java.util.List;

public class RecommendAdepter extends RecyclerView.Adapter<RecommendAdepter.RecommendViewHolder> {


    Context mContext;
    List<Recommended> recommendedList;

    // rec view adapter constructor
    public RecommendAdepter(Context mContext, List<Recommended> mRecommendedData) {
        this.mContext = mContext;
        this.recommendedList = mRecommendedData;
    }

    @NonNull
    @Override
    public RecommendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recommended_recycler_item, parent, false);
        final RecommendViewHolder viewHolder = new RecommendViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendViewHolder holder, final int position) {
        holder.recomendPrice.setText(recommendedList.get(position).getPrice());
        holder.recommendCharge.setText(recommendedList.get(position).getDeliveryCharges());
        holder.recommendDelivryTime.setText(recommendedList.get(position).getDeliveryTime());
        holder.recommendRate.setText(recommendedList.get(position).getRating());
        holder.recommendName.setText(recommendedList.get(position).getName());

        Glide.with(mContext).load(recommendedList.get(position).getImageUrl()).into(holder.recomendImageView);
//set onclick listner
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(mContext,FoodDetails.class);
                i.putExtra("name",recommendedList.get(position).getName());
                i.putExtra("price",recommendedList.get(position).getPrice());
                i.putExtra("rating",recommendedList.get(position).getRating());
                i.putExtra("image",recommendedList.get(position).getImageUrl());
                mContext.startActivity(i);

            }
        });



    }

    @Override
    public int getItemCount() {
        return recommendedList.size();
    }

    // create viewHolder
    public static class RecommendViewHolder extends RecyclerView.ViewHolder {

        ImageView recomendImageView;
        TextView recommendName,recommendRate,recommendCharge,recommendDelivryTime,recomendPrice;


        // view holder constructor
        public RecommendViewHolder(@NonNull View itemView) {

            super(itemView);

            recomendImageView = itemView.findViewById(R.id.recommended_image);
            recommendName = itemView.findViewById(R.id.recommended_name);
            recommendRate = itemView.findViewById(R.id.recommended_rating);
            recommendDelivryTime = itemView.findViewById(R.id.recommended_delivery_time);
            recommendCharge = itemView.findViewById(R.id.delivery_type);
            recomendPrice = itemView.findViewById(R.id.recommended_price);
            // نقوم بتعريف عناصر item
        }


    }
}
