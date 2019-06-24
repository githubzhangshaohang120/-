package com.example.tbdemo.bean;

import java.util.List;

public class CartBean {

    /**
     * result : [{"categoryName":"美妆护肤","shoppingCartList":[{"commodityId":5,"commodityName":"双头两用修容笔","count":3,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg","price":39},{"commodityId":6,"commodityName":"轻柔系自然裸妆假睫毛","count":4,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/4/1.jpg","price":39},{"commodityId":3,"commodityName":"Lara style女神的魔盒全套彩妆","count":5,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/1/1.jpg","price":3499},{"commodityId":4,"commodityName":"佩佩防晕染眼线液笔","count":6,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/2/1.jpg","price":19},{"commodityId":7,"commodityName":"蓝色之恋","count":7,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/5/1.jpg","price":29},{"commodityId":8,"commodityName":"Lara style蜜颊润泽腮红","count":8,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/6/1.jpg","price":19},{"commodityId":10,"commodityName":"BYPHASSE蓓昂丝温和清洁净肤保湿卸妆水","count":10,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/7/1.jpg","price":69}]}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * categoryName : 美妆护肤
         * shoppingCartList : [{"commodityId":5,"commodityName":"双头两用修容笔","count":3,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg","price":39},{"commodityId":6,"commodityName":"轻柔系自然裸妆假睫毛","count":4,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/4/1.jpg","price":39},{"commodityId":3,"commodityName":"Lara style女神的魔盒全套彩妆","count":5,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/1/1.jpg","price":3499},{"commodityId":4,"commodityName":"佩佩防晕染眼线液笔","count":6,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/2/1.jpg","price":19},{"commodityId":7,"commodityName":"蓝色之恋","count":7,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/5/1.jpg","price":29},{"commodityId":8,"commodityName":"Lara style蜜颊润泽腮红","count":8,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/6/1.jpg","price":19},{"commodityId":10,"commodityName":"BYPHASSE蓓昂丝温和清洁净肤保湿卸妆水","count":10,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/7/1.jpg","price":69}]
         */
        public boolean cartChecked;
        public String categoryName;
        public List<ShoppingCartListBean> shoppingCartList;

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public List<ShoppingCartListBean> getShoppingCartList() {
            return shoppingCartList;
        }

        public void setShoppingCartList(List<ShoppingCartListBean> shoppingCartList) {
            this.shoppingCartList = shoppingCartList;
        }

        public static class ShoppingCartListBean {
            /**
             * commodityId : 5
             * commodityName : 双头两用修容笔
             * count : 3
             * pic : http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg
             * price : 39
             */
            //二级是否选中
            public boolean productChecked;
            public int commodityId;
            public String commodityName;
            public int count;
            public String pic;
            public int price;

            //用户购买数量
            public int num = 1;

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }
        }
    }
}
