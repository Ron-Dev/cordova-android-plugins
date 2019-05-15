package com.cordova.sdk.cameracapture;
import com.entlogics.ace.teacher.R;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;


/**
 * Created by sotsys016-2 on 13/8/16 in com.cnc3camera.
 */
public class PhotoVideoRedirectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photovideo_redirect);


        init();

    }
    VideoView videoView;
    private void init() {

        ImageView imgShow = (ImageView) findViewById(R.id.imgShow);
        videoView = (VideoView) findViewById(R.id.vidShow);

        if(getIntent().getStringExtra("WHO").equalsIgnoreCase("Image")){

            imgShow.setVisibility(View.VISIBLE);

            Glide.with(PhotoVideoRedirectActivity.this)
                    .load(getIntent().getStringExtra("PATH")).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_photo_cont))
                   .listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    return false;
                }
            }).into(imgShow);
        }else {

            videoView.setVisibility(View.VISIBLE);
            try {
                videoView.setMediaController(null);
                videoView.setVideoURI(Uri.parse(getIntent().getStringExtra("PATH")));
            } catch (Exception e){
                e.printStackTrace();
            }
            videoView.requestFocus();
            //videoView.setZOrderOnTop(true);
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mp) {

                    videoView.start();
                }
            });
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    videoView.start();
                }
            });
        }











    }

    @Override
    public void onBackPressed() {
        if (videoView.isPlaying()) {
            videoView.pause();
        }
        super.onBackPressed();
    }
}
