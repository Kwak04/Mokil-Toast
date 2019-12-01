package com.example.mokil_toast;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {

    String ip = "10.0.2.2";
    String URL = "http://" + ip + ":3000/";

    @GET("/battle/battleinfo")
    Call<BattleData> getBattle();
}
