package com.example.mokil_toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClassInfoActivity extends AppCompatActivity {

    LinearLayout mainPanel;
    TextView classNumber, win, voteRate;
    RecyclerView battleList;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitService.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    RetrofitService retrofitService = retrofit.create(RetrofitService.class);

    BattleData battleDataBody;

    Animation fadeIn;

    String TAG = "ClassInfoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_info);

        getWindow().setStatusBarColor(getResources().getColor(R.color.colorBackground));

        mainPanel = findViewById(R.id.layout_main);
        classNumber = findViewById(R.id.tv_class_number);
        win = findViewById(R.id.tv_win);
        voteRate = findViewById(R.id.tv_vote_rate);
        battleList = findViewById(R.id.battle_list);

        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);


        // getIntent
        int classNumberValue = Objects.requireNonNull(getIntent().getExtras()).getInt("classNumber");
        int winValue = getIntent().getExtras().getInt("win");

        // Title
        String classNumberText = classNumberValue + "반";
        classNumber.setText(classNumberText);

        // Main
        mainPanel.startAnimation(fadeIn);  // Main panel animation
        String winText = winValue + "회";
        win.setText(winText);



        // Battle list

        battleList.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        battleList.setLayoutManager(linearLayoutManager);

        retrofitService.getClassBattleInfo(Integer.toString(classNumberValue)).enqueue(new Callback<BattleData>() {
            @Override
            public void onResponse(@NonNull Call<BattleData> call, @NonNull Response<BattleData> response) {
                battleDataBody = response.body();
                Log.d(TAG, "onResponse: battleDataBody = " + battleDataBody);
                String message = Objects.requireNonNull(battleDataBody).message;

                if (message.equals("success")) {
                    List<BattleData> battleDataList = new ArrayList<>();
                    battleDataList.add(battleDataBody);
                    Collections.sort(battleDataList, new Comparator<BattleData>() {
                        @Override
                        public int compare(BattleData lhs, BattleData rhs) {
                            return Integer.compare(lhs.results[0].seq.compareTo(rhs.results[0].seq), 0);
                        }
                    });

                    // Reverse
                    linearLayoutManager.setReverseLayout(true);
                    linearLayoutManager.setStackFromEnd(true);

                    // Animation delay
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            BattleListAdapter battleListAdapter = new BattleListAdapter(battleDataBody);
                            battleList.setAdapter(battleListAdapter);
                            battleList.startAnimation(fadeIn);
                        }
                    }, 500);
                } else {
                    Toast.makeText(ClassInfoActivity.this, "로드 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<BattleData> call, @NonNull Throwable t) {
                Toast.makeText(ClassInfoActivity.this, "데이터를 불러올 수 없습니다.", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
}
