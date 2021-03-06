package com.amanciodrp.yellotaxi;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;

import com.amanciodrp.yellotaxi.customeractivity.CustomerLoginActivity;
import com.amanciodrp.yellotaxi.driverActivity.DriverLoginActivity;
import com.amanciodrp.yellotaxi.onboarding.FullScreenVideoView;

public class MainActivity extends AppCompatActivity {
    private CardView mDriver, mCustomer;
    private FullScreenVideoView mVideoView;
    final String videoToPlay = "https://speed.animaker.com/images/video/JcnrNgdQUFSZlNZ.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDriver = (CardView) findViewById(R.id.driver);
        mCustomer = (CardView) findViewById(R.id.customer);
        mVideoView = (FullScreenVideoView) findViewById(R.id.videoView11);
        Uri video = Uri.parse(videoToPlay);
        mVideoView.setVideoURI(video);
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                mVideoView.requestFocus();
                mVideoView.start();
                mp.setLooping(true);
            }

        });

        startService(new Intent(MainActivity.this, onAppKilled.class));
        mDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DriverLoginActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        mCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomerLoginActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }
}
