package com.example.mokil_toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
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

public class MainActivity extends AppCompatActivity {

    TextView title;
    ImageButton reload;
    TabHost tabHost;
    RecyclerView battleList, classList;

    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(RetrofitService.URL)
            .build();
    RetrofitService retrofitService = retrofit.create(RetrofitService.class);

    BattleData battleDataBody;
    ClassData classDataBody;

    Animation fadeIn;

    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.tv_title);
        reload = findViewById(R.id.btn_reload);
        tabHost = findViewById(R.id.tab_host);
        battleList = findViewById(R.id.battle_list);
        classList = findViewById(R.id.class_list);

        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

        getWindow().setStatusBarColor(getResources().getColor(R.color.colorBackground));


        // TAB

        tabHost.setup();

        // Battle tab
        TabHost.TabSpec battleTab = tabHost.newTabSpec("BattleTab");
        battleTab.setContent(R.id.battle_content);
        ImageView battleTabImage = new ImageView(this);
        battleTabImage.setImageResource(R.drawable.battle_tab_button);
        battleTab.setIndicator(battleTabImage);
        tabHost.addTab(battleTab);

        // Class tab
        TabHost.TabSpec classTab = tabHost.newTabSpec("ClassTab");
        classTab.setContent(R.id.class_content);
        ImageView classTabImage = new ImageView(this);
        classTabImage.setImageResource(R.drawable.class_tab_button);
        classTab.setIndicator(classTabImage);
        tabHost.addTab(classTab);

        // Change title when the tabs are changed
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int tabIndex = tabHost.getCurrentTab();

                // Battle tab
                if (tabIndex == 0) {
                    title.setText(R.string.text_main_battle);
                }

                // Class tab
                if (tabIndex == 1) {
                    title.setText(R.string.text_main_class);
                }
            }
        });


        // Battle list
        battleList.setHasFixedSize(true);
        battleList.setLayoutManager(new LinearLayoutManager(this));
        loadBattleInfo();

        // Class list
        classList.setHasFixedSize(true);
        classList.setLayoutManager(new LinearLayoutManager(this));
        loadClassInfo();

        // Reload button
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadBattleInfo();
                loadClassInfo();
            }
        });
    }

    public void loadBattleInfo() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        retrofitService.getBattleInfo().enqueue(new Callback<BattleData>() {
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

                    BattleListAdapter battleListAdapter = new BattleListAdapter(battleDataBody);
                    battleList.setAdapter(battleListAdapter);
                    battleList.startAnimation(fadeIn);
                } else {
                    Toast.makeText(MainActivity.this, "로드 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<BattleData> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "데이터를 불러올 수 없습니다.", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    public void loadClassInfo() {
        retrofitService.getClassInfo().enqueue(new Callback<ClassData>() {
            @Override
            public void onResponse(@NonNull Call<ClassData> call, @NonNull Response<ClassData> response) {
                classDataBody = response.body();
                Log.d(TAG, "onResponse: classDataBody = " + classDataBody);
                String message = Objects.requireNonNull(classDataBody).message;

                if (message.equals("success")) {
                    List<ClassData> classDataList = new ArrayList<>();
                    classDataList.add(classDataBody);
                    Collections.sort(classDataList, new Comparator<ClassData>() {
                        @Override
                        public int compare(ClassData lhs, ClassData rhs) {
                            return Integer.compare(lhs.results[0].class_number.compareTo(rhs.results[0].class_number), 0);
                        }
                    });

                    ClassListAdapter classListAdapter = new ClassListAdapter(classDataBody);
                    classList.setAdapter(classListAdapter);
                    classList.startAnimation(fadeIn);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ClassData> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "데이터를 불러올 수 없습니다.", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
}
