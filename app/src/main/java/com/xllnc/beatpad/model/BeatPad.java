package com.xllnc.beatpad.model;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatPad {
    private static final String TAG = "BeatPad";

    private static final String SOUNDS_FOLDER = "samples";
    private static final int MAX_SOUND = 7;

    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;
    private Resources mResources;

    public BeatPad(Context context){
        mAssets = context.getAssets();
        mResources = context.getResources();
        mSoundPool = new SoundPool(MAX_SOUND, AudioManager.STREAM_MUSIC, 0);
        loadSounds();
    }

    private void loadSounds(){
        String[] soundNames;
        try{
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found " + soundNames.length + " sounds");
            for(String filename : soundNames){
                try {
                    String assetPath = SOUNDS_FOLDER + "/" + filename;
                    Sound sound = new Sound(assetPath, mResources);
                    load(sound);
                    mSounds.add(sound);
                }catch (IOException e){
                    Log.e(TAG, "Couldn't load sounds");
                }
            }
        }catch (IOException e){
            Log.e(TAG, "Couldn't list sounds");
            return;
        }
    }

    /**
     * Заргужает звук в SoundPool и устанавливает ему идентификатор
     * @param sound
     * @throws IOException
     */
    private void load(Sound sound) throws IOException{
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(afd, 1);
        sound.setSoundId(soundId);
    }

    /**
     * Запускает звук единожды при клике
     * @param sound
     */
    public void play(Sound sound){
        if(sound.getSoundId() == null){
            return;
        }
        mSoundPool.play(sound.getSoundId(), 1.0f, 1.0f, 1, 0, 1.0f);
    }

    /**
     * Запускает цикл и проверяет, находится ли звук уже в цикле, если да то останавливает цикл.
     * @param sound Объетк класса Sound
     */
    public void playLoop(Sound sound){
        if(sound.getSoundId() == null){
            return;
        }
        if(sound.inLoop()){
            mSoundPool.stop(sound.getStreamId());
        }else{
            int streamId = mSoundPool.play(sound.getSoundId(), 1.0f, 1.0f, 1, -1, 1.0f);
            sound.setStreamId(streamId);
        }
    }

    /**
     * Отключает SoundPool
     */
    public void release(){
        mSoundPool.release();
    }

    public List<Sound> getSounds(){
        return mSounds;
    }
}
