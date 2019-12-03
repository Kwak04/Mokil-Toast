package com.example.mokil_toast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class ClassInfoActivity extends AppCompatActivity {

    TextView classNumber, win, voteRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_info);

        getWindow().setStatusBarColor(getResources().getColor(R.color.colorBackground));

        classNumber = findViewById(R.id.tv_class_number);
        win = findViewById(R.id.tv_win);
        voteRate = findViewById(R.id.tv_vote_rate);


        // getIntent
        int classNumberValue = Objects.requireNonNull(getIntent().getExtras()).getInt("classNumber");
        int winValue = getIntent().getExtras().getInt("win");

        // Title
        String classNumberText = classNumberValue + "반";
        classNumber.setText(classNumberText);

        // Main
        String winText = winValue + "회";
        win.setText(winText);

        // Battle list

    }
}
