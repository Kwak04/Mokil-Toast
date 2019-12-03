package com.example.mokil_toast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class ClassInfoActivity extends AppCompatActivity {

    TextView classNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_info);

        getWindow().setStatusBarColor(getResources().getColor(R.color.colorBackground));

        classNumber = findViewById(R.id.tv_class_number);


        // Title
        int classNumberValue = Objects.requireNonNull(getIntent().getExtras()).getInt("classNumber");
        String classNumberText = classNumberValue + "반";
        classNumber.setText(classNumberText);
    }
}
