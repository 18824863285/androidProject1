package com.gangxiang.aiDaiOrder.bean;

import java.util.List;

public class Product {


    /**
     * success : true
     * data : [{"id":"2","ProTitle":"D6460","ProPrice":"188.00","DiscountPrice":"100.00","color":[{"id":"3","PCTitle":"香槟色","PCImg":"/upload/201807/30/201807301021554616.png","cup":[{"id":"3","PCTCup":"A","size":[{"id":"71","PSSize":"70","PSNumber":"71"},{"id":"73","PSSize":"80","PSNumber":"73"},{"id":"74","PSSize":"85","PSNumber":"74"},{"id":"76","PSSize":"95","PSNumber":"76"}]}]},{"id":"4","PCTitle":"黑色","PCImg":"/upload/201807/30/201807301022050102.png","cup":[{"id":"3","PCTCup":"A","size":[{"id":"78","PSSize":"75","PSNumber":"78"},{"id":"79","PSSize":"80","PSNumber":"79"},{"id":"80","PSSize":"85","PSNumber":"80"},{"id":"81","PSSize":"90","PSNumber":"81"},{"id":"82","PSSize":"95","PSNumber":"82"}]}]}]},{"id":"3","ProTitle":"D6461","ProPrice":"188.00","DiscountPrice":"100.00","color":[{"id":"5","PCTitle":"香槟色","PCImg":"/upload/201807/30/201807301026315574.png","cup":[{"id":"4","PCTCup":"C","size":[{"id":"83","PSSize":"70","PSNumber":"83"},{"id":"84","PSSize":"75","PSNumber":"84"},{"id":"85","PSSize":"80","PSNumber":"85"},{"id":"86","PSSize":"85","PSNumber":"86"},{"id":"87","PSSize":"90","PSNumber":"87"},{"id":"88","PSSize":"95","PSNumber":"88"}]},{"id":"5","PCTCup":"D","size":[{"id":"89","PSSize":"70","PSNumber":"89"},{"id":"90","PSSize":"75","PSNumber":"90"},{"id":"91","PSSize":"80","PSNumber":"91"},{"id":"92","PSSize":"85","PSNumber":"92"},{"id":"93","PSSize":"90","PSNumber":"93"},{"id":"94","PSSize":"95","PSNumber":"94"}]}]},{"id":"6","PCTitle":"黑色","PCImg":"/upload/201807/30/201807301026400381.png","cup":[{"id":"4","PCTCup":"C","size":[{"id":"95","PSSize":"70","PSNumber":"95"},{"id":"96","PSSize":"75","PSNumber":"96"},{"id":"97","PSSize":"80","PSNumber":"97"},{"id":"98","PSSize":"85","PSNumber":"98"},{"id":"99","PSSize":"90","PSNumber":"99"},{"id":"100","PSSize":"95","PSNumber":"100"}]},{"id":"5","PCTCup":"D","size":[{"id":"101","PSSize":"70","PSNumber":"101"},{"id":"102","PSSize":"75","PSNumber":"102"},{"id":"103","PSSize":"80","PSNumber":"103"},{"id":"104","PSSize":"85","PSNumber":"104"},{"id":"105","PSSize":"90","PSNumber":"105"},{"id":"106","PSSize":"95","PSNumber":"106"}]}]}]},{"id":"4","ProTitle":"D6462","ProPrice":"188.00","DiscountPrice":"100.00","color":[{"id":"7","PCTitle":"香槟","PCImg":"/upload/201807/30/201807301027362616.png","cup":[{"id":"6","PCTCup":"C","size":[{"id":"107","PSSize":"70","PSNumber":"107"},{"id":"108","PSSize":"75","PSNumber":"108"},{"id":"109","PSSize":"80","PSNumber":"109"},{"id":"110","PSSize":"85","PSNumber":"110"},{"id":"111","PSSize":"90","PSNumber":"111"},{"id":"112","PSSize":"95","PSNumber":"112"}]},{"id":"7","PCTCup":"D","size":[{"id":"113","PSSize":"70","PSNumber":"113"},{"id":"114","PSSize":"75","PSNumber":"114"},{"id":"115","PSSize":"80","PSNumber":"115"},{"id":"116","PSSize":"85","PSNumber":"116"},{"id":"117","PSSize":"90","PSNumber":"117"},{"id":"118","PSSize":"95","PSNumber":"118"}]},{"id":"8","PCTCup":"E","size":[{"id":"119","PSSize":"70","PSNumber":"119"},{"id":"120","PSSize":"75","PSNumber":"120"},{"id":"121","PSSize":"80","PSNumber":"121"},{"id":"122","PSSize":"85","PSNumber":"122"},{"id":"123","PSSize":"90","PSNumber":"123"},{"id":"124","PSSize":"95","PSNumber":"124"}]}]},{"id":"8","PCTitle":"黑色","PCImg":"/upload/201807/30/201807301027439895.png","cup":[{"id":"6","PCTCup":"C","size":[{"id":"125","PSSize":"70","PSNumber":"125"},{"id":"126","PSSize":"75","PSNumber":"126"},{"id":"127","PSSize":"80","PSNumber":"127"},{"id":"128","PSSize":"85","PSNumber":"128"},{"id":"129","PSSize":"90","PSNumber":"129"},{"id":"130","PSSize":"95","PSNumber":"130"}]},{"id":"7","PCTCup":"D","size":[{"id":"131","PSSize":"70","PSNumber":"131"},{"id":"132","PSSize":"75","PSNumber":"132"},{"id":"133","PSSize":"80","PSNumber":"133"},{"id":"134","PSSize":"85","PSNumber":"134"},{"id":"135","PSSize":"90","PSNumber":"135"},{"id":"136","PSSize":"95","PSNumber":"136"}]},{"id":"8","PCTCup":"E","size":[{"id":"137","PSSize":"70","PSNumber":"137"},{"id":"138","PSSize":"75","PSNumber":"138"},{"id":"139","PSSize":"80","PSNumber":"139"},{"id":"140","PSSize":"85","PSNumber":"140"},{"id":"141","PSSize":"90","PSNumber":"141"},{"id":"142","PSSize":"95","PSNumber":"142"}]}]}]},{"id":"5","ProTitle":"D3460","ProPrice":"188.00","DiscountPrice":"100.00","color":[{"id":"9","PCTitle":"香槟色","PCImg":"/upload/201807/30/201807301028250998.png","cup":[{"id":"9","PCTCup":"均码","size":[{"id":"143","PSSize":"70","PSNumber":"143"},{"id":"144","PSSize":"75","PSNumber":"144"},{"id":"145","PSSize":"80","PSNumber":"145"},{"id":"146","PSSize":"85","PSNumber":"146"},{"id":"147","PSSize":"90","PSNumber":"147"},{"id":"148","PSSize":"95","PSNumber":"148"}]}]},{"id":"10","PCTitle":"黑色","PCImg":"/upload/201807/30/201807301028328584.png","cup":[{"id":"9","PCTCup":"均码","size":[{"id":"149","PSSize":"70","PSNumber":"149"},{"id":"150","PSSize":"75","PSNumber":"150"},{"id":"151","PSSize":"80","PSNumber":"151"},{"id":"152","PSSize":"85","PSNumber":"152"},{"id":"153","PSSize":"90","PSNumber":"153"},{"id":"154","PSSize":"95","PSNumber":"154"}]}]}]}]
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
         * ProTitle : D6460
         * ProPrice : 188.00
         * DiscountPrice : 100.00
         * color : [{"id":"3","PCTitle":"香槟色","PCImg":"/upload/201807/30/201807301021554616.png","cup":[{"id":"3","PCTCup":"A","size":[{"id":"71","PSSize":"70","PSNumber":"71"},{"id":"73","PSSize":"80","PSNumber":"73"},{"id":"74","PSSize":"85","PSNumber":"74"},{"id":"76","PSSize":"95","PSNumber":"76"}]}]},{"id":"4","PCTitle":"黑色","PCImg":"/upload/201807/30/201807301022050102.png","cup":[{"id":"3","PCTCup":"A","size":[{"id":"78","PSSize":"75","PSNumber":"78"},{"id":"79","PSSize":"80","PSNumber":"79"},{"id":"80","PSSize":"85","PSNumber":"80"},{"id":"81","PSSize":"90","PSNumber":"81"},{"id":"82","PSSize":"95","PSNumber":"82"}]}]}]
         */
        private int allNum;
        private String id;
        private String ProTitle;
        private String ProPrice;
        private String DiscountPrice;
        private List<ColorBean> color;

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

