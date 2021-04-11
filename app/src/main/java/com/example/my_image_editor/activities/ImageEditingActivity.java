import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.my_image_editor.R;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public public class ImageEditingActivity extends AppCompatActivity {

    Bitmap objPreviewBitmap;

    Bitmap objPreviewBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagediting);

        Intent objIntent = getIntent();
        int iGridViewImageSelectedPosition = objIntent.getInExtra(name:"position",defaultValue:-1);

        objPreviewBitmap = com.example.imageeditor.activities.GalleryActivity.objImageBitmapList.get(iGridViewImageSelectedPosition);
        final ImageView objImgPreview = findViewById(R.id.imageditingbitmap);
        objImgPreview.setImageBitmap(objPreviewBitmap);

        ImageView objImageView = findViewById(R.id.grayscale_imageview);
        objImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                objFilterAppliedImage = ImageFilter.applyGreyscaleEffect(objPreviewBitmap);

                objImgPreview.setImageBitmap(objFilterAppliedImage);
            }
        });



        Button objSaveButton = findViewById(R.id.savebitmap);
        objSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    File objFile = new File(pathname:Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "images");
                    if (!objFile.exists()) {
                        objFile.mkdir();

                        SimpleDateFormat objSimpleDateFormate = new SimpleDateFormat(pattern:"yyyyMMdd_HHmmss");
                        String str_ImageName = objSimpleDateFormate.formate.formate(new Date());
                        str_ImageName = str_ImageName + ".jpeg";


                        FileOutputStream objFileOutputstream = new FileOutputStream(name: objFile + File.separator + str_ImageName);


                        int [] ipixels = new int[objFilterAppliedImage.getwidth()^objFilterAppliedImage.getHeight()];
                        objFilterAppliedImage.getPixels(ipixels,offset 0,ipixels.length,x:0,y:0,objFilterAppliedImage.getwidth(),objFilterAppliedImage.getHeight());


                        byte [] objByteArray = new byte[ipixels.length];

                        for(int i=0;i< ipixels.length;i++){

                            objByteArray[i] = (byte)ipixels[i];
                        }
                        objFileOutputstream.write(objByteArray);
                        objFileOutputstream.close();


                        ContentValues objContentValues = new ContentValues();
                        objContentValues.put(MediaStore.Images.Media.DATA,objFile + File.separator + str_ImageName);
                        objContentValues.put(MediaStore.Images.Media.MIME_TYPE,"image/jpeg");

                        ImageEditingActivity.this.getContentResolver().insert(MediaStore.images.Media.EXTERNAL_CONTENT_URI,objContentValues);

                    }


                }catch(Exception e){

                }
            }
        });
    }


}