package org.milkteaboy.simplefarm.service.dto;

/**
 * shop模块货物DTO
 */
public class ShopGoodsInfo {

    /**货物ID**/
    private int id;
    /**货物数量**/
    private int count;
    /**货物价格**/
    private int price;

    public ShopGoodsInfo() {
    }

    public ShopGoodsInfo(int id, int count, int price) {
        this.id = id;
        this.count = count;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
