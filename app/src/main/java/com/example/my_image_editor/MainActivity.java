package com.example.my_image_editor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.my_image_editor.R;
import com.example.my_image_editor.ActivityStates;

public class MainActivity extends AppCompatActivity {
    public static ActivityStates objActivityStates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        objActivityStates = new ActivityStates(this);
    }
}