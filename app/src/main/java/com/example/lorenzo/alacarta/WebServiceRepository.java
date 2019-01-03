package com.example.lorenzo.alacarta;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebServiceRepository implements RestoRepository {

    public void getRestaurant(final CallListener callListener ) {
        ServiceBuilder.getService().getRestaurant().enqueue(new Callback<RestaurantsResponse>() {
            @Override
            public void onResponse(Call<RestaurantsResponse> call, Response<RestaurantsResponse> response) {
                callListener.onSucess(response.body().getRestaurantes());
            }

            @Override
            public void onFailure(Call<RestaurantsResponse> call, Throwable t) {
                callListener.onError(t);
            }
        });
    }
}
