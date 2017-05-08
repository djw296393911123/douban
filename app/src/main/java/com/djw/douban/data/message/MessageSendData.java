package com.djw.douban.data.message;

/**
 * Created by JasonDong on 2017/5/5.
 */

public class MessageSendData extends MessageBaseData {

    private String message;

    public MessageSendData(String message) {
        super(MessageBaseData.SEND);
        this.message = message;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
