package com.shanghai.agentwebjs.fragment;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.shanghai.agentwebjs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {
    ImageView mImgOne,mImgTwo;
    SeekBar mSeekBar;
    TextView mTvProgress;
    private Bitmap mBitmap;
    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_blank, container, false);
        init(view);
        setSeekBar();
        return view;
    }

    private void init(View view) {
        mImgOne=view.findViewById(R.id.img_first);
        mImgTwo=view.findViewById(R.id.img_second);
        mSeekBar = view.findViewById(R.id.activity_main_seekbar);
        mTvProgress = view.findViewById(R.id.activity_main_progress_tv);


    }

    private void setSeekBar(){
        mSeekBar.setMax(100);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mImgTwo.setAlpha((int) (255 - progress * 2.55));
                mTvProgress.setText(progress+"");;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}
