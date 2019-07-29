package com.xllnc.beatpad.model;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.xllnc.beatpad.R;

public class Sound {
    private static final String TAG = "Sound";

    private Resources mResources;

    private String mAssetPath;
    private String mName;
    private Integer mSoundId;
    private Integer mStreamId;
    private boolean inLoop;

    public Sound(String assetPath, Resources resources) {
        mAssetPath = assetPath;
        mResources = resources;
        String[] components = assetPath.split("/");
        String fileName = components[components.length - 1];
        fileName = fileName.replace(".wav", "");
        mName = fileName.split("-")[0];

        inLoop = false;
    }


    public String getAssetPath() {
        return mAssetPath;
    }

    public String getName() {
        return mName;
    }

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }

    public Integer getStreamId() {
        return mStreamId;
    }

    public void setStreamId(Integer streamId) {
        mStreamId = streamId;
    }

    public boolean inLoop() {
        return inLoop;
    }

    public void setLoop(boolean loop) {
        inLoop = loop;
    }

    public Drawable getColor() {
        switch (mName) {
            case "clap":
                Log.i(TAG, "clap");
                return mResources.getDrawable(R.drawable.clap_drawable_background);
            case "crash":
                Log.i(TAG, "crash");
                return mResources.getDrawable(R.drawable.crash_drawable_background);
            case "hihat":
                Log.i(TAG, "hihat");
                return mResources.getDrawable(R.drawable.hihat_drawable_background);
            case "kick":
                Log.i(TAG, "kick");
                return mResources.getDrawable(R.drawable.kick_drawable_background);
            case "ride":
                Log.i(TAG, "ride");
                return mResources.getDrawable(R.drawable.ride_drawable_background);
            case "snare":
                Log.i(TAG, "snare");
                return mResources.getDrawable(R.drawable.clap_drawable_background);
            default:
                Log.i(TAG, "default");
                return null;
        }
    }

    public Drawable getLoopIcon(){
        return mResources.getDrawable(R.drawable.infinite_loop);
    }
}
