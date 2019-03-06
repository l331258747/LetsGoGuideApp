package com.njz.letsgoappguides.model.login;

/**
 * Created by Administrator on 2018/11/2.
 */

public class Datas {

    private String expire;
    private String token;
    private UserVo userVo;

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }

    @Override
    public String toString() {
        return "Datas{" +
                "expire='" + expire + '\'' +
                ", token='" + token + '\'' +
                ", userVo=" + userVo +
                '}';
    }
}
