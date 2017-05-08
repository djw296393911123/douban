package com.djw.douban.data.message;

/**
 * Created by JasonDong on 2017/5/8.
 */

public class MessageTimeData extends MessageBaseData {

    private String time;

    public MessageTimeData(String time) {
        super(MessageBaseData.TIME);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
