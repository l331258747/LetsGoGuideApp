package com.njz.letsgoappguides.customview.widget.calendarDecorators;

import android.os.Parcel;
import android.os.Parcelable;

import com.njz.letsgoappguides.model.server.ServicePreviewInfo;

/**
 * Created by LGQ
 * Time: 2018/12/11
 * Function:
 */

public class PriceModel implements Parcelable {
    String time;
    String price;

    public PriceModel() {
    }

    public PriceModel(String time, String price) {
        this.time = time;
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    String year;
    String month;
    String day;

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public void setTime(String time) {
        this.time = time;

        String[] strs = time.split("-");
        year = strs[0];
        month = strs[1];
        day = strs[2];
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PriceModel{" +
                "time='" + time + '\'' +
                ", price='" + price + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                '}';
    }

    public static final Creator<PriceModel> CREATOR = new Creator<PriceModel>() {
        @Override
        public PriceModel createFromParcel(Parcel in) {
            return new PriceModel(in);
        }
        @Override
        public PriceModel[] newArray(int size) {
            return new PriceModel[size];
        }
    };

    protected PriceModel(Parcel in) {
        time = in.readString();
        price = in.readString();
        year = in.readString();
        month = in.readString();
        day = in.readString();
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(time);
        out.writeString(price);
        out.writeString(year);
        out.writeString(month);
        out.writeString(day);
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
