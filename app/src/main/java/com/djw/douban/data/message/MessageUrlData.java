package com.djw.douban.data.message;

/**
 * Created by JasonDong on 2017/5/9.
 */

public class MessageUrlData extends MessageBaseData {

    private String url;

    public MessageUrlData(String url) {
        super(MessageBaseData.URL);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
