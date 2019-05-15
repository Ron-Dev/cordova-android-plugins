package com.cordova.sdk.cameracapture;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.util.Log;
import com.cordova.sdk.cameracapture.InitialActivity;
import com.entlogics.ace.teacher.R;
/**
 * This class echoes a string called from JavaScript.
 */
public class CameraCapture extends CordovaPlugin {

   private static String TAG = "CameraCapture";
	public static final String CAMERACAPTURE = "cameracapture";
    public  CallbackContext callback;
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
         if (action.equals(CAMERACAPTURE)) {
			this.startCameraCapture(callbackContext);
			return true;
		}
        return false;
    }
	
	private void startCameraCapture(CallbackContext callbackContext) {
		        Intent intent = new Intent(this.cordova.getActivity(), InitialActivity.class);
                 Log.e("CameraCapture tojam","InitialActivity");
                this.cordova.startActivityForResult(this,intent,90);
                  callback=callbackContext;
	}

public void onActivityResult(int requestCode, int resultCode, Intent intent){
    // for getting response of captured file
      Log.e("CameraCapture tojam","onActivityResult");
     boolean isVallidResponse=false; 
    if(callback!=null&&intent!=null&&intent.getStringExtra("activityResponse")!=null){
         Log.e("CameraCapture tojam",intent.getStringExtra("activityResponse"));
        String activityResponseString=intent.getStringExtra("activityResponse");
              Log.e("CameraCapture tojam",activityResponseString);
        if(activityResponseString!=null&&activityResponseString.length()>=0){
            isVallidResponse=true;
           callback.success(activityResponseString);
        }
    }
    if(!isVallidResponse)
       callback.error("Invalid erorr");
}
}
