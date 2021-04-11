package com.example.my_image_editor.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.my_image_editor.R;
import com.example.my_image_editor.surfaceview.SurfaceViewClass;

public class CameraActivity extends AppCompatActivity {
    CameraActivity objCameraActivity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_main);
        objCameraActivity = this;
        try {
            final SurfaceViewClass objSurfaceViewClass = new SurfaceViewClass(this);
            RelativeLayout relative = findViewById(R.id.relative);
            relative.addView(objSurfaceViewClass);

            final ImageView objImageView = findViewById(R.id.imagepictuetaken);
              Button objTakePicture = findViewById(R.id.take_picture);
              objTakePicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    objSurfaceViewClass.takePicture();
                }
            });
        }catch (Exception e){
            Log.e("CAMERA_ERROR","Error "+e.toString());
        }

    }
}
