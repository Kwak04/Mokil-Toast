package com.example.mokil_toast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {

    String ip = "10.0.2.2";
    String URL = "http://" + ip + ":3000/";

    // 경기 정보 가져오기
    @GET("/battle/battleinfo")
    Call<BattleData> getBattleInfo();

    // 반별 경기 정보 가져오기
    @GET("/class/battleinfo")
    Call<BattleData> getClassBattleInfo(@Query("class_number") String class_number);

    // 반 정보 가져오기
    @GET("/class/classinfo")
    Call<ClassData> getClassInfo();
}
