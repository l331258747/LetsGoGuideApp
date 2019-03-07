package com.njz.letsgoappguides.model.server;

public class NjzGuideServeFormatPriceEntities{
        int yearInt;
        int monthInt;
        int dateInt;
        float price;

        public int getYearInt() {
            return yearInt;
        }

        public void setYearInt(int yearInt) {
            this.yearInt = yearInt;
        }

        public int getMonthInt() {
            return monthInt;
        }

        public void setMonthInt(int monthInt) {
            this.monthInt = monthInt;
        }

        public int getDateInt() {
            return dateInt;
        }

        public void setDateInt(int dateInt) {
            this.dateInt = dateInt;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "NjzGuideServeFormatPriceEntities{" +
                    "yearInt=" + yearInt +
                    ", monthInt=" + monthInt +
                    ", dateInt=" + dateInt +
                    ", price=" + price +
                    '}';
        }
    }