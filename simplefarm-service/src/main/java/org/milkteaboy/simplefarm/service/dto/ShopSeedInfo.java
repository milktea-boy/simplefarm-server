package org.milkteaboy.simplefarm.service.dto;

/**
 * shop模块种子DTO
 */
public class ShopSeedInfo {

    /**种子ID**/
    private int id;
    /**种子价格**/
    private int price;

    public ShopSeedInfo() {
    }

    public ShopSeedInfo(int id, int price) {
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