        public String getProPrice() {
            return ProPrice;
        }

        public void setProPrice(String ProPrice) {
            this.ProPrice = ProPrice;
        }

        public String getDiscountPrice() {
            return DiscountPrice;
        }

        public void setDiscountPrice(String DiscountPrice) {
            this.DiscountPrice = DiscountPrice;
        }

        public List<ColorBean> getColor() {
            return color;
        }

        public void setColor(List<ColorBean> color) {
            this.color = color;
        }

        public static class ColorBean {
            /**
             * id : 3
             * PCTitle : 香槟色
             * PCImg : /upload/201807/30/201807301021554616.png
             * cup : [{"id":"3","PCTCup":"A","size":[{"id":"71","PSSize":"70","PSNumber":"71"},{"id":"73","PSSize":"80","PSNumber":"73"},{"id":"74","PSSize":"85","PSNumber":"74"},{"id":"76","PSSize":"95","PSNumber":"76"}]}]
             */

            private String id;
            private String PCTitle;
            private String PCImg;
            private List<CupBean> cup;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPCTitle() {
                return PCTitle;
            }

            public void setPCTitle(String PCTitle) {
                this.PCTitle = PCTitle;
            }

            public String getPCImg() {
                return PCImg;
            }

            public void setPCImg(String PCImg) {
                this.PCImg = PCImg;
            }

            public List<CupBean> getCup() {
                return cup;
            }

            public void setCup(List<CupBean> cup) {
                this.cup = cup;
            }

            public static class CupBean {
                /**
                 * id : 3
                 * PCTCup : A
                 * size : [{"id":"71","PSSize":"70","PSNumber":"71"},{"id":"73","PSSize":"80","PSNumber":"73"},{"id":"74","PSSize":"85","PSNumber":"74"},{"id":"76","PSSize":"95","PSNumber":"76"}]
                 */

                private String id;
                private String PCTCup;
                private List<SizeBean> size;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getPCTCup() {
                    return PCTCup;
                }

                public void setPCTCup(String PCTCup) {
                    this.PCTCup = PCTCup;
                }

                public List<SizeBean> getSize() {
                    return size;
                }

                public void setSize(List<SizeBean> size) {
                    this.size = size;
                }

                public static class SizeBean {
                    /**
                     * id : 71
                     * PSSize : 70
                     * PSNumber : 71
                     */

                    private String id;
                    private String PSSize;
                    private String PSNumber;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getPSSize() {
                        return PSSize;
                    }

                    public void setPSSize(String PSSize) {
                        this.PSSize = PSSize;
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
    }
}
