package com.njz.letsgoappguides.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.njz.letsgoappguides.Bean.MySelfInfo;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.customview.widget.videoView.CustomVideoView;
import com.njz.letsgoappguides.ui.activites.LoginActivity;

public class Splash2Activity extends BaseActivity implements View.OnClickListener{


    CustomVideoView videoView;
    TextView next;

    @Override
    protected void initView() {

        getWindow().setFormat(PixelFormat.TRANSPARENT);
        videoView = (CustomVideoView) findViewById(R.id.videoView);
        next = (TextView) findViewById(R.id.to_next);

        next.setOnClickListener(this);
//        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.media));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                    @Override
                    public boolean onInfo(MediaPlayer mp, int what, int extra) {
                        if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                            View placeholder = findViewById(R.id.placeholder);
                            placeholder.setVisibility(View.GONE);
                            return true;
                        }

                        return false;
                    }
                });
            }
        });

        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                toHome();
            }
        });
    }


    private void toHome() {
        Intent intent;
        if(MySelfInfo.getInstance().isLogin()){
            intent = new Intent(this, MainActivity.class);
        }else{
            intent = new Intent(this, LoginActivity.class);
        }
        startActivity(intent);
        this.finish();
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.to_next:
                toHome();
                break;
            default:
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash2;
    }

}
