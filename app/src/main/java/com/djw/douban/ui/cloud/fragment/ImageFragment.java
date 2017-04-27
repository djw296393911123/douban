package com.djw.douban.ui.cloud.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.bumptech.glide.Glide;
import com.djw.douban.R;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by JasonDong on 2017/4/25.
 */

public class ImageFragment extends DialogFragment {

    private String url;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        url = bundle.getString("url");
    }

    public static ImageFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString("url", url);
        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_image, null);
        PhotoView photoView = (PhotoView) view.findViewById(R.id.pv_pop);
        Glide.with(getActivity()).load(url).error(R.mipmap.img_default_meizi).into(photoView);
        AlertDialog alertDialog = new AlertDialog
                .Builder(getActivity())
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
}