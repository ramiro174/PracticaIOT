package com.example.practicaiot.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


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

    Retrofit retrofit;
    ApiAdafruit apiAdafruit;

    int Encendido;
    Button b, btnultrasonico;

    TextView txtultrasonico;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofit = RetrofitBuilder.getRetrofitInstance();
        ApiAdafruit apiAdafruit = retrofit.create(ApiAdafruit.class);
        b=findViewById(R.id.btnLed);
        btnultrasonico=findViewById(R.id.btnultrasonico);
        txtultrasonico=findViewById(R.id.txtultrasonico);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Feed f = new Feed();
                if (Encendido == 1) {
                    f.setValue("1");
                    Encendido = 0;
                } else {
                    f.setValue("0");
                    Encendido = 1;
                }
                Call<Feed> request = apiAdafruit.setDataFeed("led", f);
                request.enqueue(new Callback<Feed>() {
                    @Override
                    public void onResponse(Call<Feed> call, Response<Feed> response) {
                        if (response.isSuccessful()) {
                            Feed feed = response.body();
                            if(feed.getValue().equals("0")){
                                b.setText("Encender");
                            }else{
                                b.setText("Apagar");
                            }
                            Toast.makeText(MainActivity.this, feed.getValue(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Feed> call, Throwable t) {
                        Log.i("onFailure: ", t.getMessage());

                    }
                });


            }
        });

        btnultrasonico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Feed> request = apiAdafruit.getDataFeedLast("ultrasonico");
                request.enqueue(new Callback<Feed>() {
                    @Override
                    public void onResponse(Call<Feed> call, Response<Feed> response) {
                        if(response.isSuccessful()){
                            Feed feed=response.body();
                            txtultrasonico.setText(feed.getValue());
                        }

                    }
                    @Override
                    public void onFailure(Call<Feed> call, Throwable t) {

                    }
                });

            }
        });
    }
}