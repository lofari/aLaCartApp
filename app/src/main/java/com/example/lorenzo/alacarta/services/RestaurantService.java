package com.example.lorenzo.alacarta.services;

import com.example.lorenzo.alacarta.RestaurantsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestaurantService {

    @GET(".")
    Call<RestaurantsResponse> getRestaurant();
}
