package org.milkteaboy.simplefarm.entity;

/**
 * 幼崽实体
 */
public class Baby {

    /**幼崽ID**/
    private Integer id;
    /**幼崽价格**/
    private Integer price;
    /**人口**/
    private Integer population;
    /**收获时间间隔，单位秒**/
    private Integer reapInterval;
    /**食物ID**/
    private Integer foodId;
    /**最大喂养次数**/
    private Integer maxFeedCount;
    /**货物ID**/
    private Integer goodsId;
    /**最小货物数**/
    private Integer minGoods;
    /**最大货物数**/
    private Integer maxGoods;

    public Baby() {
    }

    public Baby(Integer id, Integer price, Integer population, Integer reapInterval, Integer foodId, Integer maxFeedCount, Integer goodsId, Integer minGoods, Integer maxGoods) {
        this.id = id;
        this.price = price;
        this.population = population;
        this.reapInterval = reapInterval;
        this.foodId = foodId;
        this.maxFeedCount = maxFeedCount;
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

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
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

    public Integer getMaxFeedCount() {
        return maxFeedCount;
    }

    public void setMaxFeedCount(Integer maxFeedCount) {
        this.maxFeedCount = maxFeedCount;
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
