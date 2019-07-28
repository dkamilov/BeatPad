package com.xllnc.beatpad.activities;


import androidx.fragment.app.Fragment;

import com.xllnc.beatpad.fragments.BeatPadFragment;

public class BeatPadActivity extends SingleFragmentActivity {
    private static final String TAG = "BeatPadActivity";

    @Override
    protected Fragment createFragment() {
        return BeatPadFragment.newInstance();
    }
}
