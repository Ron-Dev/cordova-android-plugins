package com.cordova.sdk.cameracapture;
import android.content.Intent;
import com.entlogics.ace.teacher.R;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;

import android.Manifest;
import android.hardware.Camera;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
public class InitialActivity extends  AppCompatActivity  {
      private static String TAG = "InitialActivity";
      private RunTimePermission runTimePermission;
      
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   try {
         setContentView(R.layout.activity_start);

        runTimePermission = new RunTimePermission(this);
        runTimePermission.requestPermission(new String[]{Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, new RunTimePermission.RunTimePermissionListener() {

            @Override
            public void permissionGranted() {
                // First we need to check availability of play services
              //  startActivity();
              Log.e("InitialActivity tojam", "WhatsappCameraActivity");
                startActivityForResult(new Intent(InitialActivity.this,WhatsappCameraActivity.class),91);
            }

            @Override
            public void permissionDenied() {

                finish();
            }
        });
   } catch ( Exception e ) {
      e.printStackTrace();
   }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         Log.e("InitialActivity tojam", "onActivityResult");
           if(data!=null&&data.getStringExtra("resultCaptureFile")!=null){
                   String fileName = data.getStringExtra("resultCaptureFile");
                    Log.e("InitialActivity tojam",fileName);
                        Intent intent = getIntent();
                        intent.putExtra("activityResponse", fileName);
                        setResult(RESULT_OK, intent);
           }
         finish();
    }


 @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(runTimePermission!=null){
            runTimePermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

}
