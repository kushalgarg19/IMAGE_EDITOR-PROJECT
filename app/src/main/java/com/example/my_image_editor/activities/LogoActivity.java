package com.example.my_image_editor.activities;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.my_image_editor.ActivityStates;
import com.example.my_image_editor.MainActivity;
import com.example.my_image_editor.R;

import java.util.Timer;
import java.util.TimerTask;

public class LogoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo_main);


        Timer objTimer = new Timer();
        TimerTask objTimerTask = new TimerTask() {
            @Override
            public void run() {
                MainActivity.objActivityStates.setCurrentScreenState(ActivityStates.iSPLASH);
            }
        };
        objTimer.schedule(objTimerTask,3000);
    }
}
