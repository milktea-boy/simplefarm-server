package org.milkteaboy.simplefarm.service.dto;

/**
 * shop模块幼崽DTO
 */
public class ShopBabyInfo {

    /**幼崽ID**/
    private int id;
    /**幼崽价格**/
    private int price;

    public ShopBabyInfo() {
    }

    public ShopBabyInfo(int id, int price) {
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
