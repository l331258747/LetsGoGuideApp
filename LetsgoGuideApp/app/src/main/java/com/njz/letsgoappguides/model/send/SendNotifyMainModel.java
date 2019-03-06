package com.njz.letsgoappguides.model.send;

/**
 * Created by LGQ
 * Time: 2018/10/12
 * Function:
 */

public class SendNotifyMainModel {
    String msgBroad;
    int page;
    int limit;

    public String getMsgBroad() {
        return msgBroad;
    }

    public void setMsgBroad(String msgBroad) {
        this.msgBroad = msgBroad;
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

}
