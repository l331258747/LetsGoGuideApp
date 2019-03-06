package com.njz.letsgoappguides.model.mine;

import java.util.Arrays;

/**
 * Created by Administrator on 2018/11/21.
 */

public class BatchUploadInfo {


    private String[] url;

    public String[] getUrl() {
        return url;
    }

    public void setUrl(String[] url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "BatchUploadInfo{" +
                "url=" + Arrays.toString(url) +
                '}';
    }
}
