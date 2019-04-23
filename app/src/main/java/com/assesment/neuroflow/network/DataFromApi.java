package com.assesment.neuroflow.network;

import com.assesment.neuroflow.data.ResponseData;
import com.assesment.neuroflow.utils.Common;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataFromApi {

    public void loadDataFromApi(Callback<List<ResponseData>> callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Common.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<ResponseData>> call = apiService.getUsers();
        call.enqueue(callback);



    }
}
