package com.example.lorenzo.alacarta;

import com.example.lorenzo.alacarta.services.RestaurantService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {


        private static final String URL = "https://api.myjson.com/bins/lcfou/";

        private static HttpLoggingInterceptor logger =
                new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

        private static OkHttpClient okHttp = new OkHttpClient.Builder().addInterceptor(logger).build();

        private static RestaurantService buildClient() {
            return new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttp)
                    .build().create(RestaurantService.class);
        }

        private static RestaurantService client = buildClient();
        public static RestaurantService getService(){
            return client;
        }


}
