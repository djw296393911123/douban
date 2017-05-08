package com.djw.douban.ui.leader;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.djw.douban.MainActivity;
import com.djw.douban.R;
import com.djw.douban.base.RxActivity;
import com.djw.douban.ui.leader.contract.LeaderContract;
import com.djw.douban.ui.leader.presenter.LeaderPresenter;
import com.djw.douban.util.RxUtil;

import java.util.concurrent.TimeUnit;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;
import rx.Observable;
import rx.functions.Action1;

public class LeaderActivity extends RxActivity<LeaderPresenter> implements LeaderContract.View {

    private ImageView imageView;
    private TextView author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBackEnable(false);
        setContentView(R.layout.activity_leader);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showGirl(String img, String string) {
        author.setText(string);
        Glide.with(this).load(img).into(imageView);
    }

    @Override
    public void initView() {
        imageView = (ImageView) findViewById(R.id.iv_leader);
        author = (TextView) findViewById(R.id.tv_author);
    }

    @Override
    public void doBusiness() {
        PermissionGen.with(LeaderActivity.this)
                .addRequestCode(100)
                .permissions(Manifest.permission.CAMERA)
                .request();
    }

    @Override
    public void inject() {
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.getGril();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionSuccess(requestCode = 100)
    public void doSomething() {
        Observable.timer(3, TimeUnit.SECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        startActivity(MainActivity.class);
                        finish();
                    }
                });
    }

    @PermissionFail(requestCode = 100)
    public void doFailSomething() {
        PermissionGen.with(LeaderActivity.this)
                .addRequestCode(100)
                .permissions(Manifest.permission.CAMERA)
                .request();
    }

}
