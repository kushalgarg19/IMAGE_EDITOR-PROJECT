package com.example.imageeditor.activities;

import android.Manifest;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Adapter;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.imageeditor.ActivityStates;
import com.example.imageeditor.R;
import com.example.imageeditor.adapter.GridViewAdapter;
import com.example.imageeditor.adapter.RecyclerViewAdapterClass;

import java.util.ArrayList;


public class GalleryActivity extends AppCompatActivity {
    String [] strPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE};

    ArrayList<String> objImagePathArrayList = new ArrayList<String>();
    ArrayList<Bitmap> objImageBitmapList = new ArrayList<Bitmap>();
    ArrayList<String> objImageNameList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallerymain);

        LoadImagesFromGallery();

        if(ContextCompat.checkSelfPermission(context: this,strPermissions[0]) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity[: this,strPermissions,requestCode:1);
            Log.e(tag:"PERMISSION_TAG",msg:"Asking permission");

        }else{
            Log.e(tag:"PERMISSION_TAG",msg:"permission granted");
            LoadImagesFromGallery();
        }


        Log.e(tag:"PERMISSION_TAG",msg:"in GalleryActivity");
    }

    @Override
    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);

        if(requestCode == 1){

            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){

                LoadImagesFromGallery();
            }
        }
    }

    public void LoadImagesFromGallery() {

        String[] strProjection = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.DISPLAY_NAME, MediaStore.Images.Media._ID};

        Cursor objCursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, strProjection, queryArgs:
        null,
                cancellationSignal:null);
        objCursor.moveToFirst();


        while (objCursor.moveToNext()) {
            String strImagePath = objCursor.getString(objCursor.getColumnIndex(MediaStore.Images.Media.DATA));

            objImagePathArrayList.add(strImagePath);
            objImageBitmapList.add(BitmapFactory.decodeFile(strImagePath));
            objImageNameList.add(objCursor.getString(objCursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)));


        }
        objCursor.close();

        GridView objGridView = findViewById(R.id.gridview);

        objGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClickListener(AdapterView<?> parent, View view, int position, long id) {

                ActivityStates objActivityStates = new ActivityStates(objContext:
                GalleryActivity.this);

                objActivityStates.sendPosition(position);
                objActivityStates.setCurrentScreenState(ActivityStates.iImageEditing);


            }

        });


        objGridView.setHorizontalSpacing(5);
        objGridView.setNumColumns(2);
        objGridView.setVerticalSpacing(5);

        objGridView.setAdapter(new GridViewAdapter(objContext:this, this.objImageBitmapList, objImageNameList));

    }


}
