package com.assesment.neuroflow.network;

import com.assesment.neuroflow.data.ResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("ryanneuroflow/370d19311602c091928300edd7a40f66/raw/1865ae6004142553d8a6c6ba79ccb511028a2cba/names.json")
    Call<List<ResponseData>> getUsers();

}
