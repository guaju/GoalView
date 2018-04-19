package com.guaju.goalview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.guaju.goalview.widget.GoalLine;

public class MainActivity extends AppCompatActivity {

    private GoalLine goalLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        goalLine = findViewById(R.id.goalLine);
        goalLine.setGoal(500000,2000000);
        goalLine.setYejiNum(200000);
        
        goalLine.setOrderNum(500000);
        goalLine.setStrValues("业绩：¥68,372,00","预约：¥68,372,00","0","1,000,000");
    }
}
