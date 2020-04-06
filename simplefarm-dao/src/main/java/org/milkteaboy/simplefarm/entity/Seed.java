package org.milkteaboy.simplefarm.entity;

/**
 * 种子实体
 */
public class Seed {

    /**种子ID**/
    private Integer id;
    /**种子价格**/
    private Integer price;
    /**收获时间间隔，单位秒**/
    private Integer reapInterval;
    /**食物ID**/
    private Integer foodId;
    /**最大浇水次数**/
    private Integer maxWaterCount;
    /**货物ID**/
    private Integer goodsId;
    /**最小货物数**/
    private Integer minGoods;
    /**最大货物数**/
    private Integer maxGoods;

    public Seed() {
    }

    public Seed(Integer id, Integer price, Integer reapInterval, Integer foodId, Integer maxWaterCount, Integer goodsId, Integer minGoods, Integer maxGoods) {
        this.id = id;
        this.price = price;
        this.reapInterval = reapInterval;
        this.foodId = foodId;
        this.maxWaterCount = maxWaterCount;
        this.goodsId = goodsId;
        this.minGoods = minGoods;
        this.maxGoods = maxGoods;
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

    public Integer getReapInterval() {
        return reapInterval;
    }

    public void setReapInterval(Integer reapInterval) {
        this.reapInterval = reapInterval;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getMaxWaterCountCount() {
        return maxWaterCount;
    }

    public void setMaxWaterCountCount(Integer maxWaterCount) {
        this.maxWaterCount = maxWaterCount;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getMinGoods() {
        return minGoods;
    }

    public void setMinGoods(Integer minGoods) {
        this.minGoods = minGoods;
    }

    public Integer getMaxGoods() {
        return maxGoods;
    }

    public void setMaxGoods(Integer maxGoods) {
        this.maxGoods = maxGoods;
    }
}
