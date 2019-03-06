package com.njz.letsgoappguides.model.other;

/**
 * selectPopupWindow Data
 * Created by llt on 2017/10/25.
 */

public class PopupSelectModel {

    private int id;
    private String name;
    private boolean isSelect;

    public PopupSelectModel(){}



    public PopupSelectModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public PopupSelectModel(int id, String name, boolean isSelect) {
        this.id = id;
        this.name = name;
        this.isSelect = isSelect;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name==null?"":name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
