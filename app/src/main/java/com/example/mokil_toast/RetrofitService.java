package com.example.mokil_toast;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {

    String ip = "10.0.2.2";
    String URL = "http://" + ip + ":3000/";

    // 경기 정보 가져오기
    @GET("/battle/battleinfo")
    Call<BattleData> getBattleInfo();

    // 반 정보 가져오기
    @GET("/class/classinfo")
    Call<ClassData> getClassInfo();
}
