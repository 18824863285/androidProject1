package com.gangxiang.aiDaiOrder.bean;

import java.util.List;

public class NkProduct {

    /**
     * success : true
     * data : [{"id":"4","ProTitle":"D3444","ProImg":"/upload/201807/30/201807301734540538.png","ProPrice":"100.00","size":[{"id":"3","PPColor":"粉色","PSNumber":"0"},{"id":"4","PPColor":"大红","PSNumber":"0"},{"id":"5","PPColor":"黑色","PSNumber":"0"},{"id":"6","PPColor":"肤色","PSNumber":"0"},{"id":"7","PPColor":"OC","PSNumber":"0"},{"id":"8","PPColor":"淡紫","PSNumber":"0"}]},{"id":"5","ProTitle":"8602#","ProImg":"/upload/201807/30/201807301736118377.png","ProPrice":"77.00","size":[{"id":"9","PPColor":"粉色","PSNumber":"0"},{"id":"10","PPColor":"大红","PSNumber":"0"},{"id":"11","PPColor":"黑色","PSNumber":"0"},{"id":"12","PPColor":"肤色","PSNumber":"0"},{"id":"13","PPColor":"OC","PSNumber":"0"},{"id":"14","PPColor":"淡紫","PSNumber":"0"}]},{"id":"6","ProTitle":"8603#","ProImg":"/upload/201807/30/201807301734540538.png","ProPrice":"100.00","size":[{"id":"15","PPColor":"粉色","PSNumber":"0"},{"id":"16","PPColor":"大红","PSNumber":"0"},{"id":"17","PPColor":"黑色","PSNumber":"0"}]},{"id":"7","ProTitle":"8604#","ProImg":"/upload/201807/30/201807301736118377.png","ProPrice":"77.00","size":[{"id":"18","PPColor":"肤色","PSNumber":"0"},{"id":"19","PPColor":"OC","PSNumber":"0"},{"id":"20","PPColor":"淡紫","PSNumber":"0"}]},{"id":"8","ProTitle":"8605#","ProImg":"/upload/201807/30/201807301736118377.png","ProPrice":"77.00","size":[{"id":"21","PPColor":"粉色","PSNumber":"0"},{"id":"22","PPColor":"大红","PSNumber":"0"}]},{"id":"9","ProTitle":"8606#","ProImg":"/upload/201807/30/201807301736118377.png","ProPrice":"77.00","size":[{"id":"23","PPColor":"黑色","PSNumber":"0"},{"id":"24","PPColor":"肤色","PSNumber":"0"},{"id":"25","PPColor":"OC","PSNumber":"0"},{"id":"26","PPColor":"淡紫","PSNumber":"0"}]},{"id":"10","ProTitle":"8607#","ProImg":"/upload/201807/30/201807301736118377.png","ProPrice":"77.00","size":[{"id":"27","PPColor":"粉色","PSNumber":"0"},{"id":"28","PPColor":"大红","PSNumber":"0"},{"id":"29","PPColor":"黑色","PSNumber":"0"},{"id":"30","PPColor":"肤色","PSNumber":"0"}]},{"id":"11","ProTitle":"8608#","ProImg":"/upload/201807/30/201807301736118377.png","ProPrice":"77.00","size":[{"id":"31","PPColor":"OC","PSNumber":"0"},{"id":"32","PPColor":"淡紫","PSNumber":"0"}]},{"id":"12","ProTitle":"8609#","ProImg":"/upload/201807/30/201807301736118377.png","ProPrice":"77.00","size":[{"id":"33","PPColor":"粉色","PSNumber":"0"},{"id":"34","PPColor":"大红","PSNumber":"0"},{"id":"35","PPColor":"黑色","PSNumber":"0"},{"id":"36","PPColor":"肤色","PSNumber":"0"}]},{"id":"13","ProTitle":"8610#","ProImg":"/upload/201807/30/201807301736118377.png","ProPrice":"77.00","size":[{"id":"37","PPColor":"OC","PSNumber":"0"},{"id":"38","PPColor":"淡紫","PSNumber":"0"}]}]
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
         * id : 4
         * ProTitle : D3444
         * ProImg : /upload/201807/30/201807301734540538.png
         * ProPrice : 100.00
         * size : [{"id":"3","PPColor":"粉色","PSNumber":"0"},{"id":"4","PPColor":"大红","PSNumber":"0"},{"id":"5","PPColor":"黑色","PSNumber":"0"},{"id":"6","PPColor":"肤色","PSNumber":"0"},{"id":"7","PPColor":"OC","PSNumber":"0"},{"id":"8","PPColor":"淡紫","PSNumber":"0"}]
         */

        private String id;
        private String ProTitle;
        private String ProImg;
        private String ProPrice;
        private List<SizeBean> size;
        private int allNum;

        public int getAllNum() {
            return allNum;
        }

        public void setAllNum(int allNum) {
            this.allNum = allNum;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProTitle() {
            return ProTitle;
        }

        public void setProTitle(String ProTitle) {
            this.ProTitle = ProTitle;
        }

        public String getProImg() {
            return ProImg;
        }

        public void setProImg(String ProImg) {
            this.ProImg = ProImg;
        }

        public String getProPrice() {
            return ProPrice;
        }

        public void setProPrice(String ProPrice) {
            this.ProPrice = ProPrice;
        }

        public List<SizeBean> getSize() {
            return size;
        }

        public void setSize(List<SizeBean> size) {
            this.size = size;
        }

        public static class SizeBean {
            /**
             * id : 3
             * PPColor : 粉色
             * PSNumber : 0
             */

            private String id;
            private String PPColor;
            private String PSNumber;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPPColor() {
                return PPColor;
            }

            public void setPPColor(String PPColor) {
                this.PPColor = PPColor;
            }

            public String getPSNumber() {
                return PSNumber;
            }

            public void setPSNumber(String PSNumber) {
                this.PSNumber = PSNumber;
            }
        }
    }
}
