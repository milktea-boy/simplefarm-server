package org.milkteaboy.simplefarm.entity;

/**
 * 种子实体
 */
public class Seed {

    /**种子ID**/
    private Integer id;
    /**价格**/
    private Integer price;
    /**收获时间间隔，单位秒**/
    private Integer reapInterval;
    /**最大浇水次数**/
    private Integer maxWaterCount;
    /**成熟最小货物数**/
    private Integer minGoods;
    /**成熟最大货物数**/
    private Integer maxGoods;
    /**食物**/
    private Food food;

    public Seed() {
    }

    public Seed(Integer id, Integer price, Integer reapInterval, Integer maxWaterCount, Integer minGoods, Integer maxGoods, Food food) {
        this.id = id;
        this.price = price;
        this.reapInterval = reapInterval;
        this.maxWaterCount = maxWaterCount;
        this.minGoods = minGoods;
        this.maxGoods = maxGoods;
        this.food = food;
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

    public Integer getMaxWaterCount() {
        return maxWaterCount;
    }

    public void setMaxWaterCount(Integer maxWaterCount) {
        this.maxWaterCount = maxWaterCount;
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

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
