package org.milkteaboy.simplefarm.service.dto;

/**
 * shop模块食物DTO
 */
public class ShopFoodInfo {

    /**食物ID**/
    private int id;
    /**食物价格**/
    private int price;

    public ShopFoodInfo() {
    }

    public ShopFoodInfo(int id, int price) {
        this.id = id;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
