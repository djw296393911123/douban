package com.djw.douban.ui.home.book.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.djw.douban.MainActivity;
import com.djw.douban.R;
import com.djw.douban.base.BaseFragment;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.book.BannerData;
import com.djw.douban.data.book.BookTypeData;
import com.djw.douban.data.book.Books;
import com.djw.douban.ui.home.book.activity.BookInfoActivity;
import com.djw.douban.ui.home.book.adapter.BookRecyclerAdapter;
import com.djw.douban.ui.home.book.adapter.BookTypeAdapter;
import com.djw.douban.ui.home.book.contract.BookContract;
import com.djw.douban.ui.home.book.presenter.ComprehensivePresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragment extends BaseFragment<ComprehensivePresenter> implements BookContract.View, XRecyclerView.LoadingListener {

    private BookTypeAdapter adapter;

    private String[] types = {"综合", "文学", "流行", "文化", "生活", "天文", "地理", "小说", "短篇", "中篇", "长篇", "郭敬明", "韩寒", "莫言", "明晓溪", "饶雪漫"};
    private XRecyclerView xRecyclerView;
    private int position;
    private BookRecyclerAdapter bookRecyclerAdapter;
    private Banner banner;


    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void initView(View view) {
        xRecyclerView = ((XRecyclerView) view.findViewById(R.id.xrv_book));
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setLoadingListener(this);
//        xRecyclerView.setPullRefreshEnabled(false);
        View head = LayoutInflater.from(getActivity()).inflate(R.layout.item_header, null);
        banner = ((Banner) head.findViewById(R.id.banner));
        xRecyclerView.addHeaderView(head);
        bookRecyclerAdapter = new BookRecyclerAdapter(getActivity());
        xRecyclerView.setAdapter(bookRecyclerAdapter);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_book_type);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new BookTypeAdapter() {
            @Override
            public void OnItemClick(int position) {
                BookFragment.this.position = position;
                mPresenter.getBookList(ParamsData.START, ParamsData.COUNT, types[position], false, true);
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void doBusiness() {
        List<BookTypeData> list = new ArrayList<>();
        list.add(new BookTypeData(types[0], true));
        for (int i = 1; i < types.length; i++) {
            list.add(new BookTypeData(types[i], false));
        }
        adapter.notifyDataChange(list);
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.getBookList(ParamsData.START, ParamsData.COUNT, types[0], false, false);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_book;
    }

    @Override
    public void onRefresh() {
        mPresenter.getBookList(ParamsData.START, ParamsData.COUNT, types[position], false, false);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getBookList(bookRecyclerAdapter.getItemCount() + 1, ParamsData.COUNT, types[position], true, false);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        ((MainActivity) getActivity()).showProgress();
    }

    @Override
    public void dismissProgress() {
        ((MainActivity) getActivity()).dismissProgress();
        xRecyclerView.loadMoreComplete();
        xRecyclerView.refreshComplete();
    }

    @Override
    public void showBookList(List<Books> list, final BannerData bannerData, boolean isLoadMore) {
        if (!isLoadMore) {
            banner.setImageLoader(new GlideImageLoader());
            banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
            banner.setOffscreenPageLimit(1);
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", bannerData.getId().get(position - 1));
                    startActivity(BookInfoActivity.class, bundle);
                }
            });
            banner.setBannerTitles(bannerData.getTitle());
            banner.setImages(bannerData.getUrl());
            banner.start();
        }
        bookRecyclerAdapter.notifyDataChange(list, isLoadMore);
    }

    private class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            Glide.with(context).load(path).asBitmap().into(imageView);
        }

        @Override
        public ImageView createImageView(Context context) {
            return new ImageView(context);
        }
    }

}
