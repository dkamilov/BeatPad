package com.xllnc.beatpad.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xllnc.beatpad.R;
import com.xllnc.beatpad.databinding.FragmentBeatPadBinding;
import com.xllnc.beatpad.databinding.ListItemSoundBinding;
import com.xllnc.beatpad.model.BeatPad;
import com.xllnc.beatpad.model.Sound;
import com.xllnc.beatpad.viewmodel.SoundViewModel;

import java.util.List;

public class BeatPadFragment extends Fragment {
    private static final String TAG = "BeatPadFragment";

    private BeatPad mBeatPad;

    public static BeatPadFragment newInstance(){
        return new BeatPadFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBeatPad = new BeatPad(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentBeatPadBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_beat_pad, container, false);
        binding.recyclerView.setAdapter(new SoundAdapter(mBeatPad.getSounds()));
        setRetainInstance(true);
        setLayoutManager(binding);
        return binding.getRoot();
    }

    private void setLayoutManager(FragmentBeatPadBinding binding){
        int orientation = getActivity().getResources().getConfiguration().orientation;
        Log.i(TAG, "Orientation " + orientation);

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
            return;
        }
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 8));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBeatPad.release();
    }


    /**
     *
     *
     *
     * ViewHolder SoundHolder
     *
     *
     *
     *
     */
    private class SoundHolder extends RecyclerView.ViewHolder{
        private ListItemSoundBinding mBinding;

        private SoundHolder(ListItemSoundBinding binding){
            super(binding.getRoot());
            mBinding = binding;
            mBinding.setViewModel(new SoundViewModel(mBeatPad));
        }

        public void bind(Sound sound){
            mBinding.getViewModel().setSound(sound);
            mBinding.executePendingBindings();
        }
    }



    /**
     *
     *
     *
     *
     * RecyclerView.Adapter
     *
     *
     *
     *
     */
    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder>{
        private List<Sound> mSounds;

        public SoundAdapter(List<Sound> sounds){
            mSounds = sounds;
        }

        @NonNull
        @Override
        public SoundHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ListItemSoundBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_item_sound, parent, false);
            return new SoundHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull SoundHolder holder, int position) {
            Sound sound = mSounds.get(position);
            holder.bind(sound);
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }
}
