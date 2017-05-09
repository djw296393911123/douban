package com.djw.douban.ui.cloud.fragment;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.douban.R;
import com.djw.douban.ui.message.adapter.ImagePageAdapter;
import com.djw.douban.util.ImagePager;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * A simple {@link Fragment} subclass.
 */
public class CloudPageFragment extends DialogFragment implements ViewPager.OnPageChangeListener {

    private int position;
    private ArrayList<String> urls;
    private List<View> views;
    private ArrayList<String> content;
    private TextView text;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        position = bundle.getInt("position");
        urls = bundle.getStringArrayList("urls");
        content = bundle.getStringArrayList("content");
        initPager();
    }

    private void initPager() {
        views = new ArrayList<>();
        for (int i = 0; i < urls.size(); i++) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_page, null);
            final PhotoView photoView = (PhotoView) view.findViewById(R.id.pv_item);
            photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    dismiss();
                }
            });
            Glide.with(getActivity()).load(urls.get(i)).asBitmap().into(photoView);
            views.add(view);
        }
    }

    public static CloudPageFragment newInstance(List<String> urls, List<String> content, int position) {

        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putStringArrayList("content", ((ArrayList<String>) content));
        args.putStringArrayList("urls", (ArrayList<String>) urls);
        CloudPageFragment fragment = new CloudPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.cloud_layout, null);
        text = ((TextView) view.findViewById(R.id.tv_cloud));
        text.setText(content.get(position));
        ImagePager pager = (ImagePager) view.findViewById(R.id.pager);
        pager.setAdapter(new ImagePageAdapter(views));
        pager.setCurrentItem(position);
        pager.addOnPageChangeListener(this);
        AlertDialog alertDialog = new AlertDialog
                .Builder(getActivity(), android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen)
                .setView(view)
                .create();
        Window window = alertDialog.getWindow();
        window.setWindowAnimations(R.style.dialog_style);
        return alertDialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        System.gc();
//        Glide.get(BaseApplication.getInstance()).clearMemory();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        text.setText(content.get(position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
