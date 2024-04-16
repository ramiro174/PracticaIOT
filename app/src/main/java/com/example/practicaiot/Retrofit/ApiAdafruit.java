package com.example.practicaiot.Retrofit;





import com.example.practicaiot.Response.Feed;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiAdafruit {


    @Headers("X-AIO-Key:aio_LaIb20FSOjsa74jKVnPVHa1USh3Y")
    @GET("{feed}/data")
    Call<List<Feed>> getDataFeed(@Path("feed") String feed);

    @Headers("X-AIO-Key:aio_LaIb20FSOjsa74jKVnPVHa1USh3Y")
    @GET("{feed}/data/last")
    Call<Feed> getDataFeedLast(@Path("feed") String feed);


    @Headers("X-AIO-Key:aio_LaIb20FSOjsa74jKVnPVHa1USh3Y")
    @POST("{feedd}/data")
    Call<Feed> setDataFeed(@Path("feedd") String feedd, @Body Feed  feed);



}
