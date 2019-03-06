package com.njz.letsgoappguides.model.login;

/**
 * Created by Administrator on 2018/11/2.
 */

public class GuideMacroEntityList {

    private int status;
    private int parentId;
    private String value;
    private int type;
    private int orderNum;
    private int id;
    private String gmtModified;
    private String gmtCreate;
    private String remark;
    private String name;
    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getStatus() {
        return status;
    }

    public int getParentId() {
        return parentId;
    }

    public String getValue() {
        return value;
    }

    public int getType() {
        return type;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public int getId() {
        return id;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public String getRemark() {
        return remark;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "GuideMacroEntityList{" +
                "status=" + status +
                ", parentId=" + parentId +
                ", value=" + value +
                ", type=" + type +
                ", orderNum=" + orderNum +
                ", id=" + id +
                ", gmtModified='" + gmtModified + '\'' +
                ", gmtCreate='" + gmtCreate + '\'' +
                ", remark='" + remark + '\'' +
                ", name='" + name + '\'' +
                ", isSelect=" + isSelect +
                '}';
    }
}
