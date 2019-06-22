package com.example.tbdemo.bean;

import java.util.List;
public class CartBean {


    /**
     * result : []
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<Result> result;


    /**
     * 一级对象
     */
    public static class Result{

        //一级是否选中
        public boolean cartChecked;
        public String categoryName;
        public  List<Product> shoppingCartList;

        /**
         * 二级对象
         */
        public static class Product{
            //二级是否选中
            public boolean productChecked;
            public String commodityId;
            public String commodityName;
            public String count;
            public String pic;
            public String price;

            //用户购买数量
            public int num = 1;
        }


    }

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

    public List<?> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }
}
