package com.njz.letsgoappguides.model.mine;

/**
 * Created by Administrator on 2018/12/29.
 */

public class GetBackListInfo {


    /**
     * gmtModified : null
     * name : 中国银行
     * orderNum : 0
     * remark :
     * id : 96
     * gmtCreate : null
     * type : 1
     * value : zgyh
     * parentId : 95
     * status : 1
     */

    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GetBackListInfo{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
