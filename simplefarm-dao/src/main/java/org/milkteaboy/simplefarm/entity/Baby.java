package org.milkteaboy.simplefarm.entity;

/**
 * 幼崽实体
 */
public class Baby {

    /**幼崽ID**/
    private Integer id;
    /**价格**/
    private Integer price;
    /**人口**/
    private Integer population;
    /**收获时间间隔，单位秒**/
    private Integer reapInterval;
    /**最大喂养次数**/
    private Integer maxFeedCount;
    /**成熟最小货物数**/
    private Integer minGoods;
    /**成熟最大货物数**/
    private Integer maxGoods;
    /**食物**/
    private Food food;
    /**货物**/
    private Goods goods;

    public Baby() {
    }

    public Baby(Integer id, Integer price, Integer population, Integer reapInterval, Integer maxFeedCount, Integer minGoods, Integer maxGoods, Food food, Goods goods) {
        this.id = id;
        this.price = price;
        this.population = population;
        this.reapInterval = reapInterval;
        this.maxFeedCount = maxFeedCount;
        this.minGoods = minGoods;
        this.maxGoods = maxGoods;
        this.food = food;
        this.goods = goods;
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

    public Integer getMaxFeedCount() {
        return maxFeedCount;
    }

    public void setMaxFeedCount(Integer maxFeedCount) {
        this.maxFeedCount = maxFeedCount;
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

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
