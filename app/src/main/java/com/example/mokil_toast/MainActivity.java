package com.example.mokil_toast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView title;

    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.tv_title);
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

        // Change title when the tabs are changed
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int tabIndex = tabHost.getCurrentTab();

                // Battle tab
                if (tabIndex == 0) {
                    title.setText(R.string.text_battle);
                }

                // Class tab
                if (tabIndex == 1) {
                    title.setText(R.string.text_class);
                }
            }
        });
    }
}
