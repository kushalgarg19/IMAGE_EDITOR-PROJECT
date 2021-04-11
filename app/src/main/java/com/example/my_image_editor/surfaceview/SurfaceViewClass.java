package com.example.my_image_editor.surfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class SurfaceViewClass extends SurfaceView implements SurfaceHolder.Callback {

    SurfaceHolder objSurfaceHolder;
    Camera objCamera;

    public SurfaceViewClass(Context objContext) {
        super(objContext);

        objSurfaceHolder = getHolder();
        objSurfaceHolder.addCallback(this);
        objSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        try {
            objCamera = Camera.open();
            objCamera.setErrorCallback(new Camera.ErrorCallback() {
                @Override
                public void onError(int error, Camera camera) {
                    Log.e("Camera_ERRROR1", "Camera error is " + error);
                }
            });

            objCamera.setPreviewDisplay(objSurfaceHolder);
            objCamera.startPreview();

        } catch (Exception e) {
            Log.e("Camera_ERRROR1", "Camera error in catch is " + e);
        }
    }

    public void takePicture() {
        try {
            objCamera.takePicture(null, objPictureCallBack, null);
        } catch (Exception e) {
            Log.e("CAMERA_TAG", "Error is " + e);
        }
    }

    Camera.PictureCallback objPictureCallBack = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            Bitmap objBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

            Log.e("PICTURE_TAKEN_TAG", "Picture taken width " + objBitmap.getWidth());
        }
    };

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        try {
            objCamera.stopPreview();
            objCamera.setPreviewDisplay(null);
            objCamera = null;
        } catch (Exception e) {
            Log.e("Camera_ERRROR", "Camera error in catch for destroy is " + e);
        }
    }
}
