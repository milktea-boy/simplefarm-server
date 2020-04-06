package org.milkteaboy.simplefarm.entity;

/**
 * 货物实体
 */
public class Goods {

    /**货物ID**/
    private Integer id;
    /**货物价格**/
    private Integer price;

    public Goods() {
    }

    public Goods(Integer id, Integer price) {
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
