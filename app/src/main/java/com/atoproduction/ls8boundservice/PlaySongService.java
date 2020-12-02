package com.atoproduction.ls8boundservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by : Thinhvh on 11/27/2020
 * Phone      : 0962890153 - 0398477967
 * Facebook   : https://www.facebook.com/thinh.super22
 * Skype      : https://join.skype.com/invite/fvfRTDLcGPJN
 * Mail       : thinhvhph04204@gmail.com
 */
public class PlaySongService extends Service {

    private MediaPlayer mediaPlayer;
    private MyBinder mMyBinder = new MyBinder();
    private String TAG = "thinhvh";


    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return mMyBinder;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ytiet);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        mediaPlayer.release();
        super.onDestroy();
    }

    public void startSong() {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ytiet);
        mediaPlayer.start();
    }

    public void stopSong() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }


    public class MyBinder extends Binder {
        public PlaySongService getService() {
            return PlaySongService.this;
        }
    }
}
