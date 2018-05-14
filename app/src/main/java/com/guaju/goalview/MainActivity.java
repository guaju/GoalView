package com.guaju.goalview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.guaju.goalview.widget.GoalLine;

public class MainActivity extends AppCompatActivity {

    private GoalLine goalLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        GoalLine goalLine = new GoalLine(this);
//        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) goalLine.getLayoutParams();
//        layoutParams.setMargins(10,10,10,10);
//        goalLine.setLayoutParams(layoutParams);

    }

    private void initView() {
        goalLine = findViewById(R.id.goalLine);
        goalLine.setGoal(20000,20000);
        goalLine.setYejiNum(1000);
        
        goalLine.setOrderNum(3000);
        goalLine.setStrValues("业绩：¥68,372,00","预约：¥68,372,00","0","1,000,000");
    }
}
