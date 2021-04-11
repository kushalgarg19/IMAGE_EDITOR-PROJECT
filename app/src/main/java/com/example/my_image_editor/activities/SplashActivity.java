package com.example.my_image_editor.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.my_image_editor.ActivityStates;
import com.example.my_image_editor.MainActivity;
import com.example.my_image_editor.R;

import java.util.Timer;
import java.util.TimerTask;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class SplashActivity extends AppCompatActivity {
    String [] strPersmission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        if(ContextCompat.checkSelfPermission(this,strPersmission[0])!= PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, strPersmission, 1);
        }
        else{
            Timer objTimer = new Timer();
            TimerTask objTimerTask = new TimerTask() {
                @Override
                public void run() {
                    MainActivity.objActivityStates.setCurrentScreenState(ActivityStates.iCamera);
                }
            };
            objTimer.schedule(objTimerTask,3000);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode ==1){
            if(grantResults[0]==PERMISSION_GRANTED){
                Timer objTimer = new Timer();
                TimerTask objTimerTask = new TimerTask() {
                    @Override
                    public void run() {
                        MainActivity.objActivityStates.setCurrentScreenState(ActivityStates.iCamera);
                    }
                };
                objTimer.schedule(objTimerTask,3000);
            }
        }
    }
}
