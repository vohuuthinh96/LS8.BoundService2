package com.atoproduction.ls8boundservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonPlay;
    private Button buttonStop;

    private boolean binded = false;
    private PlaySongService weatherService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.buttonPlay = this.findViewById(R.id.button_play);
        this.buttonStop = this.findViewById(R.id.button_stop);

        Intent intent = new Intent(this, PlaySongService.class);
        bindService(intent, weatherServiceConnection, Context.BIND_AUTO_CREATE);

        this.buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSong();
            }
        });

        this.buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopSong();
            }
        });
    }

    public void playSong() {
        weatherService.startSong();
    }

    public void stopSong() {
        weatherService.stopSong();
    }


    private ServiceConnection weatherServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            PlaySongService.MyBinder binder = (PlaySongService.MyBinder) service;
            weatherService = binder.getService();
            binded = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            binded = false;
            weatherService = null;
        }
    };
}