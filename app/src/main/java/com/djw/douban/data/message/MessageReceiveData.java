package com.djw.douban.data.message;

/**
 * Created by JasonDong on 2017/5/5.
 */

public class MessageReceiveData extends MessageBaseData {

    private String text;

    private int code;

    private String url;

    public MessageReceiveData(String text, int code, String url) {
        super(MessageBaseData.RECEIVE);
        this.text = text;
        this.code = code;
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MessageReceiveData{" +
                "text='" + text + '\'' +
                ", code=" + code +
                ", url='" + url + '\'' +
                '}';
    }
}
