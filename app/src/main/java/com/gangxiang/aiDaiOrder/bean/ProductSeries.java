package com.gangxiang.aiDaiOrder.bean;

import java.util.List;

public class ProductSeries {

    /**
     * success : true
     * data : [{"id":"2","PeoSeTitle":"月色舞曲","PeoSeDate":"6月上旬"},{"id":"3","PeoSeTitle":"暮云阑珊","PeoSeDate":"6月中旬"},{"id":"4","PeoSeTitle":"浓情华尔兹","PeoSeDate":"7月下旬"},{"id":"5","PeoSeTitle":"浅遇花暖","PeoSeDate":"8月中旬"}]
     */

    private String success;
    private List<DataBean> data;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * PeoSeTitle : 月色舞曲
         * PeoSeDate : 6月上旬
         */

        private String id;
        private String PeoSeTitle;
        private String PeoSeDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPeoSeTitle() {
            return PeoSeTitle;
        }

        public void setPeoSeTitle(String PeoSeTitle) {
            this.PeoSeTitle = PeoSeTitle;
        }

        public String getPeoSeDate() {
            return PeoSeDate;
        }

        public void setPeoSeDate(String PeoSeDate) {
            this.PeoSeDate = PeoSeDate;
        }
    }
}
