package com.example.my_image_editor;

import android.content.Context;
import android.content.Intent;

import com.example.my_image_editor.activities.CameraActivity;
import com.example.my_image_editor.activities.ImageEditingActivity;
import com.example.my_image_editor.activities.LogoActivity;
import com.example.my_image_editor.activities.SplashActivity;

public class ActivityStates {
    Context objContext;

    final public static int iLOGO = 1;
    final public static int iSPLASH = 2;
    final public static int iCamera= 3;

    int iCurrentScrenState = iLOGO;
    int iPosition;

    public ActivityStates(Context objContext){
        this.objContext = objContext;
        setCurrentScreenState(iLOGO);
    }
    int iPosition = -1;
        public void sendPosition(int position){
            this.iPosition = position;
        }

    public void setCurrentScreenState(int iNewScreenState)
    {
        this.iCurrentScrenState = iNewScreenState;
        switch (this.iCurrentScrenState){
            case iLOGO: Intent objIntent = new Intent(objContext, LogoActivity.class);
                objContext.startActivity(objIntent);
                break;
            case iSPLASH: Intent objSplashIntent = new Intent(objContext,SplashActivity.class);
                objContext.startActivity(objSplashIntent);
                break;
            case iCamera: Intent objCameraIntent = new Intent(objContext, CameraActivity.class);
                objContext.startActivity(objCameraIntent);
                break;
            case iImageEditing: Intent objIntentImage = new Intent(objContext, ImageEditingActivity.class);
                                objIntentImage.putExtra("position",iPosition);
                                objContext.startActivity(objIntentImage);
                 break;
        }
    }
}
