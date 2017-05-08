package com.djw.douban.ui.message;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.djw.douban.R;
import com.djw.douban.base.RxToolbarActivity;
import com.djw.douban.data.message.MessageImgData;
import com.djw.douban.data.message.MessageReceiveData;
import com.djw.douban.data.message.MessageSendData;
import com.djw.douban.ui.message.adapter.MessageAdapter;
import com.djw.douban.ui.message.contract.MessageContract;
import com.djw.douban.ui.message.presenter.MessagePresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageActivity extends RxToolbarActivity<MessagePresenter> implements MessageContract.View {

    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.tl_base)
    Toolbar tlBase;
    @BindView(R.id.et_send)
    EditText etSend;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.rv_message)
    RecyclerView rvMessage;
    private MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
    }

    @Override
    public void initView() {
        setToolBarTitle(getString(R.string.message));
        rvMessage.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MessageAdapter();
        rvMessage.setAdapter(adapter);
    }

    @Override
    protected void scrollToTop() {

    }

    @Override
    public void doBusiness() {

    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showReceiveMessage(MessageReceiveData data) {
        adapter.notifyDataChange(data);
        rvMessage.scrollToPosition(adapter.getItemCount());
    }

    @Override
    public void showSendMessage(MessageSendData data) {
        adapter.notifyDataChange(data);
        etSend.setText("");
        rvMessage.scrollToPosition(adapter.getItemCount());
    }

    @Override
    public void showGirlMessage(MessageImgData data) {
        adapter.notifyDataChange(data);
        rvMessage.scrollToPosition(adapter.getItemCount());
    }

    @OnClick(R.id.btn_send)
    void send() {
        mPresenter.getSendData(etSend.getText().toString().trim());
    }

}
