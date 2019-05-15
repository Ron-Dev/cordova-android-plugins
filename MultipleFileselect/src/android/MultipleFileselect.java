package com.cordova.sdk.multiplefileupload;
import android.content.Intent;
import android.util.Log;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import com.cordova.sdk.multiplefileupload.activity.InitialActivity;
import com.entlogics.ace.teacher.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MultipleFileselect extends CordovaPlugin { 
private static String TAG = "MultipleFileselect";
	public static final String MULTIPLEFILESELECT = "multiplefileselect";
    public  CallbackContext callback;
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
         if (action.equals(MULTIPLEFILESELECT)) {
           JSONObject arg_object = args.getJSONObject(0);
            String fileType = arg_object.getString("fileType");
			this.startMutipleFileUpload(fileType,callbackContext);
			return true;
		}
        return false;
    }
	
	private void startMutipleFileUpload(String fileType,CallbackContext callbackContext) {
		if(fileType != null && fileType.length() > 0) {
		        Intent intent = new Intent(this.cordova.getActivity(), InitialActivity.class);
                intent.putExtra("fileType", fileType);
                this.cordova.startActivityForResult(this,intent,90);
                  callback=callbackContext;
		} else  {
			callbackContext.error("Invalid erorr");
		}
	}

public void onActivityResult(int requestCode, int resultCode, Intent intent){
    // for getting response of uploaded files
    if(callback!=null&&intent!=null&&intent.getStringExtra("activityResponse")!=null){
        try{
        String activityResponseString=intent.getStringExtra("activityResponse");
        if(activityResponseString!=null&&activityResponseString.length()>=0){
           String[] paths = activityResponseString.split("\\s*\\r?\\n\\s*");
           JSONArray jsonArray = new JSONArray(paths); 
           callback.success(jsonArray);
        }
        }catch(JSONException exception){

        }
      
        

       
    }
     
}

}