package com.djw.douban.data.message;

/**
 * Created by JasonDong on 2017/5/8.
 */

public class MessageImgData extends MessageBaseData {

    private String url;

    public MessageImgData(String url) {
        super(MessageBaseData.IMAGE);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
