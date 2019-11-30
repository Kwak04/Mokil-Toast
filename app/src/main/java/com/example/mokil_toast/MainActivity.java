package com.example.mokil_toast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = findViewById(R.id.tab_host);

        getWindow().setStatusBarColor(getResources().getColor(R.color.colorBackground));


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
    }
}
