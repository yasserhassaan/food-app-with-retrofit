package com.powerdev.foodretrofitapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.powerdev.foodretrofitapp.model.FoodData;
import com.powerdev.foodretrofitapp.model.Popular;
import com.powerdev.foodretrofitapp.model.Recommended;
import com.powerdev.foodretrofitapp.retrofit.ApiInterface;
import com.powerdev.foodretrofitapp.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView popularRecyclerView,recommendedRecyclerView;
    PopularAdepter popularAdepter;
    RecommendAdepter recommendAdepter;


    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiInterface =RetrofitClient.getRetrofitInstans().create(ApiInterface.class);
        Call<List<FoodData>> call=apiInterface.getAllData();
        call.enqueue(new Callback<List<FoodData>>() {
            @Override
            public void onResponse(Call<List<FoodData>> call, Response<List<FoodData>> response) {
                List<FoodData> foodDataList=response.body();
                getPopularData(foodDataList.get(0).getPopular());
                getRecommendData(foodDataList.get(0).getRecommended());

            }

            @Override
            public void onFailure(Call<List<FoodData>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "server not res"+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void  getPopularData(List<Popular>popularList){
        popularRecyclerView=findViewById(R.id.popular_recycler);
        popularAdepter=new PopularAdepter(this,popularList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        popularRecyclerView.setLayoutManager(layoutManager);
        popularRecyclerView.setAdapter(popularAdepter);

    }
    public void getRecommendData(List<Recommended> recommendedList){
        recommendedRecyclerView=findViewById(R.id.recomend_recycler);
        recommendAdepter=new RecommendAdepter(this,recommendedList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recommendedRecyclerView.setLayoutManager(layoutManager);
        recommendedRecyclerView.setAdapter(recommendAdepter);

    }
}
