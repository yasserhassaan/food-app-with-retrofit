package com.powerdev.foodretrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class FoodDetails extends AppCompatActivity {
    String name,price,rating,image;
    ImageView imageView;
    TextView itemPrice,itemName,itemRate;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        Intent intent=getIntent();
        name=intent.getStringExtra("name");
        price=intent.getStringExtra("price");
        rating=intent.getStringExtra("rating");
        image=intent.getStringExtra("image");


        imageView=findViewById(R.id.fooodImage);
        itemName=findViewById(R.id.foodName);
        itemPrice=findViewById(R.id.priceId);
        itemRate=findViewById(R.id.rating);
        ratingBar=findViewById(R.id.ratingBar);

        itemPrice.setText(price);
        itemName.setText(name);
     //   imageView.setImageResource(Integer.parseInt(image));
        ratingBar.setRating(Float.parseFloat(rating));
        itemRate.setText(rating);

        Glide.with(getApplicationContext()).load(image).into(imageView);



    }
}
