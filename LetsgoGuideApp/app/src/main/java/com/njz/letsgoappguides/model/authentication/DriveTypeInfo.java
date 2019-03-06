package com.njz.letsgoappguides.model.authentication;

/**
 * Created by Administrator on 2018/11/20.
 */

public class DriveTypeInfo {

/*          "gmtModified": null,
                  "name": "A1大型客车",
                  "orderNum": 0,
                  "remark": "",
                  "id": 43,
                  "gmtCreate": null,
                  "type": 0,
                  "value": "A1",
                  "parentId": 0,
                  "status": 0*/

    private String name;
    private String value;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DriveTypeInfo{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", id=" + id +
                '}';
    }
}
