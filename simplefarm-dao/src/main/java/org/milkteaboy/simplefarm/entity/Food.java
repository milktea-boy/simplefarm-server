package org.milkteaboy.simplefarm.entity;

/**
 * 食物实体
 */
public class Food {

    /**食物ID**/
    private Integer id;
    /**食物价格**/
    private Integer price;

    public Food() {
    }

    public Food(Integer id, Integer price) {
        this.id = id;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
