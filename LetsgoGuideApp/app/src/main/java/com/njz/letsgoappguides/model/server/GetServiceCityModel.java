package com.njz.letsgoappguides.model.server;

import java.util.List;

/**
 * Created by Administrator on 2018/12/6.
 */

public class GetServiceCityModel{

    /**
     * id : 1
     * addressName : 中国
     * childrens : [{"id":2,"addressName":"湖南省","childrens":[{"id":36,"addressName":"长沙","childrens":[]},{"id":37,"addressName":"凤凰","childrens":[]},{"id":41,"addressName":"张家界","childrens":[]}]},{"id":3,"addressName":"北京市","childrens":[]},{"id":4,"addressName":"天津市","childrens":[]},{"id":5,"addressName":"上海市","childrens":[]},{"id":6,"addressName":"重庆市","childrens":[]},{"id":7,"addressName":"河北省","childrens":[]},{"id":8,"addressName":"河南省","childrens":[]},{"id":9,"addressName":"山西省","childrens":[{"id":39,"addressName":"太原","childrens":[]},{"id":42,"addressName":"临汾","childrens":[]}]},{"id":10,"addressName":"辽宁省","childrens":[]},{"id":11,"addressName":"吉林省","childrens":[]},{"id":12,"addressName":"黑龙江省","childrens":[]},{"id":13,"addressName":"江苏省","childrens":[{"id":43,"addressName":"南京","childrens":[]}]},{"id":14,"addressName":"浙江省","childrens":[]},{"id":15,"addressName":"安徽省","childrens":[]},{"id":16,"addressName":"福建省","childrens":[]},{"id":17,"addressName":"江西省","childrens":[]},{"id":18,"addressName":"山东省","childrens":[]},{"id":19,"addressName":"湖北省","childrens":[]},{"id":20,"addressName":"广东省","childrens":[]},{"id":21,"addressName":"海南省","childrens":[]},{"id":22,"addressName":"四川省","childrens":[]},{"id":23,"addressName":"贵州省","childrens":[]},{"id":24,"addressName":"云南省","childrens":[{"id":38,"addressName":"大理","childrens":[]}]},{"id":25,"addressName":"陕西省","childrens":[]},{"id":26,"addressName":"甘肃省","childrens":[]},{"id":27,"addressName":"青海省","childrens":[]},{"id":28,"addressName":"台湾省","childrens":[]},{"id":29,"addressName":"内蒙古自治区","childrens":[]},{"id":30,"addressName":"广西壮族自治区","childrens":[]},{"id":31,"addressName":"西藏自治区","childrens":[]},{"id":32,"addressName":"宁夏回族自治区","childrens":[]},{"id":33,"addressName":"新疆维吾尔族自治区","childrens":[]},{"id":34,"addressName":"香港特别行政区","childrens":[]},{"id":35,"addressName":"澳门特别行政区","childrens":[]}]
     */

    private int id;
    private String addressName;
    private List<ChildrensBeanX> childrens;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public List<ChildrensBeanX> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<ChildrensBeanX> childrens) {
        this.childrens = childrens;
    }

    @Override
    public String toString() {
        return "GetServiceCityModel{" +
                "id=" + id +
                ", addressName='" + addressName + '\'' +
                ", childrens=" + childrens +
                '}';
    }

    public static class ChildrensBeanX {
        /**
         * id : 2
         * addressName : 湖南省
         * childrens : [{"id":36,"addressName":"长沙","childrens":[]},{"id":37,"addressName":"凤凰","childrens":[]},{"id":41,"addressName":"张家界","childrens":[]}]
         */

        private int id;
        private String addressName;
        private List<ChildrensBean> childrens;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAddressName() {
            return addressName;
        }

        public void setAddressName(String addressName) {
            this.addressName = addressName;
        }

        public List<ChildrensBean> getChildrens() {
            return childrens;
        }

        public void setChildrens(List<ChildrensBean> childrens) {
            this.childrens = childrens;
        }

        @Override
        public String toString() {
            return "ChildrensBeanX{" +
                    "id=" + id +
                    ", addressName='" + addressName + '\'' +
                    ", childrens=" + childrens +
                    '}';
        }

        public static class ChildrensBean {
            /**
             * id : 36
             * addressName : 长沙
             * childrens : []
             */

            private int id;
            private String addressName;
            private List<?> childrens;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAddressName() {
                return addressName;
            }

            public void setAddressName(String addressName) {
                this.addressName = addressName;
            }

            public List<?> getChildrens() {
                return childrens;
            }

            public void setChildrens(List<?> childrens) {
                this.childrens = childrens;
            }

            @Override
            public String toString() {
                return "ChildrensBean{" +
                        "id=" + id +
                        ", addressName='" + addressName + '\'' +
                        ", childrens=" + childrens +
                        '}';
            }
        }
    }
}
