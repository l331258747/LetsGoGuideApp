package com.njz.letsgoappguides.model.server;

/**
 * Created by LGQ
 * Time: 2018/11/23
 * Function:
 */

public class ServerTypeModel {


    /**
     * gmtModified : null
     * name : 私人订制
     * orderNum : 0
     * remark :
     * id : 23
     * gmtCreate : null
     * type : 0
     * value : srdz
     * parentId : 0
     * status : 0
     */

    private String name;
    private int orderNum;
    private String remark;
    private int id;
    private int type;
    private String value;
    private int parentId;
    private int status;

    public String getName() {
        return name;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public String getRemark() {
        return remark;
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public int getParentId() {
        return parentId;
    }

    public int getStatus() {
        return status;
    }
}
