package com.shanghai.agentwebjs.activity;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.shanghai.agentwebjs.R;
import com.shanghai.agentwebjs.util.BlurBitmap;

public class AutoSizeActivity extends AppCompatActivity {
    ImageView mImgOne,mImgTwo;
    SeekBar mSeekBar;
    TextView mTvProgress;
    /**
     * 原始图片
     */
    private Bitmap mTempBitmap;

    /**
     * 模糊后的图片
     */
    private Bitmap mFinalBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_blank);
        init();
        // 获取图片
        mTempBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ads);
        mFinalBitmap = BlurBitmap.blur(this, mTempBitmap);

        // 填充模糊后的图像和原图
        mImgOne.setImageBitmap(mFinalBitmap);
        mImgTwo.setImageBitmap(mTempBitmap);

        setSeekBar();
    }
    private void init() {
        mImgOne=findViewById(R.id.img_first);
        mImgTwo=findViewById(R.id.img_second);
        mSeekBar = findViewById(R.id.activity_main_seekbar);
        mTvProgress = findViewById(R.id.activity_main_progress_tv);


    }

    private void setSeekBar(){
        mSeekBar.setMax(100);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mImgTwo.setImageAlpha((int) (255 - progress * 2.55));
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
