package com.xllnc.beatpad.viewmodel;


import android.graphics.drawable.Drawable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.xllnc.beatpad.model.BeatPad;
import com.xllnc.beatpad.model.Sound;

public class SoundViewModel extends BaseObservable {
    private BeatPad mBeatPad;
    private Sound mSound;

    public SoundViewModel(BeatPad beatPad){
        mBeatPad = beatPad;
    }

    @Bindable
    public String getTitle(){
        return mSound.getName();
    }

    @Bindable
    public Drawable getColor(){
        return mSound.getColor();
    }


    public void setSound(Sound sound){
        mSound = sound;
        notifyChange();
    }

    public void onButtonClicked(){
        mBeatPad.play(mSound);
    }

    public boolean onButtonLongClicked(){
        mBeatPad.playLoop(mSound);
        mSound.setLoop(!(mSound.inLoop()));
        return true;
    }
}
