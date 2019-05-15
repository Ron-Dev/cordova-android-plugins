package com.cordova.sdk.multiplefileupload.activity;
import android.content.Intent;
import android.os.Bundle;
import com.cordova.sdk.multiplefileupload.activity.AudioPickActivity;
import com.cordova.sdk.multiplefileupload.activity.ImagePickActivity;
import com.cordova.sdk.multiplefileupload.activity.NormalFilePickActivity;
import com.cordova.sdk.multiplefileupload.activity.VideoPickActivity;
import com.cordova.sdk.multiplefileupload.Constant;
import com.entlogics.ace.teacher.R;
import com.cordova.sdk.multiplefileupload.filter.entity.AudioFile;
import com.cordova.sdk.multiplefileupload.filter.entity.BaseFile;
import com.cordova.sdk.multiplefileupload.filter.entity.Directory;
import com.cordova.sdk.multiplefileupload.filter.entity.ImageFile;
import com.cordova.sdk.multiplefileupload.filter.entity.NormalFile;
import com.cordova.sdk.multiplefileupload.filter.entity.VideoFile;
import static com.cordova.sdk.multiplefileupload.activity.AudioPickActivity.IS_NEED_RECORDER;
import static com.cordova.sdk.multiplefileupload.activity.BaseActivity.IS_NEED_FOLDER_LIST;
import static com.cordova.sdk.multiplefileupload.activity.ImagePickActivity.IS_NEED_CAMERA;
import java.util.ArrayList;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import java.io.ByteArrayOutputStream;
public class InitialActivity extends  AppCompatActivity  {
      private static String TAG = "InitialActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   try {
        Log.v(TAG,"InitialActivity  ");
          Intent intent = getIntent();
	      String fileType =intent!=null&&intent.getStringExtra("fileType")!=null?intent.getStringExtra("fileType"):"";
         
        switch (fileType) {
            case "image":
                Intent intent1 = new Intent(this, ImagePickActivity.class);
                intent1.putExtra(IS_NEED_CAMERA, false);
                intent1.putExtra(Constant.MAX_NUMBER, 9);
                intent1.putExtra(IS_NEED_FOLDER_LIST, true);
                startActivityForResult(intent1, Constant.REQUEST_CODE_PICK_IMAGE);
                break;
            case "video":
                Intent intent2 = new Intent(this, VideoPickActivity.class);
                intent2.putExtra(IS_NEED_CAMERA, false);
                intent2.putExtra(Constant.MAX_NUMBER, 9);
                intent2.putExtra(IS_NEED_FOLDER_LIST, true);
                startActivityForResult(intent2, Constant.REQUEST_CODE_PICK_VIDEO);
                break;
            case "audio":
                Intent intent3 = new Intent(this, AudioPickActivity.class);
                intent3.putExtra(IS_NEED_RECORDER, true);
                intent3.putExtra(Constant.MAX_NUMBER, 9);
                intent3.putExtra(IS_NEED_FOLDER_LIST, true);
                startActivityForResult(intent3, Constant.REQUEST_CODE_PICK_AUDIO);
                break;
                case "file":
                Intent intent4 = new Intent(this, NormalFilePickActivity.class);
                intent4.putExtra(Constant.MAX_NUMBER, 9);
                intent4.putExtra(IS_NEED_FOLDER_LIST, true);
                intent4.putExtra(NormalFilePickActivity.SUFFIX,
                        new String[] {"xlsx", "xls", "doc", "dOcX", "ppt", ".pptx", "pdf"});
                startActivityForResult(intent4, Constant.REQUEST_CODE_PICK_FILE);
                break;
                default :
                 finish();
            }
   } catch ( Exception e ) {
      e.printStackTrace();
   }
    }

     @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Constant.REQUEST_CODE_PICK_IMAGE:
                if (resultCode == RESULT_OK) {
                    ArrayList<ImageFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_IMAGE);
                    StringBuilder builder = new StringBuilder();
                    if(list!=null){
                         for (ImageFile file : list) {
                        String path = file.getPath();
                         builder.append(path + "\n");
                    }
                        Intent intent = getIntent();
                        intent.putExtra("activityResponse", builder.toString());
                        setResult(RESULT_OK, intent);
                    }
                }
                break;
            case Constant.REQUEST_CODE_PICK_VIDEO:
                if (resultCode == RESULT_OK) {
                    ArrayList<VideoFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_VIDEO);
                    StringBuilder builder = new StringBuilder();
                     if(list!=null){
                    for (VideoFile file : list) {
                        String path = file.getPath();
                        builder.append(path + "\n");
                    }
                        Intent intent = getIntent();
                        intent.putExtra("activityResponse", builder.toString());
                        setResult(RESULT_OK, intent);
                         }
                }
                break;
            case Constant.REQUEST_CODE_PICK_AUDIO:
                if (resultCode == RESULT_OK) {
                    ArrayList<AudioFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_AUDIO);
                    StringBuilder builder = new StringBuilder();
                     if(list!=null){
                    for (AudioFile file : list) {
                        String path = file.getPath();
                        builder.append(path + "\n");
                    }
                   Intent intent = getIntent();
                        intent.putExtra("activityResponse", builder.toString());
                        setResult(RESULT_OK, intent);
                          }
                }
                break;
            case Constant.REQUEST_CODE_PICK_FILE:
                if (resultCode == RESULT_OK) {
                    ArrayList<NormalFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_FILE);
                    StringBuilder builder = new StringBuilder();
                      if(list!=null){
                    for (NormalFile file : list) {
                        String path = file.getPath();
                        builder.append(path + "\n");
                    }
                   Intent intent = getIntent();
                        intent.putExtra("activityResponse", builder.toString());
                        setResult(RESULT_OK, intent);
                         }
                }
                break;
        }
         finish();
    }

}
