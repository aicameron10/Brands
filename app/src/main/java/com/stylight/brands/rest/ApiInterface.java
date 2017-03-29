package com.stylight.brands.rest;


import com.stylight.brands.model.BrandResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {

    @GET("brands")
    Call<BrandResponse> getBrands(@Query("apiKey") String apiKey);



}