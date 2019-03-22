package com.njz.letsgoappguides.model.settlement;

import java.util.List;

public class PageUtilsBean<T> {

    private int currPage;
    private int totalPage;
    private int pageSize;
    private int totalCount;
    private List<IncomeListInfo> list;

    public List<IncomeListInfo> getList() {
        return list;
    }

    public void setList(List<IncomeListInfo> list) {
        this.list = list;
    }
}