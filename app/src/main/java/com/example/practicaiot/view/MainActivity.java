package com.example.practicaiot.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

import com.example.practicaiot.R;
import com.example.practicaiot.Response.Feed;
import com.example.practicaiot.Retrofit.ApiAdafruit;
import com.example.practicaiot.Retrofit.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Retrofit  retrofit;
    ApiAdafruit apiAdafruit;

    int Encendido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofit= RetrofitBuilder.getRetrofitInstance();
        ApiAdafruit apiAdafruit= retrofit.create(ApiAdafruit.class);
         findViewById(R.id.btnLed).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Feed  f=new Feed();
                 if(Encendido==1) {
                     f.setValue("1");
                     Encendido=0;
                 }else{
                     f.setValue("0");
                     Encendido=1;
                 }


                 Call<Feed>  request= apiAdafruit.setDataFeed("led",f);

                 request.enqueue(new Callback<Feed>() {
                     @Override
                     public void onResponse(Call<Feed> call, Response<Feed> response) {

                         if(response.isSuccessful()){
                             Feed  Values= response.body();
                             Log.i("datos",Values.getValue()+"");

                         }

                     }

                     @Override
                     public void onFailure(Call<Feed> call, Throwable t) {
                         Log.i("onFailure: ",t.getMessage());

                     }
                 });


             }
         });
    }
}