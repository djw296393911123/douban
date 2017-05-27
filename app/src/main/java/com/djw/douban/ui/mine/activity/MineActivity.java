package com.djw.douban.ui.mine.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.djw.douban.R;
import com.djw.douban.base.SimpleActivity;
import com.djw.douban.util.WaveView;

import butterknife.BindView;

public class MineActivity extends SimpleActivity {

    @BindView(R.id.wave_view)
    WaveView waveView;
    @BindView(R.id.image)
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
    }

    @Override
    public void initView() {
        final FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(-2, -2);
        lp.gravity = Gravity.BOTTOM | Gravity.CENTER;
        waveView.setOnWaveAnimationListener(new WaveView.OnWaveAnimationListener() {
            @Override
            public void OnWaveAnimation(float y) {
//                lp.setMargins(0, 0, 0, (int) y + 2);
                lp.setMargins(0,0,(int)y+2,(int)y+2);
                image.setLayoutParams(lp);
//                image.setRotation(-10+y);
            }
        });
    }

    @Override
    public void doBusiness() {

    }
}
