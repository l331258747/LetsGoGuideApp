package com.njz.letsgoappguides.model.send;

public class SendOrderRefundChildModel {
    int childRefundId;
    int isDefaultMoney;

    public SendOrderRefundChildModel(int childRefundId, int isDefaultMoney) {
        this.childRefundId = childRefundId;
        this.isDefaultMoney = isDefaultMoney;
    }

    public int getChildRefundId() {
        return childRefundId;
    }

    public int getIsDefaultMoney() {
        return isDefaultMoney;
    }

    @Override
    public String toString() {
        return "SendOrderRefundChildModel{" +
                "childRefundId=" + childRefundId +
                ", isDefaultMoney=" + isDefaultMoney +
                '}';
    }
}