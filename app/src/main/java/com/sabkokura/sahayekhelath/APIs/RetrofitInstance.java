package com.sabkokura.sahayekhelath.APIs;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;
    private static String BASEURL ="http://20.41.221.66:7000/";

    public static Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        if(retrofit == null){
            retrofit = new Retrofit.Builder().
                    baseUrl(BASEURL)
                    .client(client).
                    addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofit;
    }
    public static WebApi getService(){
        WebApi webApi = getRetrofit().create(WebApi.class);

        return webApi;
    }


}
