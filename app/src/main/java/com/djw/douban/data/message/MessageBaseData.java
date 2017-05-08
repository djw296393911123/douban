package com.djw.douban.data.message;

/**
 * Created by JasonDong on 2017/5/5.
 */

public class MessageBaseData {

    public static final int SEND = 0x9001;

    public static final int RECEIVE = 0x9002;

    public static final int IMAGE = 0x9003;

    private int type;

    public MessageBaseData(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
