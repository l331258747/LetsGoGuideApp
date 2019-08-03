package com.njz.letsgoappguides.util.rxbus.busEvent;

/**
 * Created by liguoqiang on 2019/8/3.
 */

public class SendServerEvent {
    int serverId;
    String serverName;
    String ServerArea;
    String serverImg;
    String serverPrice;

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerArea() {
        return ServerArea;
    }

    public void setServerArea(String serverArea) {
        ServerArea = serverArea;
    }

    public String getServerImg() {
        return serverImg;
    }

    public void setServerImg(String serverImg) {
        this.serverImg = serverImg;
    }

    public String getServerPrice() {
        return serverPrice;
    }

    public void setServerPrice(String serverPrice) {
        this.serverPrice = serverPrice;
    }
}
