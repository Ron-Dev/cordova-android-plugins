package com.cordova.sdk.multiplefileupload.filter;

import android.support.v4.app.FragmentActivity;

import com.cordova.sdk.multiplefileupload.filter.callback.FileLoaderCallbacks;
import com.cordova.sdk.multiplefileupload.filter.callback.FilterResultCallback;
import com.cordova.sdk.multiplefileupload.filter.entity.AudioFile;
import com.cordova.sdk.multiplefileupload.filter.entity.ImageFile;
import com.cordova.sdk.multiplefileupload.filter.entity.NormalFile;
import com.cordova.sdk.multiplefileupload.filter.entity.VideoFile;

import static com.cordova.sdk.multiplefileupload.filter.callback.FileLoaderCallbacks.TYPE_AUDIO;
import static com.cordova.sdk.multiplefileupload.filter.callback.FileLoaderCallbacks.TYPE_FILE;
import static com.cordova.sdk.multiplefileupload.filter.callback.FileLoaderCallbacks.TYPE_IMAGE;
import static com.cordova.sdk.multiplefileupload.filter.callback.FileLoaderCallbacks.TYPE_VIDEO;

/**
 * Created by Vincent Woo
 * Date: 2016/10/11
 * Time: 10:19
 */

public class FileFilter {
    public static void getImages(FragmentActivity activity, FilterResultCallback<ImageFile> callback){
        activity.getSupportLoaderManager().initLoader(0, null,
                new FileLoaderCallbacks(activity, callback, TYPE_IMAGE));
    }

    public static void getVideos(FragmentActivity activity, FilterResultCallback<VideoFile> callback){
        activity.getSupportLoaderManager().initLoader(1, null,
                new FileLoaderCallbacks(activity, callback, TYPE_VIDEO));
    }

    public static void getAudios(FragmentActivity activity, FilterResultCallback<AudioFile> callback){
        activity.getSupportLoaderManager().initLoader(2, null,
                new FileLoaderCallbacks(activity, callback, TYPE_AUDIO));
    }

    public static void getFiles(FragmentActivity activity,
                                FilterResultCallback<NormalFile> callback, String[] suffix){
        activity.getSupportLoaderManager().initLoader(3, null,
                new FileLoaderCallbacks(activity, callback, TYPE_FILE, suffix));
    }
}
